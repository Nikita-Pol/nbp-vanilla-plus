package net.nikibropol.nbpvanillaplus.worldgen.biome.Biomes;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.level.biome.Biomes;

public class PaleGardenMusicHandler {

    private static boolean wasInPaleGarden = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level == null || client.player == null) {
                wasInPaleGarden = false;
                return;
            }

            boolean inPale = client.level.getBiome(client.player.blockPosition()).is(Biomes.PALE_GARDEN);

            if (inPale && !wasInPaleGarden) {
                client.getMusicManager().stopPlaying();
            }
            wasInPaleGarden = inPale;
        });
    }
}
