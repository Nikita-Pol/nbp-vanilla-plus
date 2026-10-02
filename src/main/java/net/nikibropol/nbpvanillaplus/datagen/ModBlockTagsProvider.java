package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        List<ResourceKey<Block>> reinforcedShulkerBoxes = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            reinforcedShulkerBoxes.add(ModBlocks.getRK(ModBlocks.REINFORCED_SHULKER_BOXES.get(color)));
        }
        reinforcedShulkerBoxes.add(ModBlocks.getRK(ModBlocks.REINFORCED_SHULKER_BOX));

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_STAIRS));

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_SLAB))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_SLAB))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_SLAB))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SLAB));


        tag(ModTags.Blocks.DREAM_GREEN_LOGS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_LOG))
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_GREEN_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_GREEN_LOG));

        tag(ModTags.Blocks.DREAM_PURPLE_LOGS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_LOG))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_PURPLE_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_PURPLE_LOG));

        tag(ModTags.Blocks.DREAM_BLUE_LOGS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_LOG))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_BLUE_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_BLUE_LOG));

        tag(ModTags.Blocks.DREAM_LIGHT_BLUE_LOGS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_LOG))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG));

        tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.DREAM_LOGS);

        tag(ModTags.Blocks.DREAM_LOGS)
                .addTag(ModTags.Blocks.DREAM_GREEN_LOGS)
                .addTag(ModTags.Blocks.DREAM_PURPLE_LOGS)
                .addTag(ModTags.Blocks.DREAM_BLUE_LOGS)
                .addTag(ModTags.Blocks.DREAM_LIGHT_BLUE_LOGS);

        tag(BlockTags.PLANKS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_PLANKS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_PLANKS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_PLANKS))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_PLANKS));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_LEAVES))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_LEAVES))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_LEAVES))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_LEAVES));

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_BUTTON))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_BUTTON))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_BUTTON))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_BUTTON));

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE));

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_FENCE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_FENCE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_FENCE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE));

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE_GATE));

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_DOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_DOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_DOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_DOOR));

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_TRAPDOOR));

        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN));

        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN));

        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_WALL_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_WALL_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_WALL_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_SIGN));

        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.getRK(ModBlocks.DREAM_GREEN_WOOD_WALL_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_PURPLE_WOOD_WALL_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_BLUE_WOOD_WALL_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN));

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getRK(ModBlocks.POTTED_DREAM_GREEN_SAPLING))
                .add(ModBlocks.getRK(ModBlocks.POTTED_DREAM_PURPLE_SAPLING))
                .add(ModBlocks.getRK(ModBlocks.POTTED_DREAM_BLUE_SAPLING))
                .add(ModBlocks.getRK(ModBlocks.POTTED_DREAM_LIGHT_BLUE_SAPLING));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.ECHO_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.ECHO_MAGMA))
                .add(ModBlocks.getRK(ModBlocks.OBSIDIAN_REDSTONE_LAMP))
                .add(ModBlocks.getRK(ModBlocks.REINFORCED_OBSIDIAN))
                .add(ModBlocks.getRK(ModBlocks.DREAM_ECHO_STATUE))
                .add(reinforcedShulkerBoxes.toArray(new ResourceKey[0]));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.DREAM_ECHO_STATUE))
                .add(reinforcedShulkerBoxes.toArray(new ResourceKey[0]));

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
                .add(ModBlocks.getRK(ModBlocks.FLOWTAREM_CROP))
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
