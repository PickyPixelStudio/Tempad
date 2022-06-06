package me.codexadrian.tempad;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class TempadTeleporter implements ITeleporter {
    private final BlockPos position;

    public TempadTeleporter(BlockPos pos) {
        this.position = pos;
    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        return new PortalInfo(Vec3.atCenterOf(this.position), entity.getDeltaMovement(), entity.yRot, entity.xRot);
    }

    @Override
    public boolean isVanilla() {
        return false;
    }
}
