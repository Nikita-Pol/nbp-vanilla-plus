package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.AmethystSweetBerriesBushBlock;
import net.nikibropol.nbpvanillaplus.block.custom.DreamFlowerBlock;
import net.nikibropol.nbpvanillaplus.block.custom.DreamPetalsBlock;
import net.nikibropol.nbpvanillaplus.block.custom.StrangeBeetrootCropBlock;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.tags.ModTags;
import org.lwjgl.system.macosx.MacOSXLibraryDL;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(ModBlocks.ECHO_BLOCK);
        dropSelf(ModBlocks.ECHO_MAGMA);
        dropSelf(ModBlocks.REINFORCED_OBSIDIAN);
        dropSelf(ModBlocks.OBSIDIAN_REDSTONE_LAMP);

        this.add(ModBlocks.STRANGE_BEETROOT_CROP, this.createCropDrops(ModBlocks.STRANGE_BEETROOT_CROP, ModItems.STRANGE_BEETROOT,
                ModItems.STRANGE_BEETROOT_SEEDS, LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRANGE_BEETROOT_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrangeBeetrootCropBlock.AGE, StrangeBeetrootCropBlock.MAX_AGE))));

        this.add(ModBlocks.DREAM_FLOWER, LootTable.lootTable()
                .withPool(this.applyExplosionDecay(ModBlocks.DREAM_FLOWER, LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DREAM_SEEDS))))
                .withPool(this.applyExplosionDecay(ModBlocks.DREAM_FLOWER, LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DREAM_FLOWER)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(DreamFlowerBlock.AGE, DreamFlowerBlock.MAX_AGE)))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_GREEN).setWeight(1))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_BLUE).setWeight(1))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                            .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_PURPLE).setWeight(1))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                            .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_LIGHT_BLUE).setWeight(1))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                            .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
                .withPool(this.applyExplosionDecay(ModBlocks.DREAM_FLOWER, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DREAM_FLOWER)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(DreamFlowerBlock.AGE, DreamFlowerBlock.MAX_AGE)))
                        .add(LootItem.lootTableItem(ModItems.DREAM_SEEDS)
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(
                                        this.registries, 0.125F, 0.0625F))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))));

        this.add(ModBlocks.AMETHYST_SWEET_BERRIES_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.AMETHYST_SWEET_BERRIES_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(AmethystSweetBerriesBushBlock.AGE, 3)))
                                .add(LootItem.lootTableItem(ModItems.AMETHYST_SWEET_BERRIES)).apply(SetItemCountFunction
                                        .setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))
                                .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition
                                        .hasBlockStateProperties(ModBlocks.AMETHYST_SWEET_BERRIES_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(AmethystSweetBerriesBushBlock.AGE, 2)))
                                .add(LootItem.lootTableItem(ModItems.AMETHYST_SWEET_BERRIES))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));


        /*this.add(ModBlocks.DREAM_PETALS_GREEN, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_GREEN))));
        this.add(ModBlocks.DREAM_PETALS_BLUE, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_BLUE))));
        this.add(ModBlocks.DREAM_PETALS_PURPLE, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_PURPLE))));
        this.add(ModBlocks.DREAM_PETALS_LIGHT_BLUE, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.DREAM_PETALS_LIGHT_BLUE))));*/
    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops){
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    public static void registerModLootTableModifiers() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
            if (key.identifier().equals(
                    Identifier.withDefaultNamespace("gameplay/sniffer_digging"))) {
                tableBuilder.modifyPools(poolBuilder ->
                        poolBuilder.add(LootItem.lootTableItem(ModItems.STRANGE_BEETROOT_SEEDS))
                );
            }
        });
        LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
            if (key.identifier().equals(
                    Identifier.withDefaultNamespace("archaeology/ocean_ruin_warm"))) {
                tableBuilder.modifyPools(poolBuilder ->
                        poolBuilder.add(LootItem.lootTableItem(ModItems.DREAM_SEEDS)
                                .when(LootItemRandomChanceCondition.randomChance(0.8F))
                ));
            }
            if (key.identifier().equals(
                    Identifier.withDefaultNamespace("archaeology/ocean_ruin_cold"))) {
                tableBuilder.modifyPools(poolBuilder ->
                        poolBuilder.add(LootItem.lootTableItem(ModItems.DREAM_SEEDS)
                                .when(LootItemRandomChanceCondition.randomChance(0.1F))
                ));
            }
        });
    }
}
