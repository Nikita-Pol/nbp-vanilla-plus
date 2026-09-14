package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class EchoVinesPlantBlock extends GrowingPlantBodyBlock {

    public static final MapCodec<EchoVinesPlantBlock> CODEC = simpleCodec(EchoVinesPlantBlock::new);

    @Override
    public MapCodec<EchoVinesPlantBlock> codec() {
        return CODEC;
    }

    public EchoVinesPlantBlock(final BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, EchoVinesBlock.SHAPE, false);
        registerDefaultState(stateDefinition.any().setValue(BlockStateProperties.BERRIES, false));
    }


    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.ECHO_VINES;
    }

    @Override
    protected BlockState updateHeadAfterConvertedFromBody(final BlockState bodyState, final BlockState headState) {
        return headState.setValue(BlockStateProperties.BERRIES, bodyState.getValue(BlockStateProperties.BERRIES));
    }

    @Override
    protected ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state, final boolean includeData) {
        return new ItemStack(ModItems.ECHOBERRY);
    }

    @Override
    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        return ModVines.pickBerries(state, level, pos, player, ModItems.ECHOBERRY);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.BERRIES);
    }

    @Override
    public boolean isValidBonemealTarget(final LevelReader level, final BlockPos pos, final BlockState state) {
        return !state.getValue(BlockStateProperties.BERRIES);
    }

    @Override
    public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(final ServerLevel level, final RandomSource random, final BlockPos pos, final BlockState state) {
        level.setBlock(pos, state.setValue(BlockStateProperties.BERRIES, true), 2);
    }
}
