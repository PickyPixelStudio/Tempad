package me.codexadrian.tempad;

import me.codexadrian.tempad.client.render.TimedoorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeTempadClient {
    public static EffectInstance timedoorShader;
    public static EffectInstance timedoorWhiteShader;
    public static final TempadReloader INSTANCE = new TempadReloader();

    public static void init() {
        TempadClient.init();
        RenderingRegistry.registerEntityRenderingHandler(ForgeTempad.TIMEDOOR.get(), TimedoorRenderer::new);
        FMLJavaModLoadingContext.get().getModEventBus().register(ForgeTempadClient.class);
    }

    public static void registerTempadReloader() {
        ((ReloadableResourceManager) Minecraft.getInstance().getResourceManager()).registerReloadListener(INSTANCE);
    }
}
