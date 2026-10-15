package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.levelgen.feature.treedecorators.CreakingHeartDecorator;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CreakingHeartDecorator.class)
public class CreakingHeartDecoratorMixin {
    private static final float ECHO_CHANCE = 0.02F;

    @ModifyArgs(
            method = "place",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/levelgen/feature/treedecorators/TreeDecorator$Context;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
            )
    )
    private void nbp$echoHeart(Args args) {
        BlockPos pos = args.get(0);
        if (RandomSource.create(pos.asLong()).nextFloat() < ECHO_CHANCE) {
            BlockState state = args.get(1);
            args.set(1, ModBlocks.ECHO_CREAKING_HEART.defaultBlockState()
                    .setValue(CreakingHeartBlock.STATE, state.getValue(CreakingHeartBlock.STATE))
                    .setValue(CreakingHeartBlock.NATURAL, true));
        }
    }
}