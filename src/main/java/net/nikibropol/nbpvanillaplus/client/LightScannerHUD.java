package net.nikibropol.nbpvanillaplus.client;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.item.custom.LightScannerItem;

public class LightScannerHUD {

    public static void register() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "light_scanner_hud"),
                LightScannerHUD::render
        );
    }

    private static boolean hasActiveScanner(Inventory inventory) {
        for (int slotIndex = 0; slotIndex < inventory.getContainerSize(); slotIndex++) {
            ItemStack stack = inventory.getItem(slotIndex);
            if (stack.getItem() instanceof LightScannerItem && LightScannerItem.isHudEnabled(stack)) {
                return true;
            }
        }
        return false;
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null || minecraft.gui.hud.isHidden()) {
            return;
        }
        if (!hasActiveScanner(minecraft.player.getInventory())) {
            return;
        }

        BlockPos position = minecraft.player.blockPosition();
        int blockLight = minecraft.level.getBrightness(LightLayer.BLOCK, position);

        Component text = Component.translatable("hud.nbp-vanilla-plus.light_scanner", blockLight);
        int color = blockLight == 0 ? 0xFFFF5555 : 0xFFFFFFFF;

        int textWidth = minecraft.font.width(text);
        int x = (graphics.guiWidth() - textWidth) / 2;
        int y = graphics.guiHeight() - 93;

        graphics.text(minecraft.font, text, x, y, color, true);
    }
}
