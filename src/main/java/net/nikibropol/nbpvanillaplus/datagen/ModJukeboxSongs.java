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

    public static void bootstrap(BootstrapContext<JukeboxSong> context){
        register(context, ONLY_ONCE_MORE_KEY, ModSounds.ONLY_ONCE_MORE, 160,14);
        register(context, DUSK_TO_DAWN_KEY, ModSounds.DUSK_TO_DAWN, 197,12);
        register(context, BROKEN_ETERNITY_KEY, ModSounds.BROKEN_ETERNITY, 229,13);
    }






    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key,
                                 Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput){
        context.register(key, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), lengthInSeconds, comparatorOutput));
    }



}
