package net.nikibropol.nbpvanillaplus.datagen.villager;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {

    public static final ResourceKey<VillagerTrade> FARMER_5_EMERALD_BLOCK_FLOWTAREM = createKey("farmer/5/emerald_block_flowtarem");
    public static final ResourceKey<VillagerTrade> FARMER_4_BUNCH_OF_SWEET_BERRIES_EMERALD = createKey("farmer/4/bunch_of_sweet_berries_emerald");
    public static final ResourceKey<VillagerTrade> FARMER_4_BUNCH_OF_GLOW_BERRIES_EMERALD = createKey("farmer/4/bunch_of_glow_berries_emerald");
    public static final ResourceKey<VillagerTrade> FARMER_4_BUNCH_OF_CHORUS_FRUITS_EMERALD = createKey("farmer/4/bunch_of_chorus_fruits_emerald");
    public static final ResourceKey<VillagerTrade> MASON_1_EMERALD_COBBLER = createKey("mason/1/emerald_cobbler");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_DIAMOND_BLOCK_FLOWTAREM_SEEDS = createKey("wandering_trader/diamond_block_flowtarem_seeds");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_DIAMOND_STRANGE_BEETROOT = createKey("wandering_trader/diamond_strange_beetroot");



    public static final ResourceKey<VillagerTrade> DREAMER_1_EMERALD_DREAM_SEEDS = createKey("dreamer/1/emerald_dream_seeds");
    public static final ResourceKey<VillagerTrade> DREAMER_1_EMERALD_BLOCK_ECHO_FRAGMENT = createKey("dreamer/1/emerald_block_echo_fragment");
    public static final ResourceKey<VillagerTrade> DREAMER_2_ECHO_FRAGMENT_ECHO_ARROW = createKey("dreamer/2/echo_fragment_echo_arrow");
    public static final ResourceKey<VillagerTrade> DREAMER_2_DIAMOND_ECHO_FRAGMENT = createKey("dreamer/2/diamond_echo_fragment");
    public static final ResourceKey<VillagerTrade> DREAMER_3_GREEN_DREAM_PETALS_EMERALD = createKey("dreamer/3/green_dream_petals_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_3_BLUE_DREAM_PETALS_EMERALD = createKey("dreamer/3/blue_dream_petals_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_3_PURPLE_DREAM_PETALS_EMERALD = createKey("dreamer/3/purple_dream_petals_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_3_LIGHT_BLUE_DREAM_PETALS_EMERALD = createKey("dreamer/3/light_blue_dream_petals_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_3_BUNCH_OF_ECHO_BERRIES_EMERALD = createKey("dreamer/3/bunch_of_echo_berries_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_3_BUNCH_OF_AMETHYST_SWEET_BERRIES_EMERALD = createKey("dreamer/3/bunch_of_amethyst_sweet_berries_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_4_RESIN_CLUMP_EMERALD = createKey("dreamer/4/resin_clump_emerald");
    public static final ResourceKey<VillagerTrade> DREAMER_4_ECHO_SCRAP_SHULKER_SHELL = createKey("dreamer/4/echo_scrap_shulker_shell");
    public static final ResourceKey<VillagerTrade> DREAMER_4_ECHO_SCRAP_HEAVY_CORE = createKey("dreamer/4/echo_scrap_heavy_core");
    public static final ResourceKey<VillagerTrade> DREAMER_5_ECHO_SHARD_BROKEN_ETERNITY_MUSIC_DISC = createKey("dreamer/5/echo_shard_broken_eternity_music_disc");
    public static final ResourceKey<VillagerTrade> DREAMER_5_ECHO_INGOT_DREAM_ECHO_STATUE = createKey("dreamer/5/echo_ingot_dream_echo_statue");

    public static void bootstrap(BootstrapContext<VillagerTrade> context){


        context.register(FARMER_5_EMERALD_BLOCK_FLOWTAREM,new VillagerTrade(
                new TradeCost(Items.EMERALD_BLOCK, 4),
                new ItemStackTemplate(ModItems.FLOWTAREM),
                12, 35, 0.05f,
                Optional.empty(), List.of()));
        context.register(FARMER_4_BUNCH_OF_CHORUS_FRUITS_EMERALD,new VillagerTrade(
                new TradeCost(ModItems.BUNCH_OF_CHORUS_FRUIT, 3),
                new ItemStackTemplate(Items.EMERALD),
                16, 20, 0.05f,
                Optional.empty(), List.of()));
        context.register(FARMER_4_BUNCH_OF_GLOW_BERRIES_EMERALD,new VillagerTrade(
                new TradeCost(ModItems.BUNCH_OF_GLOW_BERRIES, 3),
                new ItemStackTemplate(Items.EMERALD),
                16, 20, 0.05f,
                Optional.empty(), List.of()));
        context.register(FARMER_4_BUNCH_OF_SWEET_BERRIES_EMERALD,new VillagerTrade(
                new TradeCost(ModItems.BUNCH_OF_SWEET_BERRIES, 3),
                new ItemStackTemplate(Items.EMERALD),
                16, 20, 0.05f,
                Optional.empty(), List.of()));
        context.register(MASON_1_EMERALD_COBBLER,new VillagerTrade(
                new TradeCost(Items.EMERALD, 5),
                new ItemStackTemplate(ModItems.COBBLER),
                12, 5, 0.05f,
                Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_DIAMOND_BLOCK_FLOWTAREM_SEEDS,new VillagerTrade(
                new TradeCost(Items.DIAMOND_BLOCK, 2),
                new ItemStackTemplate(ModItems.FLOWTAREM_SEEDS),
                4, 25, 0.01f,
                Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_DIAMOND_STRANGE_BEETROOT,new VillagerTrade(
                new TradeCost(Items.DIAMOND, 4),
                new ItemStackTemplate(ModItems.STRANGE_BEETROOT),
                4, 10, 0.01f,
                Optional.empty(), List.of()));

        context.register(DREAMER_1_EMERALD_BLOCK_ECHO_FRAGMENT,new VillagerTrade(
                new TradeCost(Items.EMERALD_BLOCK, 21),
                new ItemStackTemplate(ModItems.ECHO_FRAGMENT),
                16, 2, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_1_EMERALD_DREAM_SEEDS,new VillagerTrade(
                new TradeCost(Items.EMERALD, 7),
                new ItemStackTemplate(ModItems.DREAM_SEEDS),
                12, 1, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_2_DIAMOND_ECHO_FRAGMENT,new VillagerTrade(
                new TradeCost(Items.DIAMOND, 6),
                new ItemStackTemplate(ModItems.ECHO_FRAGMENT),
                12, 4, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_2_ECHO_FRAGMENT_ECHO_ARROW,new VillagerTrade(
                new TradeCost(ModItems.ECHO_FRAGMENT, 1),
                new ItemStackTemplate(ModItems.ECHO_ARROW),
                12, 3, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_GREEN_DREAM_PETALS_EMERALD,new VillagerTrade(
                new TradeCost(ModBlocks.DREAM_PETALS_GREEN, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 10, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_BLUE_DREAM_PETALS_EMERALD,new VillagerTrade(
                new TradeCost(ModBlocks.DREAM_PETALS_BLUE, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 10, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_PURPLE_DREAM_PETALS_EMERALD,new VillagerTrade(
                new TradeCost(ModBlocks.DREAM_PETALS_PURPLE, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 10, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_LIGHT_BLUE_DREAM_PETALS_EMERALD,new VillagerTrade(
                new TradeCost(ModBlocks.DREAM_PETALS_LIGHT_BLUE, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 10, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_BUNCH_OF_ECHO_BERRIES_EMERALD,new VillagerTrade(
                new TradeCost(ModItems.BUNCH_OF_ECHO_BERRIES, 3),
                new ItemStackTemplate(Items.EMERALD),
                16, 20, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_3_BUNCH_OF_AMETHYST_SWEET_BERRIES_EMERALD,new VillagerTrade(
                new TradeCost(ModItems.BUNCH_OF_AMETHYST_SWEET_BERRIES, 3),
                new ItemStackTemplate(Items.EMERALD),
                16, 20, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_4_RESIN_CLUMP_EMERALD,new VillagerTrade(
                new TradeCost(Items.RESIN_CLUMP, 22),
                new ItemStackTemplate(Items.EMERALD),
                16, 25, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_4_ECHO_SCRAP_SHULKER_SHELL,new VillagerTrade(
                new TradeCost(ModItems.ECHO_SCRAP, 1),
                new ItemStackTemplate(Items.SHULKER_SHELL, 2),
                16, 35, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_4_ECHO_SCRAP_HEAVY_CORE,new VillagerTrade(
                new TradeCost(ModItems.ECHO_SCRAP, 8),
                new ItemStackTemplate(Items.HEAVY_CORE, 1),
                1, 50, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_5_ECHO_SHARD_BROKEN_ETERNITY_MUSIC_DISC,new VillagerTrade(
                new TradeCost(Items.ECHO_SHARD, 32),
                new ItemStackTemplate(ModItems.BROKEN_ETERNITY_MUSIC_DISC),
                1, 40, 0.05f,
                Optional.empty(), List.of()));
        context.register(DREAMER_5_ECHO_INGOT_DREAM_ECHO_STATUE,new VillagerTrade(
                new TradeCost(ModItems.ECHO_INGOT, 1),
                new ItemStackTemplate(ModBlocks.DREAM_ECHO_STATUE.asItem(), 1),
                1, 60, 0.05f,
                Optional.empty(), List.of()));

    }

    private static ResourceKey<VillagerTrade> createKey(String name){
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }
}
