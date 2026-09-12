package net.nikibropol.nbpvanillaplus.datagen;

import com.mojang.math.Quadrant;
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
        blockModelGenerators.createTrivialCube(ModBlocks.ECHO_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.ECHO_MAGMA);
        blockModelGenerators.createTrivialCube(ModBlocks.REINFORCED_OBSIDIAN);

        blockModelGenerators.createCropBlock(ModBlocks.STRANGE_BEETROOT_CROP, StrangeBeetrootCropBlock.AGE, 0,1,2,3);
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


        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_GREEN.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_BLUE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_LIGHT_BLUE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.DREAM_PETALS_PURPLE.asItem(), ModelTemplates.FLAT_ITEM);

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
