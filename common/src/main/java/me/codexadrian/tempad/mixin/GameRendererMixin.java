package me.codexadrian.tempad.mixin;

import me.codexadrian.tempad.platform.Services;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "resize", at = @At("TAIL"))
    public void resize(int i, int j, CallbackInfo ci) {
        PostChain timedoorBlur = Services.SHADERS.getTempadReloader().getTimedoorBlur();
        if (timedoorBlur != null) timedoorBlur.resize(i, j);
    }
}
