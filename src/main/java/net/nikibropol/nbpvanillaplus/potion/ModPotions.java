package net.nikibropol.nbpvanillaplus.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.effect.ModEffects;

public class ModPotions {
    public static final Holder<Potion> WEAK_SILENCE_POTION = registerPotion("weak_silence_potion",
            new Potion("silence_potion", new MobEffectInstance(ModEffects.SILENCE, 600, 0)));
    public static final Holder<Potion> SHORT_SILENCE_POTION = registerPotion("short_silence_potion",
            new Potion("silence_potion", new MobEffectInstance(ModEffects.SILENCE, 1200, 0)));
    public static final Holder<Potion> SILENCE_POTION = registerPotion("silence_potion",
            new Potion("silence_potion", new MobEffectInstance(ModEffects.SILENCE, 3600, 0)));
    public static final Holder<Potion> LONG_SILENCE_POTION = registerPotion("long_silence_potion",
            new Potion("silence_potion", new MobEffectInstance(ModEffects.SILENCE, 9600, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion){
        return Registry.registerForHolder(BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), potion);
    }

    public static void registerPotions(){
        NBPVanillaPlus.LOGGER.info("Registering Potions for " + NBPVanillaPlus.MOD_ID);
    }
}
