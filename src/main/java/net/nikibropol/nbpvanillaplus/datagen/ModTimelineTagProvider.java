package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TimelineTags;
import net.minecraft.world.timeline.Timeline;
import net.nikibropol.nbpvanillaplus.worldgen.ModTimelines;

import java.util.concurrent.CompletableFuture;

public class ModTimelineTagProvider extends FabricTagsProvider<Timeline> {
    public ModTimelineTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.TIMELINE, registries);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(TimelineTags.IN_OVERWORLD).add(ModTimelines.NIGHT_FOG);
    }
}
