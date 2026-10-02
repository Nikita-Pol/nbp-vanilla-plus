package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootProvider extends SimpleFabricLootTableSubProvider {

    public static ResourceKey<LootTable> houseLoot(String color) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "chests/dream_" + color + "_house"));
    }

    public ModChestLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture, LootContextParamSets.CHEST);
    }

    private record HouseColor(String name, ItemLike petals, ItemLike log, ItemLike planks, ItemLike sapling, ItemLike wool) {}

    private static final List<HouseColor> COLORS = List.of(
            new HouseColor("green", ModBlocks.DREAM_PETALS_GREEN, ModBlocks.DREAM_GREEN_LOG,
                    ModBlocks.DREAM_GREEN_PLANKS, ModBlocks.DREAM_GREEN_SAPLING, Blocks.WOOL.green()),
            new HouseColor("purple", ModBlocks.DREAM_PETALS_PURPLE, ModBlocks.DREAM_PURPLE_LOG,
                    ModBlocks.DREAM_PURPLE_PLANKS, ModBlocks.DREAM_PURPLE_SAPLING, Blocks.WOOL.purple()),
            new HouseColor("blue", ModBlocks.DREAM_PETALS_BLUE, ModBlocks.DREAM_BLUE_LOG,
                    ModBlocks.DREAM_BLUE_PLANKS, ModBlocks.DREAM_BLUE_SAPLING, Blocks.WOOL.blue()),
            new HouseColor("light_blue", ModBlocks.DREAM_PETALS_LIGHT_BLUE, ModBlocks.DREAM_LIGHT_BLUE_LOG,
                    ModBlocks.DREAM_LIGHT_BLUE_PLANKS, ModBlocks.DREAM_LIGHT_BLUE_SAPLING, Blocks.WOOL.lightBlue()));

    private static LootPoolSingletonContainer.Builder<?> entry(ItemLike item, int weight, int min, int max) {
        var e = LootItem.lootTableItem(item).setWeight(weight);
        if (max > 1) e = e.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        return e;
    }

    private static LootPool.Builder rare(ItemLike item, float chance, int min, int max) {
        var e = LootItem.lootTableItem(item);
        if (max > 1) e = e.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(chance))
                .add(e);
    }

    public static LootTable.Builder addCommonLoot(LootTable.Builder table) {
        return table
                .withPool(rare(ModItems.EXTENDER, 0.05f, 1, 1))
                .withPool(rare(Items.ECHO_SHARD, 0.075f, 1, 2))
                .withPool(rare(ModItems.ECHO_SCRAP, 0.01f, 1, 1))
                .withPool(rare(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, 0.02f, 1, 1))
                .withPool(rare(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, 0.001f, 1, 1))
                .withPool(rare(ModItems.ECHO_INGOT, 0.0001f, 1, 1))
                .withPool(rare(ModBlocks.DREAMED_POWERED_OBSIDIAN, 0.1f, 1, 1))
                .withPool(rare(ModItems.FUSED_ECHO_SHARD, 0.15f, 1, 2));
    }

    private static LootPool.Builder mainPool(HouseColor houseColor) {
        return LootPool.lootPool().setRolls(UniformGenerator.between(4, 8))

                .add(entry(houseColor.petals(), 5, 1, 3))
                .add(entry(houseColor.log(), 4, 1, 3))
                .add(entry(houseColor.planks(), 5, 1, 3))
                .add(entry(houseColor.sapling(), 3, 1, 3))
                .add(entry(houseColor.wool(),1,1,3 ))

                .add(entry(ModItems.DREAM_SEEDS, 3, 1, 3))
                .add(entry(ModItems.ECHO_FRAGMENT, 1, 1, 2))
                .add(entry(Items.COPPER_NUGGET, 3, 1, 2))
                .add(entry(Items.IRON_NUGGET, 2, 1, 2))
                .add(entry(Items.GOLD_NUGGET, 2, 1, 2))
                .add(entry(ModBlocks.DREAMED_OBSIDIAN, 1, 1, 2))
                .add(entry(Items.COMPASS, 1, 1, 1))
                .add(entry(ModItems.ECHO_ARROW, 2, 1, 3))
                .add(entry(Items.AMETHYST_SHARD, 2, 1, 3))
                .add(entry(ModItems.AMETHYST_SWEET_BERRIES, 2, 1, 1));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        for (HouseColor c : COLORS) {
            output.accept(houseLoot(c.name()), addCommonLoot(LootTable.lootTable().withPool(mainPool(c))));
        }
    }
}