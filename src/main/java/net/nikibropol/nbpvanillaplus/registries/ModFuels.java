package net.nikibropol.nbpvanillaplus.registries;

import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class ModFuels {
    public static void registerFuels(){
        FuelValueEvents.BUILD.register(((builder, context) -> {
            //for future updates;
            //builder.add();
            //context.baseSmeltTime();
        }));
    }
}
