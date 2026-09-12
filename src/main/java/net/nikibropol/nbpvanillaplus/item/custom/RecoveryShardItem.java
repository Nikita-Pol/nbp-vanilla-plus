package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.nikibropol.nbpvanillaplus.data.DeathRecord;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class RecoveryShardItem extends Item {

    public RecoveryShardItem(Properties properties) {

        super(properties);
    }
    public static RecoveryShardItem create() {

        return null;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        if (level.isClientSide() || player == null) return InteractionResult.PASS;

        if (!level.getBlockState(clickedPos).is(Blocks.SCULK_CATALYST)) {
            return InteractionResult.PASS;
        }

        Optional<GlobalPos> lastDeath = player.getLastDeathLocation();
        ItemStack itemStack = context.getItemInHand();

        if (lastDeath.isEmpty() ) {
            player.sendSystemMessage(Component.translatable("item.nbpvanillaplus.recovery_shard.no_death_yet"));
            itemStack.set(ModDataComponents.LAST_DEATH_COORDINATES, new DeathRecord(lastDeath.get(), ""));
            itemStack.set(ModDataComponents.IS_ACTUAL, false);
            return InteractionResult.FAIL;
        }

        DeathRecord record = new DeathRecord(lastDeath.get(), player.getGameProfile().name());
        itemStack.set(ModDataComponents.LAST_DEATH_COORDINATES, record);
        itemStack.set(ModDataComponents.IS_ACTUAL, true);

        level.playSound(null, clickedPos, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.BLOCKS, 1.0F, 1.0F);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {


        DeathRecord deathRecord = itemStack.get(ModDataComponents.LAST_DEATH_COORDINATES);

        if (deathRecord != null) {
            BlockPos pos = deathRecord.pos().pos();
            builder.accept(Component.literal(deathRecord.playerName())
                    .withStyle(ChatFormatting.GRAY));
            builder.accept(Component.literal(String.format("X: %d Y: %d Z: %d", pos.getX(), pos.getY(), pos.getZ()))
                    .withStyle(ChatFormatting.DARK_GRAY));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if (level.isClientSide() || !(entity instanceof ServerPlayer player)) return;

        if (entity.tickCount % 20 != 0) return;

        DeathRecord record = itemStack.get(ModDataComponents.LAST_DEATH_COORDINATES);
        boolean actual;

        if (record == null) {
            actual = false;

        } else {
            Optional<GlobalPos> currentDeath = player.getLastDeathLocation();
            boolean nameMatches = record.playerName().equals(player.getGameProfile().name());
            boolean posMatches = currentDeath.isPresent() && currentDeath.get().equals(record.pos());
            actual = nameMatches && posMatches;
        }

        itemStack.set(ModDataComponents.IS_ACTUAL, actual);
    }
}
