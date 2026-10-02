package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.ECHO_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.ECHO_PICKAXE));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.ECHO_AXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.ECHO_SHOVEL));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.ECHO_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.ECHO_SPEAR));

        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.getRK(ModItems.ECHO_BOW));

        tag(ItemTags.ARROWS).add(ModItems.getRK(ModItems.ECHO_ARROW));

        tag(ItemTags.HEAD_ARMOR).add(ModItems.getRK(ModItems.ECHO_HELMET));
        tag(ItemTags.CHEST_ARMOR).add(ModItems.getRK(ModItems.ECHO_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR).add(ModItems.getRK(ModItems.ECHO_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR).add(ModItems.getRK(ModItems.ECHO_BOOTS));

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.getRK(ModItems.ECHO_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.ECHO_NAUTILUS_ARMOR));



        tag(ModTags.Items.DREAM_GREEN_LOGS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_GREEN_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_GREEN_LOG.asItem()));

        tag(ModTags.Items.DREAM_PURPLE_LOGS)
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_PURPLE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_PURPLE_LOG.asItem()));

        tag(ModTags.Items.DREAM_BLUE_LOGS)
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_BLUE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_BLUE_LOG.asItem()));

        tag(ModTags.Items.DREAM_LIGHT_BLUE_LOGS)
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG.asItem()));

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.DREAM_LOGS);

        tag(ModTags.Items.DREAM_LOGS)
                .addTag(ModTags.Items.DREAM_GREEN_LOGS)
                .addTag(ModTags.Items.DREAM_PURPLE_LOGS)
                .addTag(ModTags.Items.DREAM_BLUE_LOGS)
                .addTag(ModTags.Items.DREAM_LIGHT_BLUE_LOGS);

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_PLANKS.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_PLANKS.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_PLANKS.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_PLANKS.asItem()));

        tag(ItemTags.LEAVES)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_LEAVES.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_LEAVES.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_LEAVES.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_LEAVES.asItem()));

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_BUTTON.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_BUTTON.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_BUTTON.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_BUTTON.asItem()));

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_PRESSURE_PLATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_PRESSURE_PLATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_PRESSURE_PLATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE.asItem()));

        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_FENCE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_FENCE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_FENCE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE.asItem()));

        tag(ItemTags.FENCE_GATES)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_FENCE_GATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_FENCE_GATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_FENCE_GATE.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE_GATE.asItem()));

        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_DOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_DOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_DOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_DOOR.asItem()));

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_TRAPDOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_TRAPDOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_TRAPDOOR.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_TRAPDOOR.asItem()));

        tag(ItemTags.SIGNS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_WALL_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_WALL_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_WALL_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_SIGN.asItem()));

        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_WOOD_WALL_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_WOOD_WALL_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_WOOD_WALL_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN.asItem()));

        tag(ItemTags.SAPLINGS)
                .add(ModItems.getRK(ModBlocks.DREAM_GREEN_SAPLING.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_PURPLE_SAPLING.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_BLUE_SAPLING.asItem()))
                .add(ModItems.getRK(ModBlocks.DREAM_LIGHT_BLUE_SAPLING.asItem()));
    }
}
