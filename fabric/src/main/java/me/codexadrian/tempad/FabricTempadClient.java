package me.codexadrian.tempad;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import me.codexadrian.tempad.client.render.TimedoorRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.server.packs.PackType;
import org.lwjgl.opengl.GL11;

public class FabricTempadClient implements ClientModInitializer {
    public static EffectInstance timedoorShader;
    public static EffectInstance timedoorWhiteShader;
    public static final RenderType timedoorBlurRenderType = RenderType.create("timedoorBlur", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, GL11.GL_QUADS, 256, false, true, RenderType.CompositeState.builder().setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY).setCullState(new RenderStateShard.CullStateShard(false)).createCompositeState(false));
    public static final FabricTempadReloader INSTANCE = new FabricTempadReloader();

    @Override
    public void onInitializeClient() {
        TempadClient.init();
        EntityRendererRegistry.register(FabricTempad.TIMEDOOR_ENTITY_ENTITY_TYPE, (entityRendererDispatch, ctx) -> new TimedoorRenderer(entityRendererDispatch));
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(INSTANCE);
    }
}
