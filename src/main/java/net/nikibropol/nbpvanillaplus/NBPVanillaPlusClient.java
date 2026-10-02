package net.nikibropol.nbpvanillaplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.impl.client.renderer.RendererManager;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.entity.ModBlockEntities;
import net.nikibropol.nbpvanillaplus.block.entity.custom.ReinforcedShulkerBoxBlockEntity;
import net.nikibropol.nbpvanillaplus.block.entity.renderer.ReinforceShulkerBoxBlockEntityRenderer;
import net.nikibropol.nbpvanillaplus.menu.ModMenuTypes;
import net.nikibropol.nbpvanillaplus.menu.custom.ReinforcedShulkerBoxScreen;

import java.util.List;

public class NBPVanillaPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        leavesTint(0xFF4CAF50, ModBlocks.DREAM_GREEN_LEAVES);
        leavesTint(0xFF9C6BFF, ModBlocks.DREAM_PURPLE_LEAVES);
        leavesTint(0xFF3F6BFF, ModBlocks.DREAM_BLUE_LEAVES);
        leavesTint(0xFF7FD4FF, ModBlocks.DREAM_LIGHT_BLUE_LEAVES);

        BlockEntityRenderers.register(ModBlockEntities.REINFORCED_SHULKER_BOX_BE, ReinforceShulkerBoxBlockEntityRenderer::new);

        MenuScreens.register(ModMenuTypes.REINFORCED_SHULKER_BOX_MENU, ReinforcedShulkerBoxScreen::new);

    }
    private static void leavesTint(int color, Block block) {
        BlockColorRegistry.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return color;
            }
        }), block);
    }
}
