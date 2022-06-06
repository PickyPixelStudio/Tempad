package me.codexadrian.tempad;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import org.lwjgl.opengl.GL11;

public class TimedoorBlurRenderType extends RenderType {
    public static final RenderType TIMEDOOR_BLUR_RENDER_TYPE = timedoorShader();

    public TimedoorBlurRenderType(String p_173178_, VertexFormat p_173179_, int p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    private static RenderType timedoorShader() {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(new RenderStateShard.CullStateShard(false))
                .createCompositeState(false);

        return create("timedoorBlur", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, GL11.GL_QUADS, 256, false, true, state);
    }
}
