package net.nikibropol.nbpvanillaplus;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.resources.Identifier;

import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.client.ClockHUD;
import net.nikibropol.nbpvanillaplus.client.CoordinatesHUD;
import net.nikibropol.nbpvanillaplus.client.render.EchoArrowRenderer;
import net.nikibropol.nbpvanillaplus.creativemodetab.ModCreativeModeTabs;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import net.nikibropol.nbpvanillaplus.datagen.ModBlockLootTableProvider;
import net.nikibropol.nbpvanillaplus.entity.ModEntities;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.registries.ModCompostables;
import net.nikibropol.nbpvanillaplus.registries.ModFuels;
import net.nikibropol.nbpvanillaplus.stat.ModStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NBPVanillaPlus implements ModInitializer {
	public static final String MOD_ID = "nbp-vanilla-plus";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeTabs();
		CoordinatesHUD.register();
		EntityRendererRegistry.register(ModEntities.ECHO_ARROW, EchoArrowRenderer::new);
		ClockHUD.register();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();


		ModFuels.registerFuels();
		ModCompostables.registerCompostables();
		ModDataComponents.registerDataComponents();
		ModEntities.registerEntities();
		ModStats.registerStats();
		ModBlockLootTableProvider.registerModLootTableModifiers();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
