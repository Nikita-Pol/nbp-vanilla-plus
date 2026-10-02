package net.nikibropol.nbpvanillaplus.menu.custom;


import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ReinforcedShulkerBoxScreen extends AbstractContainerScreen<ReinforcedShulkerBoxMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,
            "textures/gui/reinforcedshulkerbox/reinforced_shulker_box_gui.png");

    public ReinforcedShulkerBoxScreen(ReinforcedShulkerBoxMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 222);
    }


    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0,
                imageWidth, imageHeight, 256, 256);
    }
}
