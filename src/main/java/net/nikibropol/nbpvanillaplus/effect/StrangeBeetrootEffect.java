package net.nikibropol.nbpvanillaplus.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantaneousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.datagen.ModDamageTypes;
import org.jspecify.annotations.Nullable;

public class StrangeBeetrootEffect extends InstantaneousMobEffect {
    private boolean isHarm;

    protected StrangeBeetrootEffect(MobEffectCategory category, int color, boolean isHarm) {
        super(category, color);
        this.isHarm = isHarm;
    }
    @Override
    public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity livingEntity, final int amplification) {
        boolean result = livingEntity.hurtServer(serverLevel,
                ModDamageTypes.create(serverLevel, ModDamageTypes.STRANGE_BEETROOT_DAMAGE), 8 * (amplification + 1.25f));
       return true;
    }

    @Override
    public void applyInstantaneousEffect(
            final ServerLevel serverLevel,
            final @Nullable Entity source,
            final @Nullable Entity owner,
            final LivingEntity livingEntity,
            final int amplification,
            final double scale
    ) {
        boolean result = livingEntity.hurtServer(serverLevel,
                ModDamageTypes.create(serverLevel, ModDamageTypes.STRANGE_BEETROOT_DAMAGE), 8 * (amplification + 1.25f));
        }

    @Override
    public boolean isInstantaneous() {
        return true;
    }

}
