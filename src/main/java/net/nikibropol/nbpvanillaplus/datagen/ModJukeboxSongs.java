package net.nikibropol.nbpvanillaplus.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.sound.ModSounds;

public class ModJukeboxSongs {
    public static final ResourceKey<JukeboxSong> ONLY_ONCE_MORE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"only_once_more"));
    public static final ResourceKey<JukeboxSong> DUSK_TO_DAWN_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"dusk_to_dawn"));
    public static final ResourceKey<JukeboxSong> BROKEN_ETERNITY_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"broken_eternity"));
    public static final ResourceKey<JukeboxSong> SLEEPING_FOREST_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"sleeping_forest"));
    public static final ResourceKey<JukeboxSong> DREAMS_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"dreams"));
    public static final ResourceKey<JukeboxSong> INTO_SILENCE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"into_silence"));
    public static final ResourceKey<JukeboxSong> JUST_A_MEMORY_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,"just_a_memory"));

    public static void bootstrap(BootstrapContext<JukeboxSong> context){
        register(context, ONLY_ONCE_MORE_KEY, ModSounds.ONLY_ONCE_MORE, 160,14);
        register(context, DUSK_TO_DAWN_KEY, ModSounds.DUSK_TO_DAWN, 197,12);
        register(context, BROKEN_ETERNITY_KEY, ModSounds.BROKEN_ETERNITY, 229,13);
        register(context, SLEEPING_FOREST_KEY, ModSounds.SLEEPING_FOREST, 182,7);
        register(context, DREAMS_KEY, ModSounds.DREAMS, 187,8);
        register(context, INTO_SILENCE_KEY, ModSounds.INTO_SILENCE, 242,10);
        register(context, JUST_A_MEMORY_KEY, ModSounds.JUST_A_MEMORY, 160,5);
    }






    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key,
                                 Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput){
        context.register(key, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), lengthInSeconds, comparatorOutput));
    }



}
