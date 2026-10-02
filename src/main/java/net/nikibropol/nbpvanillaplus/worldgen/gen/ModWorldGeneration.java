package net.nikibropol.nbpvanillaplus.worldgen.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.nikibropol.nbpvanillaplus.worldgen.ModPlacedFeatures;

public class ModWorldGeneration {

    public static void generateModWorldGen() {

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DREAM_GREEN_WOOD_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DREAM_PURPLE_WOOD_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DREAM_BLUE_WOOD_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DREAM_LIGHT_BLUE_WOOD_PLACED_KEY);

    }
}
