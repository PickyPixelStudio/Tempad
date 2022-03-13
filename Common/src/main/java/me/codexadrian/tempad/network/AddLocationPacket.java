package me.codexadrian.tempad.network;

import me.codexadrian.tempad.network.handlers.IMessageHandler;
import me.codexadrian.tempad.network.handlers.IPacket;
import me.codexadrian.tempad.tempad.LocationData;
import me.codexadrian.tempad.tempad.TempadComponent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import static me.codexadrian.tempad.Constants.MODID;

public record AddLocationPacket(String name, InteractionHand hand) implements IPacket<AddLocationPacket> {
    public static Handler HANDLER = new Handler();
    public static final ResourceLocation ID = new ResourceLocation(MODID, "location");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public IMessageHandler<AddLocationPacket> getHandler() {
        return HANDLER;
    }

    private static class Handler implements IMessageHandler<AddLocationPacket> {

        @Override
        public void encode(AddLocationPacket message, FriendlyByteBuf buffer) {
            buffer.writeUtf(message.name);
            buffer.writeEnum(message.hand);
        }

        @Override
        public AddLocationPacket decode(FriendlyByteBuf buffer) {
            return new AddLocationPacket(buffer.readUtf(), buffer.readEnum(InteractionHand.class));
        }

        @Override
        public boolean handle(AddLocationPacket message, MinecraftServer server, Player player) {
            server.execute(() -> {
                ItemStack stack = player.getItemInHand(message.hand);
                var tempadLocation = new LocationData(message.name, player.level.dimension(), player.blockPosition());

                TempadComponent.addStackLocation(stack, tempadLocation);
            });
            return true;
        }
    }

}