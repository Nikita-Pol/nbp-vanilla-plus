package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.ECHO_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.ECHO_MAGMA))
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN));


        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.ECHO_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN));

        tag(ModTags.Blocks.NEEDS_ECHO_TOOL)
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN))
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL);
    }
}
