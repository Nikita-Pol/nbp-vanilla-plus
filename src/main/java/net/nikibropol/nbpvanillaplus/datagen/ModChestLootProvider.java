package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.nikibropol.nbpvanillaplus.datagen.ModLootHelper.*;

public class ModChestLootProvider extends SimpleFabricLootTableSubProvider {

    public static ResourceKey<LootTable> houseLoot(String color) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "chests/dream_" + color + "_house"));
    }

    public static ResourceKey<LootTable> paleGardenLoot() {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "chests/pale_garden"));
    }
    public static ResourceKey<LootTable> sculkedHouseLoot() {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "chests/sculked_house"));
    }

    public ModChestLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture, LootContextParamSets.CHEST);
    }

    public static LootTable.Builder addDreamLoot(LootTable.Builder table) {
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

    public static LootTable.Builder addEchoLoot(LootTable.Builder table) {
        return table
                .withPool(rare(Items.ECHO_SHARD, 0.075f, 1, 2))
                .withPool(rare(ModItems.ECHO_SCRAP, 0.01f, 1, 1))
                .withPool(rare(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, 0.02f, 1, 1))
                .withPool(rare(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, 0.001f, 1, 1))
                .withPool(rare(ModItems.ECHO_INGOT, 0.0001f, 1, 1))
                .withPool(rare(ModBlocks.DREAMED_POWERED_OBSIDIAN, 0.1f, 1, 1))
                .withPool(rare(ModItems.FUSED_ECHO_SHARD, 0.15f, 1, 2));
    }

    private static LootPool.Builder mainPool(Color color) {
        return LootPool.lootPool().setRolls(UniformGenerator.between(4, 8))
                .add(entry(color.petals(), 5, 1, 7))
                .add(entry(color.log(), 4, 1, 2))
                .add(entry(color.planks(), 5, 1, 5))
                .add(entry(color.sapling(), 3, 1, 3))
                .add(entry(color.wool(), 2, 1, 3))
                .add(entry(color.disc_fragment(), 1, 1, 1))
                .add(entry(color.dye(), 3, 1, 3))
                .add(entry(color.candle(), 2, 1, 3))

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

    private static LootPool.Builder paleGardenPool() {
        return LootPool.lootPool().setRolls(UniformGenerator.between(4, 9))
                .add(entry(Items.RESIN_CLUMP, 10, 1, 6))
                .add(entry(ModItems.RESIN_ECHO_CLUMP, 1, 1, 3))
                .add(entry(Items.RESIN_BRICK, 6, 1, 3))
                .add(entry(Items.PALE_OAK_LOG, 6, 1, 3))
                .add(entry(Items.STRIPPED_PALE_OAK_LOG, 4, 1, 3))
                .add(entry(Items.PALE_OAK_PLANKS, 9, 1, 5))
                .add(entry(Items.PALE_OAK_SAPLING, 6, 1, 2))
                .add(entry(Items.PALE_MOSS_BLOCK, 8, 1, 3))
                .add(entry(Items.PALE_MOSS_CARPET, 7, 1, 4))
                .add(entry(Items.PALE_HANGING_MOSS, 5, 1, 2))
                .add(entry(Items.CLOSED_EYEBLOSSOM, 2, 1, 1))
                .add(entry(Items.OPEN_EYEBLOSSOM, 2, 1, 1))
                .add(entry(Items.ENDER_PEARL, 1, 1, 1))
                .add(entry(Items.IRON_NUGGET, 3, 1, 3))
                .add(entry(Items.GOLD_NUGGET, 4, 1, 4))
                .add(entry(Items.COPPER_NUGGET, 5, 1, 5))
                .add(entry(Items.COPPER_INGOT, 2, 1, 2))
                .add(entry(ModItems.ECHO_FRAGMENT, 1, 1, 2))
                .add(entry(ModItems.ECHO_ARROW, 2, 1, 3))
                .add(entry(ModItems.INTO_SILENCE_MUSIC_DISC, 1, 1,1));
    }
    private static LootPool.Builder sculkedHousePool() {
        return LootPool.lootPool().setRolls(UniformGenerator.between(1, 27))
                .add(entry(ModItems.BUNCH_OF_APPLE, 2, 1, 1))
                .add(entry(ModItems.FLOWTAREM, 1, 1, 3))
                .add(entry(ModItems.COBBLER, 2, 1, 1))
                .add(entry(ModBlocks.REINFORCED_OBSIDIAN, 1, 1, 3))
                .add(entry(Items.LEATHER, 6, 1, 3))
                .add(entry(Items.PAINTING, 2, 1, 1))
                .add(entry(Items.COBWEB, 4, 1, 4))
                .add(entry(Items.STRING, 3, 1, 4))
                .add(entry(Items.SCULK, 7, 1, 5))
                .add(entry(ModItems.ECHOBERRY, 2, 1, 3))
                .add(entry(Items.SCULK_VEIN, 4, 1, 3))
                .add(entry(Items.SCULK_SENSOR, 4, 1, 2))
                .add(entry(Items.SCULK_CATALYST, 3, 1, 1))
                .add(entry(ModBlocks.ECHO_MAGMA, 5, 1, 3))
                .add(entry(Items.ENDER_PEARL, 1, 1, 1))
                .add(entry(Items.IRON_NUGGET, 5, 1, 5))
                .add(entry(Items.GOLD_NUGGET, 7, 1, 7))
                .add(entry(Items.COPPER_NUGGET, 8, 1, 8))
                .add(entry(Items.AMETHYST_SHARD, 5, 1, 4))
                .add(entry(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, 1, 1, 1))
                .add(entry(Items.DIAMOND, 2, 1, 2))
                .add(entry(Items.IRON_INGOT, 4, 1, 3))
                .add(entry(Items.GOLD_INGOT, 5, 1, 4))
                .add(entry(Items.COPPER_INGOT, 7, 1, 5))
                .add(entry(Items.ECHO_SHARD, 1, 1, 3))
                .add(entry(ModItems.ECHO_FRAGMENT, 2, 1, 6))
                .add(entry(ModItems.ECHO_ARROW, 3, 1, 3))
                .add(entry(ModItems.ONLY_ONCE_MORE_MUSIC_DISC, 1, 1,1))
                .add(entry(ModItems.DUSK_TO_DAWN_MUSIC_DISC, 2, 1,1));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        for (Color color : COLORS) {
            output.accept(houseLoot(color.name()), addDreamLoot(LootTable.lootTable().withPool(mainPool(color))));
        }

        output.accept(paleGardenLoot(), addEchoLoot(LootTable.lootTable().withPool(paleGardenPool())));
        output.accept(sculkedHouseLoot(), addEchoLoot(LootTable.lootTable().withPool(sculkedHousePool())));
    }
}