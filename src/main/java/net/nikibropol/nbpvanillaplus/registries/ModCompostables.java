package net.nikibropol.nbpvanillaplus.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class ModCompostables {
    public static void registerCompostables(){
        CompostableRegistry.INSTANCE.add(ModItems.ECHOBERRY, 0.4f);
        CompostableRegistry.INSTANCE.add(ModItems.AMETHYST_SWEET_BERRIES, 0.4f);
        CompostableRegistry.INSTANCE.add(ModItems.STRANGE_BEETROOT, 0.95f);
        CompostableRegistry.INSTANCE.add(ModItems.FLOWTAREM, 0.6f);
        CompostableRegistry.INSTANCE.add(ModItems.STRANGE_BEETROOT_SEEDS, 0.55f);
        CompostableRegistry.INSTANCE.add(ModItems.FLOWTAREM_SEEDS, 0.35f);
        CompostableRegistry.INSTANCE.add(ModItems.DREAM_SEEDS, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.DREAM_PETALS_GREEN, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.DREAM_PETALS_BLUE, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.DREAM_PETALS_PURPLE, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.DREAM_PETALS_LIGHT_BLUE, 0.3f);
    }
}
