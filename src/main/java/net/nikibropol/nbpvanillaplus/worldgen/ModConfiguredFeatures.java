package net.nikibropol.nbpvanillaplus.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

public class ModConfiguredFeatures {

    // CF => Features with Configuration
    // Tree --> height, what trunks etc etc...
    // How something looks like

    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_GREEN_WOOD_KEY = registerKey("dream_green_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_PURPLE_WOOD_KEY = registerKey("dream_purple_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_BLUE_WOOD_KEY = registerKey("dream_blue_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_LIGHT_BLUE_WOOD_KEY = registerKey("dream_light_blue_wood");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){

        register(context, DREAM_GREEN_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DREAM_GREEN_LOG),
                new StraightTrunkPlacer(3, 1, 5),
                BlockStateProvider.simple(ModBlocks.DREAM_GREEN_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());

        register(context, DREAM_PURPLE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DREAM_PURPLE_LOG),
                new BendingTrunkPlacer(4, 1, 2, 2, ConstantInt.of(1)),
                BlockStateProvider.simple(ModBlocks.DREAM_PURPLE_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());

        register(context, DREAM_BLUE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DREAM_BLUE_LOG),
                new StraightTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBlocks.DREAM_BLUE_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());

        register(context, DREAM_LIGHT_BLUE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DREAM_LIGHT_BLUE_LOG),
                new BendingTrunkPlacer(3, 2, 4, 4, ConstantInt.of(2)),
                BlockStateProvider.simple(ModBlocks.DREAM_LIGHT_BLUE_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
