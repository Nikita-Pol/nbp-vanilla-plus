package net.nikibropol.nbpvanillaplus.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class EchoArrow extends AbstractArrow {
    private static final double HOMING_RANGE = 27.5;
    private static final double MAX_TURN_DEGREES_PER_TICK = 10.0;
    private static final double CONE_DOT = 0.35; //Control arrow homing degree angle

    public EchoArrow(EntityType<? extends EchoArrow> type, Level level) {
        super(type, level);
        this.setBaseDamage(0.7);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isInGround()) {
            applyHoming();
        }

        if (this.level().isClientSide() && !this.isInGround()) {
            this.level().addParticle(ParticleTypes.SCULK_SOUL,
                    this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    private void applyHoming() {
        LivingEntity target = findTarget();
        if (target == null) return;

        Vec3 current = this.getDeltaMovement();
        double speed = current.length();
        Vec3 currentDir = current.normalize();
        Vec3 desiredDir = target.getEyePosition().subtract(this.position()).normalize();

        double dot = Mth.clamp(currentDir.dot(desiredDir), -1.0, 1.0);
        double angleBetween = Math.acos(dot);

        if (angleBetween < 1.0E-4) {
            return;
        }

        double maxTurnRad = Math.toRadians(MAX_TURN_DEGREES_PER_TICK);
        double t = Math.min(1.0, maxTurnRad / angleBetween);

        Vec3 newDir = currentDir.lerp(desiredDir, t).normalize();
        this.setDeltaMovement(newDir.scale(speed));
    }

    private LivingEntity findTarget() {
        Vec3 direction = this.getDeltaMovement().normalize();
        AABB searchBox = this.getBoundingBox().inflate(HOMING_RANGE);

        List<LivingEntity> candidates = this.level().getEntitiesOfClass(LivingEntity.class, searchBox,
                entity -> entity != this.getOwner()
                        && entity.isAlive()
                        && !entity.isSpectator()
                        && inCone(entity, direction));

        if (candidates.isEmpty()) return null;
        // Firstly target on Enemy entities
        LivingEntity hostile = nearest(candidates, e -> e instanceof Enemy);
        if (hostile != null) return hostile;
        // Secondly target on neutral aggroed Entities
        LivingEntity aggroed = nearest(candidates,
                e -> e instanceof Mob mob && mob.getTarget() == this.getOwner());
        if (aggroed != null) return aggroed;
        //Homing stopped and echo arrow works like a vanilla arrow
        return null;
    }

    private LivingEntity nearest(List<LivingEntity> list, Predicate<LivingEntity> filter) {
        return list.stream()
                .filter(filter)
                .min(Comparator.comparingDouble(this::distanceToSqr))
                .orElse(null);
    }

    private boolean inCone(LivingEntity entity, Vec3 direction) {
        Vec3 toEntity = entity.position().subtract(this.position()).normalize();
        return direction.dot(toEntity) > CONE_DOT;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.ECHO_ARROW);
    }
}
