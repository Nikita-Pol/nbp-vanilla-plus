package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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
                .add(ModBlocks.getRK(ModBlocks.OBSIDIAN_REDSTONE_LAMP))
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN));


        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.ECHO_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN))
                .add(ModBlocks.getRK(ModBlocks.OBSIDIAN_REDSTONE_LAMP));

        tag(ModTags.Blocks.NEEDS_ECHO_TOOL)
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN))
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL);

        tag(BlockTags.CROPS)
                .add(ModBlocks.getRK(ModBlocks.STRANGE_BEETROOT_CROP))
                .add(ModBlocks.getRK(ModBlocks.DREAM_FLOWER));

        tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES))
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES_PLANT));

        tag(BlockTags.CAVE_VINES)
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES))
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES_PLANT));

        tag(ModTags.Blocks.DREAM_PETALS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_PETALS_GREEN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PETALS_BLUE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PETALS_PURPLE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PETALS_LIGHT_BLUE));

        tag(ModTags.Blocks.AMETHYST_BUSH_PLACEABLE)
                .add(ModBlocks.getRK(Blocks.AMETHYST_BLOCK));

        tag(ModTags.Blocks.ECHO_VINES_PLACEABLE)
                .add(ModBlocks.getRK(Blocks.SCULK))
                .add(ModBlocks.getRK(ModBlocks.ECHO_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.ECHO_MAGMA))
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES))
                .add(ModBlocks.getRK(ModBlocks.ECHO_VINES_PLANT));
    }
}
