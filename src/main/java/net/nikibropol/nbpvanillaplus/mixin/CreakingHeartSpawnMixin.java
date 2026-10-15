package net.nikibropol.nbpvanillaplus.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.TrailParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.entity.CreakingHeartBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.EchoCreakingHeartBlock;
import net.nikibropol.nbpvanillaplus.entity.ModEntities;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(CreakingHeartBlockEntity.class)
public abstract class CreakingHeartSpawnMixin {

    private static final int ECHO_TRAIL_TO_CREAKING = 0x29DFEB;
    private static final int ECHO_TRAIL_TO_HEART = 0x0E5A6B;

    @ModifyExpressionValue(
            method = "spawnProtector",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/EntityTypes;CREAKING:Lnet/minecraft/world/entity/EntityType;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private static EntityType<Creaking> nbp$useEchoCreaking(
            final EntityType<Creaking> original,
            @Local(argsOnly = true) final CreakingHeartBlockEntity entity
    ) {
        if (entity.getBlockState().getBlock() instanceof EchoCreakingHeartBlock) {
            return (EntityType<Creaking>) (EntityType<?>) ModEntities.ECHO_CREAKING;
        }
        return original;
    }
    @Inject(method = "spreadResin", at = @At("HEAD"), cancellable = true)
    private void nbp$echoSpreadResin(final ServerLevel level, final CallbackInfoReturnable<Optional<BlockPos>> callbackInfoReturnable) {
        CreakingHeartBlockEntity self = (CreakingHeartBlockEntity) (Object) this;
        if (!(self.getBlockState().getBlock() instanceof EchoCreakingHeartBlock)) {
            return;
        }

        RandomSource random = level.getRandom();
        Mutable<BlockPos> placed = new MutableObject<>(null);
        Block clump = ModBlocks.RESIN_ECHO_CLUMP;

        BlockPos.breadthFirstTraversal(self.getBlockPos(), 2, 64, (pos, acceptor) -> {
            for (Direction dir : Util.shuffledCopy(Direction.values(), random)) {
                BlockPos neighbour = pos.relative(dir);
                if (level.getBlockState(neighbour).is(BlockTags.PALE_OAK_LOGS)) {
                    acceptor.accept(neighbour);
                }
            }
        }, pos -> {
            if (!level.getBlockState(pos).is(BlockTags.PALE_OAK_LOGS)) {
                return BlockPos.TraversalNodeStatus.ACCEPT;
            }

            for (Direction direction : Util.shuffledCopy(Direction.values(), random)) {
                BlockPos neighbourPos = pos.relative(direction);
                BlockState neighbourState = level.getBlockState(neighbourPos);
                Direction opposite = direction.getOpposite();
                if (neighbourState.isAir()) {
                    neighbourState = clump.defaultBlockState();
                } else if (neighbourState.is(Blocks.WATER) && neighbourState.getFluidState().isSource()) {
                    neighbourState = clump.defaultBlockState().setValue(MultifaceBlock.WATERLOGGED, true);
                }

                if (neighbourState.is(clump) && !MultifaceBlock.hasFace(neighbourState, opposite)) {
                    level.setBlock(neighbourPos, neighbourState.setValue(MultifaceBlock.getFaceProperty(opposite), true), 3);
                    placed.setValue(neighbourPos);
                    return BlockPos.TraversalNodeStatus.STOP;
                }
            }

            return BlockPos.TraversalNodeStatus.ACCEPT;
        });

        callbackInfoReturnable.setReturnValue(Optional.ofNullable(placed.get()));
    }
    @WrapOperation(
            method = "emitParticles",
            at = @At(value = "NEW", target = "(Lnet/minecraft/world/phys/Vec3;II)Lnet/minecraft/core/particles/TrailParticleOption;")
    )
    private TrailParticleOption nbp$echoTrailColor(
            final Vec3 target, final int color, final int duration,
            final Operation<TrailParticleOption> original,
            @Local(argsOnly = true) final boolean towardsCreaking
    ) {
        CreakingHeartBlockEntity self = (CreakingHeartBlockEntity) (Object) this;
        if (self.getBlockState().getBlock() instanceof EchoCreakingHeartBlock) {
            return original.call(target, towardsCreaking ? ECHO_TRAIL_TO_CREAKING : ECHO_TRAIL_TO_HEART, duration);
        }
        return original.call(target, color, duration);
    }
}
