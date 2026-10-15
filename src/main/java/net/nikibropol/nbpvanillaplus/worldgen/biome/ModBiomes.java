package net.nikibropol.nbpvanillaplus.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.nikibropol.nbpvanillaplus.worldgen.ModPlacedFeatures;

public class ModBiomes {
    public static final ResourceKey<Biome> DREAM_GREEN = key("dreamscape_green");
    public static final ResourceKey<Biome> DREAM_BLUE = key("dreamscape_blue");
    public static final ResourceKey<Biome> DREAM_LIGHT_BLUE = key("dreamscape_light_blue");
    public static final ResourceKey<Biome> DREAM_PURPLE = key("dreamscape_purple");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {

        context.register(DREAM_GREEN,      Dream(context, 0x5FA87A, 0x4E9668, 0x3F8F80, 0x1F4337, 0x0F2A28, ModPlacedFeatures.DREAM_GREEN_WOOD_PLACED_KEY));
        context.register(DREAM_BLUE,       Dream(context, 0x4F6FC8, 0x3F5CB5, 0x34509F, 0x1D2A50, 0x0E1533, ModPlacedFeatures.DREAM_BLUE_WOOD_PLACED_KEY));
        context.register(DREAM_LIGHT_BLUE, Dream(context, 0x6FBBDD, 0x5AA8CC, 0x4F9CC8, 0x28485F, 0x101F3A, ModPlacedFeatures.DREAM_LIGHT_BLUE_WOOD_PLACED_KEY));
        context.register(DREAM_PURPLE,     Dream(context, 0x9673D0, 0x8262BC, 0x7452B0, 0x2E2150, 0x160E33, ModPlacedFeatures.DREAM_PURPLE_WOOD_PLACED_KEY));

    }


    private static int argb(int rgb) { return 0xFF000000 | rgb; }

    private static Biome Dream(BootstrapContext<Biome> context, int grass, int leaves, int water, int fog, int sky, ResourceKey<PlacedFeature> dreamWoodPlacedKey) {
        var placed = context.lookup(Registries.PLACED_FEATURE);
        var carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placed, carvers);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, dreamWoodPlacedKey);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5f)
                .downfall(0.0f)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(water)
                        .grassColorOverride(grass)
                        .foliageColorOverride(leaves)
                        .build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, argb(fog))
                .setAttribute(EnvironmentAttributes.SKY_COLOR, argb(sky))
                .setAttribute(EnvironmentAttributes.CLOUD_COLOR, argb(0x2E3350))
                .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 4.0f)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 64.0f)
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

    public static Biome paleGarden(HolderGetter<PlacedFeature> placed,
                                   HolderGetter<ConfiguredWorldCarver<?>> carvers, FogProfile fog) {
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobs);

        BiomeGenerationSettings.Builder genetationSettings = new BiomeGenerationSettings.Builder(placed, carvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(genetationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genetationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genetationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genetationSettings);
        BiomeDefaultFeatures.addDefaultSprings(genetationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(genetationSettings);

        genetationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PALE_GARDEN_VEGETATION);
        genetationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PALE_MOSS_PATCH);
        genetationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PALE_GARDEN_FLOWERS);
        BiomeDefaultFeatures.addDefaultOres(genetationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(genetationSettings);
        genetationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_PALE_GARDEN);
        BiomeDefaultFeatures.addForestGrass(genetationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genetationSettings, true);

        var attributes = EnvironmentAttributeMap.builder()
                .set(EnvironmentAttributes.SKY_COLOR, 0x7F8A7F)
                .set(EnvironmentAttributes.FOG_COLOR, -8292496)
                .set(EnvironmentAttributes.WATER_FOG_COLOR, -11179648)
                .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.EMPTY)
                .set(EnvironmentAttributes.MUSIC_VOLUME, 0.0F);
        fog.apply(attributes);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.8F)
                .putAttributes(attributes.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(7768221)
                        .grassColorOverride(7832178)
                        .foliageColorOverride(8883574)
                        .dryFoliageColorOverride(10528412)
                        .build())
                .mobSpawnSettings(mobs.build())
                .generationSettings(genetationSettings.build())
                .build();
    }
}
