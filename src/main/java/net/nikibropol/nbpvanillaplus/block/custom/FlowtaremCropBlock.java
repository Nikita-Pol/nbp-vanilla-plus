package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class FlowtaremCropBlock extends CropBlock {

    public static int MAX_AGE = 3;
    public static IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    public static final MapCodec<FlowtaremCropBlock> CODEC = simpleCodec(FlowtaremCropBlock::new);
    private static final VoxelShape[] SHAPES = Block.boxes(7, age -> Block.column(16.0, 0.0, 2 + age));

    public FlowtaremCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public MapCodec<FlowtaremCropBlock> codec() {
        return CODEC;
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.FLOWTAREM_SEEDS;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }
}
