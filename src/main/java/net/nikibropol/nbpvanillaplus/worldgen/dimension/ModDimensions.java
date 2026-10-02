package net.nikibropol.nbpvanillaplus.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.*;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> DREAMSCAPE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dreamscape"));
    public static final ResourceKey<Level> DREAMSCAPE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dreamscape"));
    public static final ResourceKey<DimensionType> DREAMSCAPE_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dreamscape_type"));
    public static final ResourceKey<NoiseGeneratorSettings> DREAMSCAPE_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dreamscape"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var blocks = context.lookup(Registries.BLOCK);

        context.register(DREAMSCAPE_TYPE, new DimensionType(
                false,
                false,
                false,
                false,
                0.5f,
                0,
                256,
                256,
                blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD),
                0.2f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder().set(EnvironmentAttributes.FOG_COLOR, 0xFF27386B)
                        .set(EnvironmentAttributes.SKY_COLOR, 0xFF0E1533)
                        .set(EnvironmentAttributes.SKY_LIGHT_FACTOR, 0.1f)
                        .set(EnvironmentAttributes.SKY_LIGHT_COLOR, 0xFF6C78C8)
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0xFF6C78C8)
                        .set(EnvironmentAttributes.CLOUD_COLOR, 0xFF2E3350)
                        .set(EnvironmentAttributes.CLOUD_HEIGHT, 320F)
                        .set(EnvironmentAttributes.STAR_BRIGHTNESS, 0.8f)
                        .set(EnvironmentAttributes.SUN_ANGLE, 180.0f)
                        .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
                        .set(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK)
                        .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
                        .set(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, false)
                        .build(),
                HolderSet.empty(),
                Optional.empty()));
    }

    public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        var noises = context.lookup(Registries.NOISE);

        DensityFunction gradient = DensityFunctions.yClampedGradient(0, 128, 1.0, -1.0); // поверхня ~Y64
        DensityFunction hills = DensityFunctions.mul(
                DensityFunctions.noise(noises.getOrThrow(Noises.SURFACE), 0.5, 0.0),
                DensityFunctions.constant(0.12));
        DensityFunction mountains = DensityFunctions.mul(
                DensityFunctions.max(DensityFunctions.zero(),
                        DensityFunctions.add(
                                DensityFunctions.noise(noises.getOrThrow(Noises.CONTINENTALNESS), 2.0, 0.0),
                                DensityFunctions.constant(-0.25))),
                DensityFunctions.constant(1.2));

        DensityFunction finalDensity = DensityFunctions.interpolated(
                DensityFunctions.add(gradient, DensityFunctions.add(hills, mountains)));

        DensityFunction temperature = DensityFunctions.noise(noises.getOrThrow(Noises.TEMPERATURE), 1.0, 0.0);
        DensityFunction zero = DensityFunctions.zero();

        NoiseRouter router = new NoiseRouter(
                zero, zero, zero, zero,   // barrier, 2fluidFloodedness, fluidSpread, lava
                temperature,
                zero, zero, zero, zero, zero, // vegetation, continents, erosion, depth, ridges
                zero,                     // preliminary surface level
                finalDensity,
                zero, zero, zero);        // vein toggle, ridged, gap

        SurfaceRules.RuleSource surface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                        SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.DIRT.defaultBlockState())));

        context.register(DREAMSCAPE_NOISE, new NoiseGeneratorSettings(
                NoiseSettings.create(0, 256, 1, 2),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                router,
                surface,
                List.of(),
                0,
                true,
                false,
                false,
                false));
    }


    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseSettings = context.lookup(Registries.NOISE_SETTINGS);

        Climate.Parameter full = Climate.Parameter.span(-2f, 2f);

        Climate.Parameter t1 = Climate.Parameter.span(-2f, -0.2f);
        Climate.Parameter t2 = Climate.Parameter.span(-0.2f, 0.0f);
        Climate.Parameter t3 = Climate.Parameter.span(0.0f, 0.2f);
        Climate.Parameter t4 = Climate.Parameter.span(0.2f, 2f);

        NoiseBasedChunkGenerator generator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(List.of(
                        Pair.of(Climate.parameters(t1, full, full, full, full, full, 0f), biomes.getOrThrow(ModBiomes.DREAM_PURPLE)),
                        Pair.of(Climate.parameters(t2, full, full, full, full, full, 0f), biomes.getOrThrow(ModBiomes.DREAM_BLUE)),
                        Pair.of(Climate.parameters(t3, full, full, full, full, full, 0f), biomes.getOrThrow(ModBiomes.DREAM_LIGHT_BLUE)),
                        Pair.of(Climate.parameters(t4, full, full, full, full, full, 0f), biomes.getOrThrow(ModBiomes.DREAM_GREEN))
                ))),
                noiseSettings.getOrThrow(DREAMSCAPE_NOISE));

        context.register(DREAMSCAPE_KEY, new LevelStem(dimTypes.getOrThrow(DREAMSCAPE_TYPE), generator));
    }
}
