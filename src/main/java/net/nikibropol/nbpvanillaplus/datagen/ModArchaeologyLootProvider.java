package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.nikibropol.nbpvanillaplus.datagen.ModLootHelper.*;

public class ModArchaeologyLootProvider extends SimpleFabricLootTableSubProvider {

    public static ResourceKey<LootTable> ruinLoot(String color) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "archaeology/dream_" + color + "_lake"));
    }

    public ModArchaeologyLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LootContextParamSets.ARCHAEOLOGY);
    }

    private static LootPoolSingletonContainer.Builder<?> single(ItemLike item, int weight) {
        return entry(item, weight, 1, 1);
    }
    private static LootPool.Builder fullPool(Color color) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(single(color.petals(), 6))
                .add(single(color.sapling(), 5))
                .add(single(color.disc_fragment(), 2))
                .add(single(color.dye(), 5))
                .add(single(color.candle(), 4))
                .add(single(ModItems.ECHO_FRAGMENT, 3))
                .add(single(ModBlocks.DREAMED_OBSIDIAN, 2))
                .add(single(Items.AMETHYST_SHARD, 3))
                .add(single(ModItems.DREAM_SEEDS, 5))
                .add(single(ModItems.FUSED_ECHO_SHARD, 2))
                .add(single(Items.ECHO_SHARD, 1))
                .add(single(ModItems.JUST_A_MEMORY_MUSIC_DISC, 1));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        for (Color color : COLORS) {
            output.accept(ruinLoot(color.name()), LootTable.lootTable()
                    .withPool(fullPool(color))
                    .withPool(rare(ModItems.ECHO_SCRAP, 0.03f, 1, 1))
                    .withPool(rare(ModItems.ECHO_INGOT, 0.001f, 1, 1)));
        }
    }
}
