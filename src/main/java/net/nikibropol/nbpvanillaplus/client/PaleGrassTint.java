package net.nikibropol.nbpvanillaplus.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.List;

public class PaleGrassTint {
    private static final int PALE_GRASS = 0x7F8A7F;

    public static void register() {
        BlockColorRegistry.register(List.of(new BlockTintSource() {
            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                BlockPos ground = pos.below();
                if (state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)
                        && state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                    ground = ground.below();
                }
                if (level.getBlockState(ground).is(Blocks.PALE_MOSS_BLOCK)) {
                    return ARGB.opaque(PALE_GRASS);
                }
                return BiomeColors.getAverageGrassColor(level, pos);
            }

            @Override
            public int color(BlockState state) {
                return ARGB.opaque(0x91BD59);
            }
        }), Blocks.SHORT_GRASS, Blocks.TALL_GRASS, Blocks.FERN, Blocks.LARGE_FERN);
    }
}