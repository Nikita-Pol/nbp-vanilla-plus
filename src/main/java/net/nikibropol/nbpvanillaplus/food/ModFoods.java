package net.nikibropol.nbpvanillaplus.food;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;
import net.nikibropol.nbpvanillaplus.effect.ModEffects;

public class ModFoods {

    public static final FoodProperties ECHOBERRY = new FoodProperties.Builder().nutrition(4).saturationModifier(2F).alwaysEdible().build();

    public static final FoodProperties AMETHYST_SWEET_BERRIES = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).alwaysEdible().build();

    public static final FoodProperties STRANGE_BEETROOT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).alwaysEdible().build();

    public static final FoodProperties FLOWTAREM = new FoodProperties.Builder().nutrition(3).saturationModifier(0.8F).alwaysEdible().build();
    public static final FoodProperties RESIN_FLOWTAREM = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).alwaysEdible().build();

    public static final Consumable ECHOBERRY_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 400, 0), 0.05F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.SILENCE, 300, 0), 1F))
            .consumeSeconds(2f).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build();

    public static final Consumable AMETHYST_SWEET_BERRIES_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0), 0.05F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0)))
            .consumeSeconds(1.5f).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build();

    public static final Consumable STRANGE_BEETROOT_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.STRANGE_BEETROOT_DAMAGE, 1, 0)))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LEVITATION, 120, 0)))
            .consumeSeconds(1.5f).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build();

    public static final Consumable FLOWTAREM_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 100, 0)))
            .consumeSeconds(2.5f).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build();

    public static final Consumable RESIN_FLOWTAREM_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new RemoveStatusEffectsConsumeEffect(HolderSet.direct(MobEffects.DARKNESS)))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 400, 0)))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.OOZING, 400, 0)))
            .consumeSeconds(2.5f).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true).build();
}
