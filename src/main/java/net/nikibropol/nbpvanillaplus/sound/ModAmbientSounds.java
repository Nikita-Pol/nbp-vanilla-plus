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

import java.util.*;

public class ModAmbientSounds {

    public record Playlist(Holder.Reference<SoundEvent> event, List<String> files, List<String> vanilla) {}

    private static final Map<ResourceKey<Biome>, List<String>> TRACKS = new LinkedHashMap<>();
    private static final Map<ResourceKey<Biome>, List<String>> VANILLA = new HashMap<>();
    private static final Set<ResourceKey<Biome>> NO_GAME_MUSIC = new HashSet<>();
    private static final Map<ResourceKey<Biome>, Holder.Reference<SoundEvent>> EVENTS = new LinkedHashMap<>();

    public static void registerAmbientSounds() {
        defineMusic();

        for (ResourceKey<Biome> biome : TRACKS.keySet()) {
            String name = "music_" + biome.identifier().getPath();
            Holder.Reference<SoundEvent> event = ModSounds.registerSoundEventAsHolder(name);
            EVENTS.put(biome, event);
            register(name, event, biome);
        }
        BiomeModifications.create(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "pale_garden_music_volume"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(Biomes.PALE_GARDEN), context ->
                        context.getAttributes().set(EnvironmentAttributes.MUSIC_VOLUME, 0.5F));
    }

    private static void defineMusic() {
        track("they_still_wait_you", Biomes.PLAINS, Biomes.SNOWY_PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.DEEP_DARK);
        track("what_are_you_waiting_for",
                Biomes.CHERRY_GROVE, Biomes.LUSH_CAVES, Biomes.BEACH, Biomes.SNOWY_BEACH, Biomes.RIVER,
                Biomes.OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_OCEAN, Biomes.FROZEN_OCEAN,
                Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.MUSHROOM_FIELDS);

        track("break_time", ModBiomes.DREAM_BLUE);
        track("lets_be_happy", ModBiomes.DREAM_PURPLE);
        track("after_all", ModBiomes.DREAM_LIGHT_BLUE);
        track("breathe_blur", ModBiomes.DREAM_LIGHT_BLUE);
        track("evening", ModBiomes.DREAM_PURPLE);
        track("informated", ModBiomes.DREAM_LIGHT_BLUE);
        track("memories", ModBiomes.DREAM_GREEN);
        track("nebula", ModBiomes.DREAM_BLUE);
        track("restart", ModBiomes.DREAM_GREEN);
        track("still_there", ModBiomes.DREAM_BLUE);
        track("times_up", ModBiomes.DREAM_PURPLE);
        track("nameless_echoes", ModBiomes.DREAM_GREEN);
        track("ambient_broken_eternity",
                ModBiomes.DREAM_BLUE, ModBiomes.DREAM_GREEN, ModBiomes.DREAM_PURPLE, ModBiomes.DREAM_LIGHT_BLUE);

        track("ambient_sleeping_forest",
                ModBiomes.DREAM_GREEN, ModBiomes.DREAM_PURPLE, ModBiomes.DREAM_BLUE, ModBiomes.DREAM_LIGHT_BLUE,
                Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.FLOWER_FOREST, Biomes.WINDSWEPT_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.CHERRY_GROVE, Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.SWAMP, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.SNOWY_TAIGA, Biomes.PALE_GARDEN, Biomes.JUNGLE,
                Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.MANGROVE_SWAMP, Biomes.SAVANNA,
                Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA);

        track("a_walk_around_the_lake", Biomes.PALE_GARDEN, Biomes.DEEP_DARK, Biomes.SWAMP, Biomes.MANGROVE_SWAMP);
        track("across_the_fog_and_silence", Biomes.PALE_GARDEN, Biomes.DEEP_DARK);
        track("the_traitor_is_still_among_us", Biomes.PALE_GARDEN, Biomes.DEEP_DARK, Biomes.DRIPSTONE_CAVES);
        track("they_know_where_you_are", Biomes.PALE_GARDEN, Biomes.DEEP_DARK);

        //=============================================================================================
        // Vanilla music recovery after set ModSounds (Check event name in SoundEvents.java)
        vanilla("music.overworld.deep_dark", Biomes.DEEP_DARK);
        vanilla("music.overworld.swamp", Biomes.SWAMP, Biomes.MANGROVE_SWAMP);
        vanilla("music.overworld.dripstone_caves", Biomes.DRIPSTONE_CAVES);
        vanilla("music.overworld.lush_caves", Biomes.LUSH_CAVES);
        vanilla("music.overworld.cherry_grove", Biomes.CHERRY_GROVE);
        vanilla("music.overworld.forest", Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
        vanilla("music.overworld.flower_forest", Biomes.FLOWER_FOREST);
        vanilla("music.overworld.jungle", Biomes.JUNGLE);
        vanilla("music.overworld.bamboo_jungle", Biomes.BAMBOO_JUNGLE);
        vanilla("music.overworld.sparse_jungle", Biomes.SPARSE_JUNGLE);
        vanilla("music.overworld.old_growth_taiga", Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA);

        //=============================================================================================================
        // Custom Biomes Music
        noGameMusic(ModBiomes.DREAM_BLUE, ModBiomes.DREAM_PURPLE, ModBiomes.DREAM_LIGHT_BLUE, ModBiomes.DREAM_GREEN,
                Biomes.PALE_GARDEN);
    }


    public static List<Playlist> playlists() {
        List<Playlist> result = new ArrayList<>();
        EVENTS.forEach((biome, event) -> {
            List<String> vanilla = new ArrayList<>();
            if (!NO_GAME_MUSIC.contains(biome)) vanilla.add("music.game");
            vanilla.addAll(VANILLA.getOrDefault(biome, List.of()));
            result.add(new Playlist(event, TRACKS.get(biome), vanilla));
        });
        return result;
    }

    @SafeVarargs
    private static void track(String file, ResourceKey<Biome>... biomes) {
        for (ResourceKey<Biome> biome : biomes) {
            TRACKS.computeIfAbsent(biome, resourceKey -> new ArrayList<>()).add(file);
        }
    }

    @SafeVarargs
    private static void vanilla(String event, ResourceKey<Biome>... biomes) {
        for (ResourceKey<Biome> biome : biomes) {
            VANILLA.computeIfAbsent(biome, resourceKey -> new ArrayList<>()).add(event);
        }
    }

    @SafeVarargs
    private static void noGameMusic(ResourceKey<Biome>... biomes) {
        NO_GAME_MUSIC.addAll(Arrays.asList(biomes));
    }

    @SafeVarargs
    private static void register(String id, Holder.Reference<SoundEvent> soundEvent,
                                 ResourceKey<Biome>... biomes) {
        BiomeModifications.create(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, id))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(biomes), context ->
                        context.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(soundEvent)));
    }
}