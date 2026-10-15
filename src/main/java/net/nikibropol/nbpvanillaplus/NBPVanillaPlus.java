package net.nikibropol.nbpvanillaplus;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.Identifier;

import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.entity.ModBlockEntities;
import net.nikibropol.nbpvanillaplus.block.state.properties.ModWoodType;
import net.nikibropol.nbpvanillaplus.client.ClockHUD;
import net.nikibropol.nbpvanillaplus.client.CoordinatesHUD;
import net.nikibropol.nbpvanillaplus.effect.EchoArmorEffects;
import net.nikibropol.nbpvanillaplus.entity.client.EchoArrowRenderer;
import net.nikibropol.nbpvanillaplus.creativemodetab.ModCreativeModeTabs;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import net.nikibropol.nbpvanillaplus.effect.ModEffects;
import net.nikibropol.nbpvanillaplus.entity.custom.EchoCreaking;
import net.nikibropol.nbpvanillaplus.event.CreakingBuff;
import net.nikibropol.nbpvanillaplus.loot.ModLootTableModifiers;
import net.nikibropol.nbpvanillaplus.menu.ModMenuTypes;
import net.nikibropol.nbpvanillaplus.potion.ModPotions;
import net.nikibropol.nbpvanillaplus.recipe.ModRecipes;
import net.nikibropol.nbpvanillaplus.registries.*;
import net.nikibropol.nbpvanillaplus.sound.ModAmbientSounds;
import net.nikibropol.nbpvanillaplus.entity.ModEntities;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.sound.ModSounds;
import net.nikibropol.nbpvanillaplus.stat.ModStats;
import net.nikibropol.nbpvanillaplus.villager.ModVillagers;
import net.nikibropol.nbpvanillaplus.worldgen.dimension.ModPortals;
import net.nikibropol.nbpvanillaplus.worldgen.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nikibropol.nbpvanillaplus.entity.ModEntities.ECHO_CREAKING;

public class NBPVanillaPlus implements ModInitializer {
	public static final String MOD_ID = "nbp-vanilla-plus";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeTabs();

		CreakingBuff.register();


		ModItems.registerModItems();
		ModBlocks.registerBlockTypes();
		ModBlocks.registerModBlocks();

		ModWoodType.registerModWoodTypes();

		ModSounds.registerSounds();
		ModAmbientSounds.registerAmbientSounds();

		ModFuels.registerFuels();
		ModCompostables.registerCompostables();
		ModDataComponents.registerDataComponents();
		ModEntities.registerEntities();

		ModEffects.registerEffects();
		EchoArmorEffects.register();

		ModPotions.registerPotions();
		ModPotionRecipes.registerPotionRecipes();

		ModVillagers.register();

		ModMenuTypes.registerModMenuTypes();

		ModRecipes.registerModRecipes();

		ModWorldGeneration.generateModWorldGen();

		ModFlammableBlocks.registerFlammableBLocks();
		ModStrippableBlocks.registerStrippableBlocks();

		ModStats.registerStats();
		ModBlockEntities.registerBlockEntities();

		ModPortals.registerPortals();

		FabricDefaultAttributeRegistry.register(ECHO_CREAKING, EchoCreaking.createAttributes());

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);
		//ModBlockLootTableProvider.registerModLootTableModifiers();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
