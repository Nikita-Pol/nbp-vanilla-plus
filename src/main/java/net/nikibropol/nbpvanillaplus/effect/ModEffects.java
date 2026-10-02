package net.nikibropol.nbpvanillaplus.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModEffects {
    public static final Holder<MobEffect> SILENCE = registerMobEffect("silence",
            new SilenceEffect(MobEffectCategory.BENEFICIAL, 0x034150));

    public static final Holder<MobEffect> STRANGE_BEETROOT_DAMAGE = registerMobEffect("strange_beetroot_damage",
            new StrangeBeetrootEffect(MobEffectCategory.HARMFUL, 0xccffcc, true));


    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect){
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), effect);
    }

    public static void registerEffects()
    {
        NBPVanillaPlus.LOGGER.info("Registering Effects for " + NBPVanillaPlus.MOD_ID);
    }
}
