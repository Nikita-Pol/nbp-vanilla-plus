package net.nikibropol.nbpvanillaplus;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.nikibropol.nbpvanillaplus.datagen.*;
import net.nikibropol.nbpvanillaplus.datagen.villager.ModPOITags;
import net.nikibropol.nbpvanillaplus.datagen.villager.ModTradeSets;
import net.nikibropol.nbpvanillaplus.datagen.villager.ModVillagerTradeTags;
import net.nikibropol.nbpvanillaplus.datagen.villager.ModVillagerTrades;
import net.nikibropol.nbpvanillaplus.worldgen.ModConfiguredFeatures;
import net.nikibropol.nbpvanillaplus.worldgen.ModPlacedFeatures;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;
import net.nikibropol.nbpvanillaplus.worldgen.dimension.ModDimensions;
import net.nikibropol.nbpvanillaplus.worldgen.structures.ModStructures;

public class NBPVanillaPlusDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModDamageTypeTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModEquipmentAssetProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModPaintingsTagsProvider::new);
		pack.addProvider(ModSoundsProvider::new);
		pack.addProvider(ModVillagerTradeTags::new);
		pack.addProvider(ModPOITags::new);
		pack.addProvider(ModAtlasProvider::new);
		pack.addProvider(ModChestLootProvider::new);
        pack.addProvider(ModBiomeTagsProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.PAINTING_VARIANT, ModPaintings::bootstrap);
		registryBuilder.add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
		registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap);

		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
		registryBuilder.add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);
		registryBuilder.add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);
		registryBuilder.add(Registries.NOISE_SETTINGS, ModDimensions::bootstrapNoise);
		registryBuilder.add(Registries.TEMPLATE_POOL, ModStructures::bootstrapPools);
		registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrapStructures);
		registryBuilder.add(Registries.STRUCTURE_SET, ModStructures::bootstrapSets);
	}
}
