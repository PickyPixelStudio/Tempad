package me.codexadrian.tempad;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import me.codexadrian.tempad.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import java.io.IOException;

public class TempadReloader implements ResourceManagerReloadListener {
    private PostChain timedoorBlur;
    private RenderTarget renderTarget;

    protected TempadReloader() {}

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        var minecraft = Minecraft.getInstance();

        if (timedoorBlur != null) {
            timedoorBlur.close();
        }

        ResourceLocation resourceLocation = new ResourceLocation("shaders/post/timedoorblur.json");
        try {
            timedoorBlur = new PostChain(minecraft.getTextureManager(), resourceManager, minecraft.getMainRenderTarget(), resourceLocation);
            timedoorBlur.resize(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
            renderTarget = timedoorBlur.getTempTarget("blur_target");
        } catch (JsonSyntaxException | IOException var4) {
            timedoorBlur = null;
            renderTarget = null;
        }
        try {
            Services.SHADERS.setTimeDoorShader(new EffectInstance(resourceManager, "rendertype_timedoor"));
        } catch (Exception e) {
            EffectInstance timedoorShader = Services.SHADERS.getTimedoorShader();

            if (timedoorShader != null) {
                timedoorShader.close();
                Services.SHADERS.setTimeDoorShader(null);
            }

            throw new RuntimeException("could not reload Timedoor shaders", e);
        }
        try {
            Services.SHADERS.setTimedoorWhiteShader(new EffectInstance(resourceManager, "rendertype_timedoor_white"));
        } catch (Exception e) {
            EffectInstance timedoorWhiteShader = Services.SHADERS.getTimedoorWhiteShader();

            if (timedoorWhiteShader != null) {
                timedoorWhiteShader.close();
                Services.SHADERS.setTimedoorWhiteShader(null);
            }

            throw new RuntimeException("could not reload Timedoor White shaders", e);
        }
    }

    public PostChain getTimedoorBlur() {
        return timedoorBlur;
    }

    public RenderTarget getRenderTarget() {
        return renderTarget;
    }
}
