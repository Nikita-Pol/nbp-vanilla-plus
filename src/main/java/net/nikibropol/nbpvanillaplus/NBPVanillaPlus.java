package net.nikibropol.nbpvanillaplus;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.creativemodetab.ModCreativeModeTabs;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NBPVanillaPlus implements ModInitializer {
	public static final String MOD_ID = "nbp-vanilla-plus";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
