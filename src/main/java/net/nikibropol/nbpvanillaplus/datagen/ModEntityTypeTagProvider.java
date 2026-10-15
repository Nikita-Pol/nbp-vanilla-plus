package net.nikibropol.nbpvanillaplus.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

public class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static ResourceKey<EntityType<?>> vanilla(final String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.withDefaultNamespace(path));
    }

    @Override
    protected void addTags(final HolderLookup.Provider registries) {
        tag(ModTags.Entities.ECHO_CREAKING_HEARS)
                .add(vanilla("player"))
                .add(vanilla("zombie"))
                .add(vanilla("skeleton"))
                .add(vanilla("villager"));
    }
}