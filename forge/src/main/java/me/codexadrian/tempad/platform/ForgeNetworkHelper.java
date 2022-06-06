package me.codexadrian.tempad.platform;

import me.codexadrian.tempad.Constants;
import me.codexadrian.tempad.network.handlers.IPacketHandler;
import me.codexadrian.tempad.network.handlers.IPacket;
import me.codexadrian.tempad.platform.services.INetworkHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ForgeNetworkHelper implements INetworkHelper {
    private static int id = 0;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Constants.MODID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @Override
    public <T extends IPacket<T>> void sendToServer(T packet) {
        INSTANCE.sendToServer(packet);
    }

    @Override
    public <T> void registerClientToServerPacket(ResourceLocation location, IPacketHandler<T> handler, Class<T> tClass) {
        INSTANCE.registerMessage(++id, tClass, handler::encode, handler::decode, (t, context) -> {
            ServerPlayer sender = context.get().getSender();
            if(sender != null) {
                context.get().enqueueWork(() -> handler.handle(t).accept(sender.server, sender));
            }
            context.get().setPacketHandled(true);
        });
    }
}
