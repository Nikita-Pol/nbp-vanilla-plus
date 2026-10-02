package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class ModPaintingsTagsProvider extends FabricTagsProvider<PaintingVariant> {

    public ModPaintingsTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.PAINTING_VARIANT, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(PaintingVariantTags.PLACEABLE)
                .add(TagEntry.element(ModPaintings.BROKEN_ETERNITY_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.DUSK_TO_DAWN_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.NAMELESS_ECHOES_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.AMBIENTS_KEY.identifier()));
    }
}
