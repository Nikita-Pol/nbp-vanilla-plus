package net.nikibropol.nbpvanillaplus.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

public class ModStrippableBlocks {

    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(ModBlocks.DREAM_GREEN_LOG, ModBlocks.STRIPPED_DREAM_GREEN_LOG);
        StrippableBlockRegistry.register(ModBlocks.DREAM_GREEN_WOOD, ModBlocks.STRIPPED_DREAM_GREEN_WOOD);
    }
}
