package net.nikibropol.nbpvanillaplus.sound;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModSounds {

    public static final SoundEvent COBBLER_USE = registerSoundEvent("cobbler_use");

    public static final Holder.Reference<SoundEvent> ONLY_ONCE_MORE = registerSoundEventAsHolder("only_once_more");
    public static final Holder.Reference<SoundEvent> DUSK_TO_DAWN = registerSoundEventAsHolder("dusk_to_dawn");
    public static final Holder.Reference<SoundEvent> BROKEN_ETERNITY = registerSoundEventAsHolder("broken_eternity");
    public static final Holder.Reference<SoundEvent> SLEEPING_FOREST = registerSoundEventAsHolder("sleeping_forest");
    public static final Holder.Reference<SoundEvent> DREAMS = registerSoundEventAsHolder("dreams");
    public static final Holder.Reference<SoundEvent> INTO_SILENCE = registerSoundEventAsHolder("into_silence");
    public static final Holder.Reference<SoundEvent> JUST_A_MEMORY = registerSoundEventAsHolder("just_a_memory");

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
