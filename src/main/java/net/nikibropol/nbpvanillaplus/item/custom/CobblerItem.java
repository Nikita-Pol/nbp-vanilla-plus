package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class CobblerItem extends Item {

    private static final Map<Block, Block> COBBLER_MAP =
            Map.of(
                    Blocks.STONE, Blocks.COBBLESTONE,
                    Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE,
                    Blocks.STONE_SLAB, Blocks.COBBLESTONE_SLAB,
                    Blocks.STONE_STAIRS, Blocks.COBBLESTONE_STAIRS
            );

    public CobblerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // Right Click Block
        // Change Block from A to B...

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (COBBLER_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            level.setBlockAndUpdate(context.getClickedPos(), COBBLER_MAP.get(clickedBlock).defaultBlockState());
            Optional<BlockState> newBlock = this.evaluateNewBlockState(level, pos, player, level.getBlockState(pos));
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }


        return InteractionResult.SUCCESS;
    }

    private Optional<BlockState> getCobbled(final BlockState state) {
        return Optional.ofNullable(COBBLER_MAP.get(state.getBlock()))
                .map(block -> block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
    }

    private Optional<BlockState> evaluateNewBlockState(final Level level, final BlockPos pos, final @Nullable Player player, final BlockState oldState) {
        Optional<BlockState> cobbledBlock = this.getCobbled(oldState);
        if (cobbledBlock.isPresent()) {
            level.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
            return cobbledBlock;
        } else {
            return Optional.empty();
        }


    }


}

