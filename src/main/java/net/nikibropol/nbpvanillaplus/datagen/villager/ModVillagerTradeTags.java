package net.nikibropol.nbpvanillaplus.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {

    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_4)
                .add(TagEntry.element(ModVillagerTrades.FARMER_4_BUNCH_OF_CHORUS_FRUITS_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_4_BUNCH_OF_SWEET_BERRIES_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_4_BUNCH_OF_GLOW_BERRIES_EMERALD.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_5)
                .add(TagEntry.element(ModVillagerTrades.FARMER_5_EMERALD_BLOCK_FLOWTAREM.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.MASON_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.MASON_1_EMERALD_COBBLER.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.WANDERING_TRADER_COMMON)
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_DIAMOND_STRANGE_BEETROOT.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.WANDERING_TRADER_UNCOMMON)
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_DIAMOND_BLOCK_FLOWTAREM_SEEDS.identifier()));

        getOrCreateRawBuilder(ModTags.Trades.DREAMER_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.DREAMER_1_EMERALD_BLOCK_ECHO_FRAGMENT.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_1_EMERALD_DREAM_SEEDS.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.DREAMER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.DREAMER_2_DIAMOND_ECHO_FRAGMENT.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_2_ECHO_FRAGMENT_ECHO_ARROW.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.DREAMER_LEVEL_3)
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_GREEN_DREAM_PETALS_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_PURPLE_DREAM_PETALS_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_BLUE_DREAM_PETALS_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_LIGHT_BLUE_DREAM_PETALS_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_BUNCH_OF_ECHO_BERRIES_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_3_BUNCH_OF_AMETHYST_SWEET_BERRIES_EMERALD.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.DREAMER_LEVEL_4)
                .add(TagEntry.element(ModVillagerTrades.DREAMER_4_RESIN_CLUMP_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_4_ECHO_SCRAP_SHULKER_SHELL.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_4_ECHO_SCRAP_HEAVY_CORE.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.DREAMER_LEVEL_5)
                .add(TagEntry.element(ModVillagerTrades.DREAMER_5_ECHO_SHARD_BROKEN_ETERNITY_MUSIC_DISC.identifier()))
                .add(TagEntry.element(ModVillagerTrades.DREAMER_5_ECHO_INGOT_DREAM_ECHO_STATUE.identifier()));
    }
}
