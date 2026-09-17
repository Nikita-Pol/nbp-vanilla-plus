package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class DreamEchoStatueBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = Block.box(3,0,3,13,16,13);
    private final Map<BlockState, VoxelShape> shapes = new HashMap<>();
    public static final MapCodec<DreamEchoStatueBlock> CODEC = simpleCodec(DreamEchoStatueBlock::new);
    public DreamEchoStatueBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
        this.stateDefinition.getPossibleStates().forEach(state ->
                this.shapes.put(state, calculateShape(state)));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    private static VoxelShape calculateShape(BlockState state) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);

        return switch (face) {
            case FLOOR, CEILING -> SHAPE;
            case WALL -> rotateToHorizontalFacing(rotateAroundX90(SHAPE), facing);
        };
    }

    private static VoxelShape rotateToHorizontalFacing(VoxelShape shape, Direction facing) {
        VoxelShape result = shape;
        int rotations = switch (facing) {
            case NORTH -> 0;
            case EAST -> 1;
            case SOUTH -> 2;
            case WEST -> 3;
            default -> 0;
        };
        for (int i = 0; i < rotations; i++) {
            result = rotateShape90(result);
        }
        return result;
    }

    private static VoxelShape rotateShape90(VoxelShape shape) {
        VoxelShape[] result = {Shapes.empty()};
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
                result[0] = Shapes.or(result[0], Shapes.box(
                        1 - maxZ, minY, minX,
                        1 - minZ, maxY, maxX)));
        return result[0];
    }
    private static VoxelShape rotateAroundX90(VoxelShape shape) {
        VoxelShape[] result = {Shapes.empty()};
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
                result[0] = Shapes.or(result[0], Shapes.box(
                        minX, 1 - maxZ, minY,
                        maxX, 1 - minZ, maxY)));
        return result[0];
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.getOrDefault(state, Shapes.block());
    }

    @Override
    protected BlockState rotate(final BlockState state, final Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(final BlockState state, final Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
