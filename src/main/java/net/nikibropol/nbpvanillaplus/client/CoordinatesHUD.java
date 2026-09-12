package net.nikibropol.nbpvanillaplus.client;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class CoordinatesHUD {
    public static void register() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "coordinates_hud"),
                CoordinatesHUD::render
        );
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.gui.hud.isHidden()) return;

        ItemStack main = mc.player.getMainHandItem();
        ItemStack off = mc.player.getOffhandItem();
        if (!main.is(Items.COMPASS) && !off.is(Items.COMPASS)) return;

        var pos = mc.player.blockPosition();
        String text = String.format("X: %d  Y: %d  Z: %d", pos.getX(), pos.getY(), pos.getZ());

        int textWidth = mc.font.width(text);
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight - 81;

        graphics.text(mc.font, text, x, y, -1, true);
    }
}