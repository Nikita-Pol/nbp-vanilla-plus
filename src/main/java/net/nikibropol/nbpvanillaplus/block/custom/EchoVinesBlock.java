package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class EchoVinesBlock extends GrowingPlantHeadBlock {
    public static final MapCodec<EchoVinesBlock> CODEC = simpleCodec(EchoVinesBlock::new);
    private static final float CHANCE_OF_BERRIES_ON_GROWTH = 0.08F;

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    @Override
    public MapCodec<EchoVinesBlock> codec() {
        return CODEC;
    }

    public EchoVinesBlock(BlockBehaviour.Properties properties) {

        super(properties, Direction.DOWN, SHAPE, false, 0.1);
        registerDefaultState(stateDefinition.any().setValue(BlockStateProperties.BERRIES, false));
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.ECHO_VINES_PLANT;
    }

    @Override
    protected BlockState updateBodyAfterConvertedFromHead(BlockState headState, BlockState bodyState) {
        return bodyState.setValue(BlockStateProperties.BERRIES, headState.getValue(BlockStateProperties.BERRIES));
    }

    @Override
    protected BlockState getGrowIntoState(BlockState state, RandomSource random) {
        return super.getGrowIntoState(state, random).setValue(BlockStateProperties.BERRIES, random.nextFloat() < CHANCE_OF_BERRIES_ON_GROWTH);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.ECHOBERRY);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return ModVines.pickBerries(state, level, pos, player, ModItems.ECHOBERRY);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.BERRIES);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !state.getValue(BlockStateProperties.BERRIES);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(net.minecraft.server.level.ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(BlockStateProperties.BERRIES, true), 2);
    }
}
