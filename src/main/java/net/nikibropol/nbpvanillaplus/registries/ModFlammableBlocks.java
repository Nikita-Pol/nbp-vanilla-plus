package net.nikibropol.nbpvanillaplus.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammableBLocks() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DREAM_GREEN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DREAM_GREEN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_PLANKS, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_LEAVES, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_WOOD_STAIRS, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DREAM_GREEN_WOOD_SLAB, 5, 5);
    }
}
