package net.nikibropol.nbpvanillaplus.sound;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biomes;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModSounds {

    public static final SoundEvent COBBLER_USE = registerSoundEvent("cobbler_use");

    public static final Holder.Reference<SoundEvent> ONLY_ONCE_MORE = registerSoundEventAsHolder("only_once_more");
    public static final Holder.Reference<SoundEvent> DUSK_TO_DAWN = registerSoundEventAsHolder("dusk_to_dawn");
    public static final Holder.Reference<SoundEvent> BROKEN_ETERNITY = registerSoundEventAsHolder("broken_eternity");

    public static final Holder.Reference<SoundEvent> THEY_STILL_WAIT_YOU = registerSoundEventAsHolder("they_still_wait_you");

    public static final Holder.Reference<SoundEvent> BREAK_TIME = registerSoundEventAsHolder("break_time");
    public static final Holder.Reference<SoundEvent> LETS_BE_HAPPY = registerSoundEventAsHolder("lets_be_happy");
    public static final Holder.Reference<SoundEvent> AFTER_ALL = registerSoundEventAsHolder("after_all");
    public static final Holder.Reference<SoundEvent> BREATHE_BLUR = registerSoundEventAsHolder("breathe_blur");
    public static final Holder.Reference<SoundEvent> EVENING = registerSoundEventAsHolder("evening");
    public static final Holder.Reference<SoundEvent> INFORMATED = registerSoundEventAsHolder("informated");
    public static final Holder.Reference<SoundEvent> MEMORIES = registerSoundEventAsHolder("memories");
    public static final Holder.Reference<SoundEvent> NEBULA = registerSoundEventAsHolder("nebula");
    public static final Holder.Reference<SoundEvent> RESTART = registerSoundEventAsHolder("restart");
    public static final Holder.Reference<SoundEvent> STILL_THERE = registerSoundEventAsHolder("still_there");
    public static final Holder.Reference<SoundEvent> TIMES_UP = registerSoundEventAsHolder("times_up");
    public static final Holder.Reference<SoundEvent> AMBIENT_BROKEN_ETERNITY = registerSoundEventAsHolder("ambient_broken_eternity");
    public static final Holder.Reference<SoundEvent> NAMELESS_ECHOES = registerSoundEventAsHolder("nameless_echoes");

    public static Holder.Reference<SoundEvent> registerSoundEventAsHolder(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds(){
        NBPVanillaPlus.LOGGER.info("Registering Sounds for " + NBPVanillaPlus.MOD_ID);
    }

}
