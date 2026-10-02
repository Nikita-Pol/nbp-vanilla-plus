package net.nikibropol.nbpvanillaplus.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class ModLootTableModifiers {
    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder,
                                        LootTableSource source, HolderLookup.Provider provider){

        // This modifies vanilla sniffer loot table
        if (key.identifier().equals(Identifier.withDefaultNamespace("gameplay/sniffer_digging"))) {
            builder.modifyPools(poolBuilder ->
                    poolBuilder.add(LootItem.lootTableItem(ModItems.STRANGE_BEETROOT_SEEDS).build()));
        }

        // This targets all ancient city chest loot tables
        if(BuiltInLootTables.ANCIENT_CITY.equals(key)) {
            LootPool.Builder echoIngotPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.0001f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_INGOT))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
            builder.pool(echoIngotPoolBuilder.build());

            LootPool.Builder echoUpgradePoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.00001f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
            builder.pool(echoUpgradePoolBuilder.build());

            LootPool.Builder echoToolPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.000001f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_SWORD)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_PICKAXE)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_AXE)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_SHOVEL)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_HOE)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_SPEAR)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_HELMET)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_CHESTPLATE)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_LEGGINGS)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_BOOTS)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_HORSE_ARMOR)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))))
                    .add(LootItem.lootTableItem(ModItems.ECHO_NAUTILUS_ARMOR)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f))).build());
            builder.pool(echoToolPoolBuilder.build());

            LootPool.Builder echoFragmentPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(3))
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_FRAGMENT))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)).build());
            builder.pool(echoFragmentPoolBuilder.build());

            LootPool.Builder echoBerriesPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(3))
                    .when(LootItemRandomChanceCondition.randomChance(0.08f))
                    .add(LootItem.lootTableItem(ModItems.ECHOBERRY))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());
            builder.pool(echoBerriesPoolBuilder.build());

            LootPool.Builder echoArrowPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.08f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_ARROW))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 8f)).build());
            builder.pool(echoArrowPoolBuilder.build());

            LootPool.Builder echoBowPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.01f))
                    .add(LootItem.lootTableItem(ModItems.ECHO_BOW))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());
            builder.pool(echoBowPoolBuilder.build());

            LootPool.Builder fusedEchoShardPoolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.02f))
                    .add(LootItem.lootTableItem(ModItems.FUSED_ECHO_SHARD))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());
            builder.pool(fusedEchoShardPoolBuilder.build());

        }

        if (key.identifier().equals(Identifier.withDefaultNamespace("archaeology/ocean_ruin_warm"))) {
            builder.modifyPools(poolBuilder ->
                    poolBuilder.add(LootItem.lootTableItem(ModItems.DREAM_SEEDS)
                            .when(LootItemRandomChanceCondition.randomChance(0.1f)))
            );
        }

        if (key.identifier().equals(Identifier.withDefaultNamespace("archaeology/ocean_ruin_cold"))) {
            builder.modifyPools(poolBuilder ->
                    poolBuilder.add(LootItem.lootTableItem(ModItems.DREAM_SEEDS)
                            .when(LootItemRandomChanceCondition.randomChance(0.1f)))
            );
        }
    }
}
