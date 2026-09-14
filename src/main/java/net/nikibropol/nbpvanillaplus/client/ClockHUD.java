package net.nikibropol.nbpvanillaplus.client;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ClockHUD {
    public static void register() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "clock_hud"),
                ClockHUD::render
        );
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.gui.hud.isHidden()) return;

        boolean hasClock = mc.player.getInventory().contains(itemStack -> itemStack.is(Items.CLOCK));
        if (!hasClock) return;

        assert mc.level != null;
        var dayCounter = mc.level.getDefaultClockTime();
        var day = dayCounter / 24000;
        String text = String.format("Day: %d", day);

        int textWidth = mc.font.width(text);
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight - 70;

        graphics.text(mc.font, text, x, y, -1, true);
    }
}
