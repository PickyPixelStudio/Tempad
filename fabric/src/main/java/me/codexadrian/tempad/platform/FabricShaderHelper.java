package me.codexadrian.tempad.platform;

import me.codexadrian.tempad.FabricTempadClient;
import me.codexadrian.tempad.TempadReloader;
import me.codexadrian.tempad.platform.services.IShaderHelper;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.RenderType;

public class FabricShaderHelper implements IShaderHelper {
    @Override
    public EffectInstance getTimedoorShader() {
        return FabricTempadClient.timedoorShader;
    }

    @Override
    public void setTimeDoorShader(EffectInstance shader) {
        FabricTempadClient.timedoorShader = shader;
    }

    @Override
    public EffectInstance getTimedoorWhiteShader() {
        return FabricTempadClient.timedoorWhiteShader;
    }

    @Override
    public void setTimedoorWhiteShader(EffectInstance shader) {
        FabricTempadClient.timedoorWhiteShader = shader;
    }

    @Override
    public RenderType getTimedoorShaderType() {
        return FabricTempadClient.timedoorBlurRenderType;
    }

    @Override
    public TempadReloader getTempadReloader() {
        return FabricTempadClient.INSTANCE;
    }
}
