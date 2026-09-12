package net.nikibropol.nbpvanillaplus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class DreamFlowerBlock extends CropBlock {

    public DreamFlowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId(){
        return ModItems.DREAM_SEEDS;
    }
    protected boolean canSurvive (BlockState state, LevelReader level, BlockPos pos){
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(Blocks.WATER);
    }
}
