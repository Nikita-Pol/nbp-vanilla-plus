package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.DyeColor;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.*;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import net.nikibropol.nbpvanillaplus.item.ModArmorMaterials;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {


        blockModelGenerators.woodProvider(ModBlocks.DREAM_GREEN_LOG)
                .log(ModBlocks.DREAM_GREEN_LOG)
                .wood(ModBlocks.DREAM_GREEN_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_DREAM_GREEN_LOG)
                .log(ModBlocks.STRIPPED_DREAM_GREEN_LOG)
                .wood(ModBlocks.STRIPPED_DREAM_GREEN_WOOD);
        blockModelGenerators.family(ModBlocks.DREAM_GREEN_PLANKS)
                .stairs(ModBlocks.DREAM_GREEN_WOOD_STAIRS)
                .slab(ModBlocks.DREAM_GREEN_WOOD_SLAB)
                .button(ModBlocks.DREAM_GREEN_WOOD_BUTTON)
                .pressurePlate(ModBlocks.DREAM_GREEN_WOOD_PRESSURE_PLATE)
                .fence(ModBlocks.DREAM_GREEN_WOOD_FENCE)
                .fenceGate(ModBlocks.DREAM_GREEN_WOOD_FENCE_GATE);
        blockModelGenerators.createDoor(ModBlocks.DREAM_GREEN_WOOD_DOOR);
        blockModelGenerators.createOrientableTrapdoor(ModBlocks.DREAM_GREEN_WOOD_TRAPDOOR);
        ModBlockModelGenerators.createSignModels(blockModelGenerators, ModBlocks.DREAM_GREEN_WOOD_SIGN, ModBlocks.DREAM_GREEN_WOOD_WALL_SIGN, ModBlocks.DREAM_GREEN_PLANKS);
        ModBlockModelGenerators.createHangingSignModels(blockModelGenerators, ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN, ModBlocks.DREAM_GREEN_WOOD_WALL_HANGING_SIGN, ModBlocks.DREAM_GREEN_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.DREAM_GREEN_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.DREAM_GREEN_SAPLING, ModBlocks.POTTED_DREAM_GREEN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.woodProvider(ModBlocks.DREAM_PURPLE_LOG)
                .log(ModBlocks.DREAM_PURPLE_LOG)
                .wood(ModBlocks.DREAM_PURPLE_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_DREAM_PURPLE_LOG)
                .log(ModBlocks.STRIPPED_DREAM_PURPLE_LOG)
                .wood(ModBlocks.STRIPPED_DREAM_PURPLE_WOOD);
        blockModelGenerators.family(ModBlocks.DREAM_PURPLE_PLANKS)
                .stairs(ModBlocks.DREAM_PURPLE_WOOD_STAIRS)
                .slab(ModBlocks.DREAM_PURPLE_WOOD_SLAB)
                .button(ModBlocks.DREAM_PURPLE_WOOD_BUTTON)
                .pressurePlate(ModBlocks.DREAM_PURPLE_WOOD_PRESSURE_PLATE)
                .fence(ModBlocks.DREAM_PURPLE_WOOD_FENCE)
                .fenceGate(ModBlocks.DREAM_PURPLE_WOOD_FENCE_GATE);
        blockModelGenerators.createDoor(ModBlocks.DREAM_PURPLE_WOOD_DOOR);
        blockModelGenerators.createOrientableTrapdoor(ModBlocks.DREAM_PURPLE_WOOD_TRAPDOOR);
        ModBlockModelGenerators.createSignModels(blockModelGenerators, ModBlocks.DREAM_PURPLE_WOOD_SIGN, ModBlocks.DREAM_PURPLE_WOOD_WALL_SIGN, ModBlocks.DREAM_PURPLE_PLANKS);
        ModBlockModelGenerators.createHangingSignModels(blockModelGenerators, ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN, ModBlocks.DREAM_PURPLE_WOOD_WALL_HANGING_SIGN, ModBlocks.DREAM_PURPLE_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.DREAM_PURPLE_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.DREAM_PURPLE_SAPLING, ModBlocks.POTTED_DREAM_PURPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.woodProvider(ModBlocks.DREAM_BLUE_LOG)
                .log(ModBlocks.DREAM_BLUE_LOG)
                .wood(ModBlocks.DREAM_BLUE_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_DREAM_BLUE_LOG)
                .log(ModBlocks.STRIPPED_DREAM_BLUE_LOG)
                .wood(ModBlocks.STRIPPED_DREAM_BLUE_WOOD);
        blockModelGenerators.family(ModBlocks.DREAM_BLUE_PLANKS)
                .stairs(ModBlocks.DREAM_BLUE_WOOD_STAIRS)
                .slab(ModBlocks.DREAM_BLUE_WOOD_SLAB)
                .button(ModBlocks.DREAM_BLUE_WOOD_BUTTON)
                .pressurePlate(ModBlocks.DREAM_BLUE_WOOD_PRESSURE_PLATE)
                .fence(ModBlocks.DREAM_BLUE_WOOD_FENCE)
                .fenceGate(ModBlocks.DREAM_BLUE_WOOD_FENCE_GATE);
        blockModelGenerators.createDoor(ModBlocks.DREAM_BLUE_WOOD_DOOR);
        blockModelGenerators.createOrientableTrapdoor(ModBlocks.DREAM_BLUE_WOOD_TRAPDOOR);
        ModBlockModelGenerators.createSignModels(blockModelGenerators, ModBlocks.DREAM_BLUE_WOOD_SIGN, ModBlocks.DREAM_BLUE_WOOD_WALL_SIGN, ModBlocks.DREAM_BLUE_PLANKS);
        ModBlockModelGenerators.createHangingSignModels(blockModelGenerators, ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN, ModBlocks.DREAM_BLUE_WOOD_WALL_HANGING_SIGN, ModBlocks.DREAM_BLUE_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.DREAM_BLUE_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.DREAM_BLUE_SAPLING, ModBlocks.POTTED_DREAM_BLUE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.woodProvider(ModBlocks.DREAM_LIGHT_BLUE_LOG)
                .log(ModBlocks.DREAM_LIGHT_BLUE_LOG)
                .wood(ModBlocks.DREAM_LIGHT_BLUE_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG)
                .log(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG)
                .wood(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_WOOD);
        blockModelGenerators.family(ModBlocks.DREAM_LIGHT_BLUE_PLANKS)
                .stairs(ModBlocks.DREAM_LIGHT_BLUE_WOOD_STAIRS)
                .slab(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SLAB)
                .button(ModBlocks.DREAM_LIGHT_BLUE_WOOD_BUTTON)
                .pressurePlate(ModBlocks.DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE)
                .fence(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE)
                .fenceGate(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE_GATE);
        blockModelGenerators.createDoor(ModBlocks.DREAM_LIGHT_BLUE_WOOD_DOOR);
        blockModelGenerators.createOrientableTrapdoor(ModBlocks.DREAM_LIGHT_BLUE_WOOD_TRAPDOOR);
        ModBlockModelGenerators.createSignModels(blockModelGenerators, ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN, ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_SIGN, ModBlocks.DREAM_LIGHT_BLUE_PLANKS);
        ModBlockModelGenerators.createHangingSignModels(blockModelGenerators, ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN, ModBlocks.DREAM_LIGHT_BLUE_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.DREAM_LIGHT_BLUE_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.DREAM_LIGHT_BLUE_SAPLING, ModBlocks.POTTED_DREAM_LIGHT_BLUE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        ModBlockModelGenerators.generateDreamPortal(blockModelGenerators);

        blockModelGenerators.createTrivialCube(ModBlocks.ECHO_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.ECHO_MAGMA);
        blockModelGenerators.createTrivialCube(ModBlocks.REINFORCED_OBSIDIAN);
        blockModelGenerators.createTrivialCube(ModBlocks.DREAMED_OBSIDIAN);
        blockModelGenerators.createTrivialCube(ModBlocks.DREAMED_POWERED_OBSIDIAN);

        blockModelGenerators.createCropBlock(ModBlocks.STRANGE_BEETROOT_CROP, StrangeBeetrootCropBlock.AGE, 0,1,2,3);
        blockModelGenerators.createCropBlock(ModBlocks.FLOWTAREM_CROP, FlowtaremCropBlock.AGE, 0,1,2,3);
        blockModelGenerators.createCropBlock(ModBlocks.DREAM_FLOWER, DreamFlowerBlock.AGE, 0,1,2,3,4,5,6,7);
        blockModelGenerators.createCrossBlock(ModBlocks.AMETHYST_SWEET_BERRIES_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                AmethystSweetBerriesBushBlock.AGE, 0,1,2,3);
        ModBlockModelGenerators.generateEchoVines(blockModelGenerators);
        //blockModelGenerators.createGrowingPlant();

        Identifier lampOffIdentifier = TexturedModel.CUBE.create(ModBlocks.OBSIDIAN_REDSTONE_LAMP, blockModelGenerators.modelOutput);
        Identifier lampOnIdentifier = blockModelGenerators.createSuffixedVariant(ModBlocks.OBSIDIAN_REDSTONE_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.OBSIDIAN_REDSTONE_LAMP)
                .with(BlockModelGenerators.createBooleanModelDispatch(ObsidianRedstoneLampBlock.LIT,
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOnIdentifier)).build()),
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOffIdentifier)).build()))));


        ModBlockModelGenerators.generateDreamPetals(blockModelGenerators, ModBlocks.DREAM_PETALS_GREEN);
        ModBlockModelGenerators.generateDreamPetals(blockModelGenerators, ModBlocks.DREAM_PETALS_BLUE);
        ModBlockModelGenerators.generateDreamPetals(blockModelGenerators, ModBlocks.DREAM_PETALS_LIGHT_BLUE);
        ModBlockModelGenerators.generateDreamPetals(blockModelGenerators, ModBlocks.DREAM_PETALS_PURPLE);

        ModBlockModelGenerators.generateDreamEchoStatue(blockModelGenerators, ModBlocks.DREAM_ECHO_STATUE);

        for (DyeColor color : DyeColor.values()) {
            ModBlockModelGenerators.createReinforcedShulkerBox(blockModelGenerators, ModBlocks.REINFORCED_SHULKER_BOXES.get(color), color);
        }
        ModBlockModelGenerators.createReinforcedShulkerBox(blockModelGenerators, ModBlocks.REINFORCED_SHULKER_BOX, null);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.ECHO_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FUSED_ECHO_SHARD, ModelTemplates.FLAT_ITEM);
        createRecoveryShardModels(itemModelGenerators); // Generating Recovery Shard Models
        itemModelGenerators.generateFlatItem(ModItems.ECHO_SCRAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_INGOT, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.COBBLER, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.ECHO_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateSpear(ModItems.ECHO_SPEAR);

        itemModelGenerators.createFlatItemModel(ModItems.ECHO_BOW, ModelTemplates.BOW);
        itemModelGenerators.generateBow(ModItems.ECHO_BOW);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_ARROW, ModelTemplates.FLAT_ITEM);


        itemModelGenerators.generateFlatItem(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateTrimmableItem(ModItems.ECHO_HELMET, ModArmorMaterials.ECHO_KEY,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ECHO_CHESTPLATE, ModArmorMaterials.ECHO_KEY,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ECHO_LEGGINGS, ModArmorMaterials.ECHO_KEY,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ECHO_BOOTS, ModArmorMaterials.ECHO_KEY,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerators.generateFlatItem(ModItems.ECHO_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ECHO_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.ECHOBERRY, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.STRANGE_BEETROOT, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.FLOWTAREM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RESIN_FLOWTAREM, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_SUGAR_CANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_CHORUS_FRUIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_CARROT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_APPLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_POTATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_BEETROOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_STRANGE_BEETROOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_SWEET_BERRIES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_AMETHYST_SWEET_BERRIES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_GLOW_BERRIES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_ECHO_BERRIES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_FLOWTAREM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUNCH_OF_RESIN_FLOWTAREM, ModelTemplates.FLAT_ITEM);


        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_GREEN.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_BLUE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_LIGHT_BLUE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_PURPLE.asItem(), ModelTemplates.FLAT_ITEM);

        itemModelGenerators.declareCustomModelItem(ModItems.EXTENDER);
        itemModelGenerators.declareCustomModelItem(ModItems.EXTENDER_AMETHYST);
        itemModelGenerators.declareCustomModelItem(ModItems.EXTENDER_ECHO);

        itemModelGenerators.generateFlatItem(ModItems.ONLY_ONCE_MORE_MUSIC_DISC, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DUSK_TO_DAWN_MUSIC_DISC, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BROKEN_ETERNITY_MUSIC_DISC, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.DREAM_GREEN_WOOD_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_GREEN_WOOD_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_PURPLE_WOOD_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_PURPLE_WOOD_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_BLUE_WOOD_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_BLUE_WOOD_HANGING_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_LIGHT_BLUE_WOOD_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, ModelTemplates.FLAT_ITEM);

    }

    public static void createRecoveryShardModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked right = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(ModItems.RECOVERY_SHARD, "_right", ModelTemplates.FLAT_ITEM)
        );

        ItemModel.Unbaked wrong = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(ModItems.RECOVERY_SHARD, "_wrong", ModelTemplates.FLAT_ITEM)
        );

        itemModelGenerators.itemModelOutput.accept(ModItems.RECOVERY_SHARD,ItemModelUtils.select(
                        new ComponentContents<>(ModDataComponents.IS_ACTUAL), wrong,
                        ItemModelUtils.when(false, wrong), ItemModelUtils.when(true, right)
                )
        );
    }
}
