package me.codexadrian.tempad.platform;

import me.codexadrian.tempad.TempadReloader;
import me.codexadrian.tempad.ForgeTempadClient;
import me.codexadrian.tempad.TimedoorBlurRenderType;
import me.codexadrian.tempad.platform.services.IShaderHelper;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.RenderType;

public class ForgeShaderHelper implements IShaderHelper {
    @Override
    public EffectInstance getTimedoorShader() {
        return ForgeTempadClient.timedoorShader;
    }

    @Override
    public void setTimeDoorShader(EffectInstance shader) {
        ForgeTempadClient.timedoorShader = shader;
    }

    @Override
    public EffectInstance getTimedoorWhiteShader() {
        return ForgeTempadClient.timedoorWhiteShader;
    }

    @Override
    public void setTimedoorWhiteShader(EffectInstance shader) {
        ForgeTempadClient.timedoorWhiteShader = shader;
    }

    @Override
    public RenderType getTimedoorShaderType() {
        return TimedoorBlurRenderType.TIMEDOOR_BLUR_RENDER_TYPE;
    }

    @Override
    public TempadReloader getTempadReloader() {
        return ForgeTempadClient.INSTANCE;
    }
}
