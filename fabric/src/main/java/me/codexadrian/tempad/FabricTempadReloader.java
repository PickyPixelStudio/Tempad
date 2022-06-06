package me.codexadrian.tempad;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

import static me.codexadrian.tempad.Constants.MODID;

public class FabricTempadReloader extends TempadReloader implements IdentifiableResourceReloadListener {

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(MODID, "timedoorblur");
    }
}
