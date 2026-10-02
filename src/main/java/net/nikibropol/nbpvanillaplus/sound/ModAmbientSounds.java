package net.nikibropol.nbpvanillaplus.sound;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;

public class ModAmbientSounds {

    public static void registerAmbientSounds() {
        register("they_still_wait_you", ModSounds.THEY_STILL_WAIT_YOU,
                Biomes.PLAINS, Biomes.SNOWY_PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.DEEP_DARK);

        register("break_time", ModSounds.BREAK_TIME,
                ModBiomes.DREAM_BLUE);
        register("lets_be_happy", ModSounds.LETS_BE_HAPPY,
                ModBiomes.DREAM_PURPLE);
        register("after_all", ModSounds.AFTER_ALL,
                ModBiomes.DREAM_LIGHT_BLUE);
        register("breathe_blur", ModSounds.BREATHE_BLUR,
                ModBiomes.DREAM_LIGHT_BLUE);
        register("evening", ModSounds.EVENING,
                ModBiomes.DREAM_PURPLE);
        register("informated", ModSounds.INFORMATED,
                ModBiomes.DREAM_LIGHT_BLUE);
        register("memories", ModSounds.MEMORIES,
                ModBiomes.DREAM_GREEN);
        register("nebula", ModSounds.NEBULA,
                ModBiomes.DREAM_BLUE);
        register("restart", ModSounds.RESTART,
                ModBiomes.DREAM_GREEN);
        register("still_there", ModSounds.STILL_THERE,
                ModBiomes.DREAM_BLUE);
        register("times_up", ModSounds.TIMES_UP,
                ModBiomes.DREAM_PURPLE);
        register("ambient_broken_eternity", ModSounds.AMBIENT_BROKEN_ETERNITY,
                ModBiomes.DREAM_BLUE, ModBiomes.DREAM_GREEN, ModBiomes.DREAM_PURPLE, ModBiomes.DREAM_LIGHT_BLUE);
        register("nameless_echoes", ModSounds.NAMELESS_ECHOES,
                ModBiomes.DREAM_GREEN);
    }

    @SafeVarargs
    private static void register(String id, Holder.Reference<SoundEvent> soundEvent,
                                 ResourceKey<Biome>... biomes) {
        BiomeModifications.create(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, id))
                .add(ModificationPhase.ADDITIONS,BiomeSelectors.includeByKey(biomes),context ->
                                context.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(soundEvent)));
    }
}