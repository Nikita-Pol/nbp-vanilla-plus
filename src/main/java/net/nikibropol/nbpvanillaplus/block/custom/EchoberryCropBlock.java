package net.nikibropol.nbpvanillaplus.block.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class EchoberryCropBlock extends CropBlock {

    public EchoberryCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return super.getBaseSeedId();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return super.getAgeProperty();
    }

    @Override
    public int getMaxAge() {
        return super.getMaxAge();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }


}
