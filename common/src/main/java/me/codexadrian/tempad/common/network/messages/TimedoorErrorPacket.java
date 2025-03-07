package me.codexadrian.tempad.common.network.messages;

import com.teamresourceful.resourcefullib.common.networking.base.Packet;
import com.teamresourceful.resourcefullib.common.networking.base.PacketContext;
import com.teamresourceful.resourcefullib.common.networking.base.PacketHandler;
import me.codexadrian.tempad.common.Tempad;
import me.codexadrian.tempad.common.utils.ClientUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record TimedoorErrorPacket(String error) implements Packet<TimedoorErrorPacket> {
    public static Handler HANDLER = new Handler();
    public static final ResourceLocation ID = new ResourceLocation(Tempad.MODID, "timedoor_error");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public PacketHandler<TimedoorErrorPacket> getHandler() {
        return HANDLER;
    }

    private static class Handler implements PacketHandler<TimedoorErrorPacket> {

        @Override
        public void encode(TimedoorErrorPacket message, FriendlyByteBuf buffer) {
            buffer.writeUtf(message.error);
        }

        @Override
        public TimedoorErrorPacket decode(FriendlyByteBuf buffer) {
            return new TimedoorErrorPacket(buffer.readUtf());
        }

        @Override
        public PacketContext handle(TimedoorErrorPacket message) {
            return (player, level) -> ClientUtils.showError(message);
        }
    }
}
