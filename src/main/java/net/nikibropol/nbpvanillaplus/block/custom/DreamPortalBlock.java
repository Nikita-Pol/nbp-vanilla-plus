package net.nikibropol.nbpvanillaplus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.worldgen.dimension.DreamPortalShape;
import net.nikibropol.nbpvanillaplus.worldgen.dimension.ModDimensions;

public class DreamPortalBlock extends Block implements Portal {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final VoxelShape X_SHAPE = Block.box(0, 0, 6, 16, 16, 10);
    private static final VoxelShape Z_SHAPE = Block.box(6, 0, 0, 10, 16, 16);

    public DreamPortalBlock(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return state.getValue(AXIS) == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
                                     Direction dir, BlockPos neighborPos, BlockState neighbor, RandomSource random) {
        Direction.Axis changed = dir.getAxis();
        boolean inPlane = changed != Direction.Axis.Y && changed != state.getValue(AXIS);
        if (!inPlane && !neighbor.is(this) && !neighbor.is(ModBlocks.DREAMED_POWERED_OBSIDIAN)
                && !(level instanceof LevelAccessor la && DreamPortalShape.find(la, pos, state.getValue(AXIS)).isPresent())) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, level, ticks, pos, dir, neighborPos, neighbor, random);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
                                InsideBlockEffectApplier applier, boolean intersects) {
        if (entity.canUsePortal(false)) entity.setAsInsidePortal(this, pos);
    }


    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player p && p.getAbilities().invulnerable ? 1 : 80;
    }

    @Override
    public Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    @Override
    public TeleportTransition getPortalDestination(ServerLevel fromLevel, Entity entity, BlockPos blockPos) {
        boolean inDream = fromLevel.dimension().equals(ModDimensions.DREAMSCAPE_LEVEL_KEY);
        ServerLevel destLevel = fromLevel.getServer().getLevel(inDream ? Level.OVERWORLD : ModDimensions.DREAMSCAPE_LEVEL_KEY);
        if (destLevel == null) return null;

        Direction.Axis axis = fromLevel.getBlockState(blockPos).getValue(AXIS);
        BlockPos base = findOrBuild(destLevel, blockPos, axis);
        boolean x = axis == Direction.Axis.X;
        Vec3 target = new Vec3(base.getX() + (x ? 1.0 : 0.5), base.getY(), base.getZ() + (x ? 0.5 : 1.0));

        return new TeleportTransition(destLevel, target, Vec3.ZERO, entity.getYRot(), entity.getXRot(),
                TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET));
    }

    private static BlockPos findOrBuild(ServerLevel dest, BlockPos around, Direction.Axis axis) {
        int cx = around.getX(), cz = around.getZ();
        int surface = surfaceY(dest, cx, cz);

        BlockPos.MutableBlockPos p = new BlockPos.MutableBlockPos();
        BlockPos best = null;
        for (int dx = -8; dx <= 8; dx++)
            for (int dz = -8; dz <= 8; dz++)
                for (int dy = -6; dy <= 10; dy++) {
                    p.set(cx + dx, surface + dy, cz + dz);
                    if (dest.getBlockState(p).is(ModBlocks.DREAM_PORTAL)
                            && (best == null || p.getY() < best.getY())) {
                        best = p.immutable();
                    }
                }
        if (best != null) {
            Direction.Axis a = dest.getBlockState(best).getValue(AXIS);
            Direction left = a == Direction.Axis.X ? Direction.WEST : Direction.NORTH;
            while (dest.getBlockState(best.relative(left)).is(ModBlocks.DREAM_PORTAL)) best = best.relative(left);
            return best;
        }

        BlockPos base = new BlockPos(cx, surface, cz);
        Direction right = axis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;
        BlockState frame = ModBlocks.DREAMED_POWERED_OBSIDIAN.defaultBlockState();
        for (int i = -1; i <= 2; i++)
            for (int j = -1; j <= 3; j++) {
                boolean inside = i >= 0 && i <= 1 && j >= 0 && j <= 2;
                dest.setBlock(base.relative(right, i).above(j), inside ? Blocks.AIR.defaultBlockState() : frame, 18);
            }
        DreamPortalShape.find(dest, base, axis).ifPresent(s -> s.fill(dest));
        return base;
    }
    private static int surfaceY(ServerLevel level, int x, int z) {
        level.getChunk(x >> 4, z >> 4);
        BlockPos.MutableBlockPos p = new BlockPos.MutableBlockPos(x, 0, z);
        for (int y = level.getMaxY() - 1; y >= level.getMinY(); y--) {
            p.setY(y);
            BlockState s = level.getBlockState(p);
            if (!s.isAir() && !s.canBeReplaced()
                    && !s.is(BlockTags.LEAVES) && !s.is(BlockTags.LOGS) && !s.is(Blocks.BEDROCK)) {
                return y + 1;
            }
        }
        return level.getSeaLevel();
    }
}
