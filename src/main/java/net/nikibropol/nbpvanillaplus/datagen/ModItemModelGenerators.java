package net.nikibropol.nbpvanillaplus.datagen;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;
import net.minecraft.resources.Identifier;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {

    public ModItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    static void generateLightScannerModels(ItemModelGenerators itemModelGenerators) {
        List<RangeSelectItemModel.Entry> entries = new ArrayList<>();
        for (int lightLevel = 0; lightLevel < 16; lightLevel++) {
            Identifier levelModelId = createLightScannerModel(itemModelGenerators, "_level_" + lightLevel);
            entries.add(ItemModelUtils.override(ItemModelUtils.plainModel(levelModelId), lightLevel));
        }

        ItemModel.Unbaked scannerModel = ItemModelUtils.rangeSelect(
                new net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty(0),
                entries);

        itemModelGenerators.itemModelOutput.accept(ModItems.LIGHT_SCANNER, scannerModel);
    }

    private static Identifier createLightScannerModel(ItemModelGenerators itemModelGenerators, String suffix) {
        Identifier modelId = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "item/light_scanner_level" + suffix);
        ModelTemplates.FLAT_ITEM.create(modelId,
                TextureMapping.layer0(TextureMapping.getItemTexture(ModItems.LIGHT_SCANNER, suffix)),
                itemModelGenerators.modelOutput);
        return modelId;
    }

    static void generateRecoveryShardModels(ItemModelGenerators itemModelGenerators) {
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
