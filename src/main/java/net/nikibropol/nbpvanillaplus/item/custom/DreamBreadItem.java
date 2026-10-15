package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nikibropol.nbpvanillaplus.effect.ModEffects;

import java.util.List;
import java.util.Properties;

public class DreamBreadItem extends Item {
    private static final List<Holder<MobEffect>> EFFECTS = List.of(
            MobEffects.SPEED, MobEffects.JUMP_BOOST, MobEffects.REGENERATION,
            MobEffects.NIGHT_VISION, MobEffects.RESISTANCE, MobEffects.STRENGTH,
            MobEffects.SLOW_FALLING, MobEffects.HASTE, ModEffects.SILENCE);

    public DreamBreadItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            Holder<MobEffect> effect = EFFECTS.get(level.getRandom().nextInt(EFFECTS.size()));
            int duration = 200 + level.getRandom().nextInt(101);
            entity.addEffect(new MobEffectInstance(effect, duration, 0));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
