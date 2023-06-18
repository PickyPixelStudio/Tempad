package me.codexadrian.tempad;

import dev.architectury.injectables.annotations.ExpectPlatform;
import me.codexadrian.tempad.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.util.function.Supplier;

public class Tempad {

    private static TempadConfig tempadConfig;

    public static final TagKey<Item> TEMPAD_FUEL_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MODID, "tempad_fuel"));

    public static final Supplier<SoundEvent> TIMEDOOR_SOUND = registerSound("entity.timedoor.open");

    public static void init() {
        try {
            tempadConfig = TempadConfig.loadConfig(Services.PLATFORM.getConfigDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TempadConfig getTempadConfig() {
        return tempadConfig;
    }

    @ExpectPlatform
    public static Supplier<SoundEvent> registerSound(String name) {
        throw new AssertionError("Not implemented");
    }
}
