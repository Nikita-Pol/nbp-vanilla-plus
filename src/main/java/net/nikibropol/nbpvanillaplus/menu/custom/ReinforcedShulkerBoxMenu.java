package net.nikibropol.nbpvanillaplus.menu.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.nikibropol.nbpvanillaplus.menu.ModMenuTypes;

public class ReinforcedShulkerBoxMenu extends AbstractContainerMenu {
    private final Container inventory;

    private static final int TE_ROWS = 6;
    private static final int TE_COLUMNS = 9;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_SLOT_COUNT = 54;
    private static final int VANILLA_FIRST_SLOT_INDEX = TE_INVENTORY_SLOT_COUNT;

    public ReinforcedShulkerBoxMenu(int containerId, Inventory inv, BlockPos blockPos) {
        this(containerId, inv, inv.player.level().getBlockEntity(blockPos));
    }

    public ReinforcedShulkerBoxMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.REINFORCED_SHULKER_BOX_MENU, containerId);
        this.inventory = (Container) blockEntity;
        this.inventory.startOpen(inv.player);

        for (int row = 0; row < TE_ROWS; row++) {
            for (int col = 0; col < TE_COLUMNS; col++) {
                addSlot(new Slot(inventory, col + row * TE_COLUMNS, 8 + col * 18, 18 + row * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return inventory.canPlaceItem(getContainerSlot(), stack);
                    }
                });
            }
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, slots.size(), false)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.inventory.stopOpen(player);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int yOffset = 18 + TE_ROWS * 18 + 14;
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, yOffset + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        int y = 18 + TE_ROWS * 18 + 14 + 3 * 18 + 4;
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, y));
        }
    }
}