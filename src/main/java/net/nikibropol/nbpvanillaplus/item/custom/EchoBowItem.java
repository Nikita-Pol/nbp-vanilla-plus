package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EchoBowItem extends BowItem {

    private static final Map<UUID, Vec3> lastPositions = new HashMap<>();

    public EchoBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingTicks) {
        super.onUseTick(level, entity, stack, remainingTicks);
        if (level.isClientSide()) return;

        Vec3 currentPos = entity.position();
        Vec3 lastPos = lastPositions.get(entity.getUUID());
        lastPositions.put(entity.getUUID(), currentPos);

        int charge = stack.getOrDefault(ModDataComponents.ECHO_CHARGE, 0);

        if (lastPos != null) {
            double distance = currentPos.distanceTo(lastPos);
            if (distance > 0.01) {
                charge = Math.min(charge + 1, 40);
            } else {
                charge = Math.max(charge - 1, 0);
            }
        }

        stack.set(ModDataComponents.ECHO_CHARGE, charge);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeCharged) {
        boolean result = super.releaseUsing(stack, level, entity, timeCharged);
        if (!level.isClientSide()) {
            lastPositions.remove(entity.getUUID());
        }
        return result;
    }

    @Override
    protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand,
                         ItemStack bow, List<ItemStack> ammo, float velocity, float inaccuracy,
                         boolean critical, @Nullable LivingEntity target) {
        int charge = bow.getOrDefault(ModDataComponents.ECHO_CHARGE, 0);
        float boosted = velocity * (1.0F + charge / 40.0F * 0.8F);

        super.shoot(level, shooter, hand, bow, ammo, boosted, inaccuracy, critical, target);

        bow.set(ModDataComponents.ECHO_CHARGE, 0);
        spawnSonicWave(level, shooter, charge);
    }

    private void spawnSonicWave(ServerLevel level, LivingEntity shooter, int charge) {
        Vec3 look = shooter.getLookAngle();
        Vec3 start = shooter.getEyePosition();

        int length = 4 + Math.round(12 * (charge / 40.0F));
        int particlesPerPoint = 1 + Math.round(2 * (charge / 40.0F));

        for (int i = 1; i <= length; i++) {
            Vec3 point = start.add(look.scale(i));
            level.sendParticles(ParticleTypes.SONIC_BOOM,
                    point.x, point.y, point.z, particlesPerPoint, 0.05, 0.05, 0.05, 0);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.isDamaged() || stack.getOrDefault(ModDataComponents.ECHO_CHARGE, 0) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int charge = stack.getOrDefault(ModDataComponents.ECHO_CHARGE, 0);
        if (charge > 0) {
            return Math.round(13.0F * Math.min(charge, 40) / 40.0F);
        }
        return Math.round(13.0F - (float) stack.getDamageValue() * 13.0F / (float) stack.getMaxDamage());
    }

    @Override
    public int getBarColor(ItemStack stack) {
        int charge = stack.getOrDefault(ModDataComponents.ECHO_CHARGE, 0);
        if (charge > 0) {
            return 0x00AAFF;
        }
        float durabilityFraction = 1.0F - (float) stack.getDamageValue() / (float) stack.getMaxDamage();
        return Mth.hsvToRgb(Math.max(0.0F, durabilityFraction) / 3.0F, 1.0F, 1.0F);
    }
}
