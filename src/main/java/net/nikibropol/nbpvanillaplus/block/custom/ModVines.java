package net.nikibropol.nbpvanillaplus.block.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

import static net.minecraft.world.level.block.Block.popResource;

public class ModVines {

    public static InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, Item berryItem, SoundEvent pickSound) {
        return pickBerries(state, level, pos, player, berryItem);
    }

    public static InteractionResult pickBerries(BlockState state, Level level, BlockPos pos, Player player, Item berryItem) {
        if (state.getValue(BlockStateProperties.BERRIES)) {
            popResource(level, pos, new ItemStack(berryItem));
            level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
            BlockState newState = state.setValue(BlockStateProperties.BERRIES, false);
            level.setBlock(pos, newState, 2);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.PASS;
    }
}
