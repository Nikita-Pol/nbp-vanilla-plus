package net.nikibropol.nbpvanillaplus.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    // PF has CF
    // CF Placed down in the world
    // How placed, how many placed, where placed.

    public static final ResourceKey<PlacedFeature> DREAM_GREEN_WOOD_PLACED_KEY = registerKey("dream_green_wood_placed");
    public static final ResourceKey<PlacedFeature> DREAM_PURPLE_WOOD_PLACED_KEY = registerKey("dream_purple_wood_placed");
    public static final ResourceKey<PlacedFeature> DREAM_BLUE_WOOD_PLACED_KEY = registerKey("dream_blue_wood_placed");
    public static final ResourceKey<PlacedFeature> DREAM_LIGHT_BLUE_WOOD_PLACED_KEY = registerKey("dream_light_blue_wood_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, DREAM_GREEN_WOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DREAM_GREEN_WOOD_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(12),
                        ModBlocks.DREAM_GREEN_SAPLING));
        register(context, DREAM_PURPLE_WOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DREAM_PURPLE_WOOD_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(12),
                        ModBlocks.DREAM_PURPLE_SAPLING));
        register(context, DREAM_BLUE_WOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DREAM_BLUE_WOOD_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(12),
                        ModBlocks.DREAM_BLUE_SAPLING));
        register(context, DREAM_LIGHT_BLUE_WOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DREAM_LIGHT_BLUE_WOOD_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(12),
                        ModBlocks.DREAM_LIGHT_BLUE_SAPLING));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
