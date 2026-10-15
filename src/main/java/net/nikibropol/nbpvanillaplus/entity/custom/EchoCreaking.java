package net.nikibropol.nbpvanillaplus.entity.custom;

import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.block.state.properties.CreakingHeartState;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.EchoCreakingHeartBlock;
import net.nikibropol.nbpvanillaplus.tags.ModTags;
import org.jspecify.annotations.Nullable;

public class EchoCreaking extends Creaking implements VibrationSystem {
    public static final int HEARING_RADIUS = 16;
    public static final int DARKNESS_RADIUS = HEARING_RADIUS + 5;
    private static final int MOVE_TICKS = 200;
    private static final float WALK_SPEED = 1.0F;

    private static final Brain.Provider<Creaking> ECHO_BRAIN_PROVIDER = Brain.provider(
            java.util.List.of(
                    MemoryModuleType.WALK_TARGET,
                    MemoryModuleType.LOOK_TARGET,
                    MemoryModuleType.ATTACK_TARGET,
                    MemoryModuleType.NEAREST_PLAYERS
            ),
            java.util.List.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS),
            EchoCreakingAi::getActivities
    );

    private final DynamicGameEventListener<VibrationSystem.Listener> dynamicGameEventListener;
    private final VibrationSystem.User vibrationUser;
    private VibrationSystem.Data vibrationData;

    private @Nullable BlockPos soundTarget;
    private int moveTicks;
    private int attackCooldown;

    public EchoCreaking(final EntityType<? extends Creaking> type, final Level level) {
        super(type, level);
        this.vibrationUser = new EchoVibrationUser();
        this.vibrationData = new VibrationSystem.Data();
        this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationSystem.Listener(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.9)
                .add(Attributes.ATTACK_DAMAGE, 7.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.STEP_HEIGHT, 1.0625);
    }

    @Override
    protected Brain<Creaking> makeBrain(final Brain.Packed packedBrain) {
        return ECHO_BRAIN_PROVIDER.makeBrain(this, packedBrain);
    }

    @Override
    public boolean checkCanMove() {
        return this.moveTicks > 0;
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel serverLevel) {
            VibrationSystem.Ticker.tick(serverLevel, this.vibrationData, this.vibrationUser);
        }
        super.tick();
    }

    @Override
    protected void customServerAiStep(final ServerLevel level) {
        if (this.moveTicks > 0 && this.soundTarget != null
                && !this.getBrain().hasMemoryValue(MemoryModuleType.WALK_TARGET)) {
            this.applyWalkTarget();
        }
        super.customServerAiStep(level);

        if (this.moveTicks > 0) {
            this.moveTicks--;
            if (this.soundTarget != null
                    && this.distanceToSqr(Vec3.atBottomCenterOf(this.soundTarget)) < 4.0) {
                this.finishMoving();
            }
            this.tryAttackNearby(level);
        }

        if ((this.tickCount + this.getId()) % 40 == 0) {
            MobEffectInstance darkness = new MobEffectInstance(MobEffects.DARKNESS, 200, 0, false, false);
            MobEffectUtil.addEffectToPlayersAround(level, this, this.position(), DARKNESS_RADIUS, darkness, 200);
        }
    }

    private void tryAttackNearby(final ServerLevel level) {
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
            return;
        }
        var players = level.getEntitiesOfClass(
                Player.class, this.getBoundingBox().inflate(0.4, 0.0, 0.4), EntitySelector.NO_CREATIVE_OR_SPECTATOR
        );
        if (!players.isEmpty()) {
            this.doHurtTarget(level, players.get(0));
            this.attackCooldown = 10;
        }
    }

    private void hearSound(final BlockPos pos) {
        this.soundTarget = pos;
        this.moveTicks = MOVE_TICKS;
        this.applyWalkTarget();
    }

    private void applyWalkTarget() {
        if (this.soundTarget == null) {
            return;
        }
        this.getBrain().setMemoryWithExpiry(MemoryModuleType.WALK_TARGET, new WalkTarget(this.soundTarget, WALK_SPEED, 1), MOVE_TICKS);
        this.getBrain().setMemoryWithExpiry(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(this.soundTarget), MOVE_TICKS);
    }

    private void finishMoving() {
        this.moveTicks = 0;
        this.soundTarget = null;
        this.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        this.getNavigation().stop();
    }

    @Override
    public void tearDown() {
        if (this.level() instanceof ServerLevel serverLevel) {
            AABB box = this.getBoundingBox();
            Vec3 center = box.getCenter();
            double xSpread = box.getXsize() * 0.3;
            double ySpread = box.getYsize() * 0.3;
            double zSpread = box.getZsize() * 0.3;
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK_CRUMBLE, Blocks.PALE_OAK_WOOD.defaultBlockState()),
                    center.x, center.y, center.z,
                    100,
                    xSpread, ySpread, zSpread,
                    0.0
            );
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK_CRUMBLE,
                            ModBlocks.ECHO_CREAKING_HEART.defaultBlockState().setValue(CreakingHeartBlock.STATE, CreakingHeartState.AWAKE)),
                    center.x, center.y, center.z,
                    10,
                    xSpread, ySpread, zSpread,
                    0.0
            );
        }

        this.makeSound(this.getDeathSound());
        this.remove(Entity.RemovalReason.DISCARDED);
    }
    @Override
    public void creakingDeathEffects(final DamageSource source) {
        this.blameSourceForDamage(source);
        this.die(source);
        this.makeSound(SoundEvents.CREAKING_TWITCH);
    }

    @Override
    public void updateDynamicGameEventListener(final BiConsumer<DynamicGameEventListener<?>, ServerLevel> action) {
        if (this.level() instanceof ServerLevel serverLevel) {
            action.accept(this.dynamicGameEventListener, serverLevel);
        }
    }

    @Override
    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("listener", VibrationSystem.Data.CODEC, this.vibrationData);
    }

    @Override
    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        this.vibrationData = input.read("listener", VibrationSystem.Data.CODEC).orElseGet(VibrationSystem.Data::new);
    }

    private boolean canHear(final @Nullable Entity source) {
        return source != null
                && source != this
                && !source.isSpectator()
                && source.is(ModTags.Entities.ECHO_CREAKING_HEARS);
    }

    @Override
    public boolean hurtServer(final ServerLevel level, final DamageSource source, final float damage) {
        boolean wasHurt = super.hurtServer(level, source, damage);
        Entity attacker = source.getEntity();
        if (canHear(attacker) && !this.isDeadOrDying()) {
            this.hearSound(attacker.blockPosition());
        }
        return wasHurt;
    }

    @Override
    public VibrationSystem.Data getVibrationData() {
        return this.vibrationData;
    }

    @Override
    public VibrationSystem.User getVibrationUser() {
        return this.vibrationUser;
    }

    private class EchoVibrationUser implements VibrationSystem.User {
        private final PositionSource positionSource = new EntityPositionSource(EchoCreaking.this, EchoCreaking.this.getEyeHeight());

        @Override
        public int getListenerRadius() {
            return HEARING_RADIUS;
        }

        @Override
        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public TagKey<GameEvent> getListenableEvents() {
            return ModTags.GameEvents.ECHO_CREAKING_LISTENS;
        }

        @Override
        public boolean canReceiveVibration(final ServerLevel level, final BlockPos pos, final Holder<GameEvent> event, final GameEvent.Context context) {
            Entity source = context.sourceEntity();
            return !EchoCreaking.this.isNoAi()
                    && !EchoCreaking.this.isDeadOrDying()
                    && level.getWorldBorder().isWithinBounds(pos)
                    && source != null;
        }

        @Override
        public void onReceiveVibration(
                final ServerLevel level,
                final BlockPos pos,
                final Holder<GameEvent> event,
                final @Nullable Entity sourceEntity,
                final @Nullable Entity projectileOwner,
                final float receivingDistance
        ) {
            if (!EchoCreaking.this.isDeadOrDying()) {
                EchoCreaking.this.hearSound(pos);
            }
        }
    }
}
