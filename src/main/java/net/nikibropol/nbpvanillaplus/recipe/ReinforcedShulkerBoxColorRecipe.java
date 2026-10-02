package net.nikibropol.nbpvanillaplus.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;

public class ReinforcedShulkerBoxColorRecipe extends CustomRecipe {

    public ReinforcedShulkerBoxColorRecipe() {
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean foundBox = false;
        boolean foundDye = false;
        DyeColor currentColor = null;
        DyeColor newColor = null;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (isReinforcedShulkerBox(stack)) {
                if (foundBox) return false;
                foundBox = true;
                currentColor = getColorOf(stack);
            } else if (stack.getItem() instanceof DyeItem) {
                if (foundDye) return false;
                foundDye = true;
                newColor = stack.get(DataComponents.DYE);
            } else {
                return false;
            }
        }
        return foundBox && foundDye && newColor != null && newColor != currentColor;
    }

    private DyeColor getColorOf(ItemStack stack) {
        Block block = Block.byItem(stack.getItem());
        for (var entry : ModBlocks.REINFORCED_SHULKER_BOXES.entrySet()) {
            if (entry.getValue() == block) return entry.getKey();
        }
        return null;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack box = ItemStack.EMPTY;
        DyeColor newColor = null;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (isReinforcedShulkerBox(stack)) {
                box = stack;
            } else if (stack.getItem() instanceof DyeItem) {
                newColor = stack.get(DataComponents.DYE);
                System.out.println("[DEBUG] dye stack=" + stack.getItem() + " color=" + newColor);
            }
        }
        System.out.println("[DEBUG] box=" + box + " newColor=" + newColor);
        if (box.isEmpty() || newColor == null) return ItemStack.EMPTY;

        Block resultBlock = ModBlocks.REINFORCED_SHULKER_BOXES.get(newColor);
        System.out.println("[DEBUG] resultBlock=" + resultBlock);

        ItemStack result = new ItemStack(resultBlock);
        ItemContainerContents contents = box.get(DataComponents.CONTAINER);
        if (contents != null) {
            result.set(DataComponents.CONTAINER, contents);
        }

        Component customName = box.get(DataComponents.CUSTOM_NAME);
        if (customName != null) {
            result.set(DataComponents.CUSTOM_NAME, customName);
        }
        return result;
    }

    private boolean isReinforcedShulkerBox(ItemStack stack) {
        Block block = Block.byItem(stack.getItem());
        return ModBlocks.REINFORCED_SHULKER_BOXES.containsValue(block) || block == ModBlocks.REINFORCED_SHULKER_BOX;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        return CraftingRecipe.defaultCraftingReminder(input);
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipes.REINFORCED_SHULKER_BOX_COLOR;
    }
}