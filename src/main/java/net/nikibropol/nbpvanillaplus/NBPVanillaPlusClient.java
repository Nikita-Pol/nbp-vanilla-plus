package net.nikibropol.nbpvanillaplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.entity.ModBlockEntities;
import net.nikibropol.nbpvanillaplus.block.entity.renderer.ReinforceShulkerBoxBlockEntityRenderer;
import net.nikibropol.nbpvanillaplus.client.*;
import net.nikibropol.nbpvanillaplus.entity.ModEntities;
import net.nikibropol.nbpvanillaplus.entity.client.EchoArrowRenderer;
import net.nikibropol.nbpvanillaplus.entity.client.EchoCreakingRenderer;
import net.nikibropol.nbpvanillaplus.menu.ModMenuTypes;
import net.nikibropol.nbpvanillaplus.menu.custom.ReinforcedShulkerBoxScreen;
import net.nikibropol.nbpvanillaplus.worldgen.biome.Biomes.PaleGardenMusicHandler;

import java.util.List;

public class NBPVanillaPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        leavesTint(0xFF4CAF50, ModBlocks.DREAM_GREEN_LEAVES);
        leavesTint(0xFF9C6BFF, ModBlocks.DREAM_PURPLE_LEAVES);
        leavesTint(0xFF3F6BFF, ModBlocks.DREAM_BLUE_LEAVES);
        leavesTint(0xFF7FD4FF, ModBlocks.DREAM_LIGHT_BLUE_LEAVES);

        CoordinatesHUD.register();
        ClockHUD.register();
        LightScannerHUD.register();

        EntityRendererRegistry.register(ModEntities.ECHO_ARROW, EchoArrowRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.REINFORCED_SHULKER_BOX_BE, ReinforceShulkerBoxBlockEntityRenderer::new);

        MenuScreens.register(ModMenuTypes.REINFORCED_SHULKER_BOX_MENU, ReinforcedShulkerBoxScreen::new);
        PaleFogClient.register();
        PaleGrassTint.register();
        PaleGardenMusicHandler.register();

        EntityRendererRegistry.register(ModEntities.ECHO_CREAKING, EchoCreakingRenderer::new);

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
