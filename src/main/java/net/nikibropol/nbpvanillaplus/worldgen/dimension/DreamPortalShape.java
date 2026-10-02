package net.nikibropol.nbpvanillaplus.worldgen.dimension;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.nikibropol.nbpvanillaplus.block.custom.DreamPortalBlock;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

import java.util.Optional;

public record DreamPortalShape(Direction.Axis axis, BlockPos bottomLeft, int width, int height) {
    private static final int MIN_W = 2, MIN_H = 3, MAX = 21;

    private static boolean empty(LevelAccessor l, BlockPos p) {
        BlockState s = l.getBlockState(p);
        return s.isAir() || s.is(ModBlocks.DREAM_PORTAL);
    }
    private static boolean frame(LevelAccessor l, BlockPos p) {
        return l.getBlockState(p).is(ModBlocks.DREAMED_POWERED_OBSIDIAN);
    }

    public static Optional<DreamPortalShape> find(LevelAccessor level, BlockPos start, Direction.Axis axis) {
        Direction right = axis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;
        Direction left = right.getOpposite();
        BlockPos pos = start;

        int n = 0;
        while (n++ < MAX && empty(level, pos.below())) pos = pos.below();
        if (!frame(level, pos.below())) return Optional.empty();

        n = 0;
        while (n++ < MAX && empty(level, pos.relative(left))) pos = pos.relative(left);
        if (!frame(level, pos.relative(left))) return Optional.empty();

        int w = 0;
        while (w <= MAX && empty(level, pos.relative(right, w))) w++;
        if (w < MIN_W || w > MAX || !frame(level, pos.relative(right, w))) return Optional.empty();

        int h = 0;
        while (h <= MAX && rowEmpty(level, pos, right, w, h)) h++;
        if (h < MIN_H || h > MAX) return Optional.empty();

        for (int i = 0; i < w; i++) {
            if (!frame(level, pos.relative(right, i).below())) return Optional.empty();
            if (!frame(level, pos.relative(right, i).above(h))) return Optional.empty();
        }
        for (int j = 0; j < h; j++) {
            if (!frame(level, pos.relative(left).above(j))) return Optional.empty();
            if (!frame(level, pos.relative(right, w).above(j))) return Optional.empty();
        }
        return Optional.of(new DreamPortalShape(axis, pos, w, h));
    }

    private static boolean rowEmpty(LevelAccessor l, BlockPos base, Direction right, int w, int h) {
        for (int i = 0; i < w; i++) if (!empty(l, base.relative(right, i).above(h))) return false;
        return true;
    }

    public void fill(LevelAccessor level) {
        Direction right = axis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;
        BlockState portal = ModBlocks.DREAM_PORTAL.defaultBlockState().setValue(DreamPortalBlock.AXIS, axis);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                level.setBlock(bottomLeft.relative(right, i).above(j), portal, 18);
    }
}
