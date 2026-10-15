package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.List;
import java.util.function.Consumer;

public class LightScannerItem extends Item {

    private final Component[] tooltips;

    public LightScannerItem(Properties properties, Component... tooltips) {
        super(properties);
        this.tooltips = tooltips;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        for (Component tooltip : tooltips) {
            builder.accept(tooltip);
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    public static boolean isHudEnabled(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
    }

    private static int getStoredLightLevel(ItemStack stack) {
        CustomModelData customModelData = stack.get(DataComponents.CUSTOM_MODEL_DATA);
        if (customModelData == null) {
            return -1;
        }
        Float lightLevel = customModelData.getFloat(0);
        return lightLevel == null ? -1 : lightLevel.intValue();
    }

    private static void writeLightLevel(ItemStack stack, int lightLevel) {
        stack.set(DataComponents.CUSTOM_MODEL_DATA,
                new CustomModelData(List.of((float) lightLevel), List.of(), List.of(), List.of()));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean newHudState = !isHudEnabled(stack);

        if (!level.isClientSide()) {
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, newHudState);
        }
        level.playSound(player, player.blockPosition(), SoundEvents.UI_BUTTON_CLICK.value(),
                SoundSource.PLAYERS, 0.6f, newHudState ? 1.2f : 0.8f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if (entity.tickCount % 5 != 0) {
            return;
        }
        int measuredLightLevel = level.getBrightness(LightLayer.BLOCK, entity.blockPosition());
        if (measuredLightLevel != getStoredLightLevel(stack)) {
            writeLightLevel(stack, measuredLightLevel);
        }
    }
}
