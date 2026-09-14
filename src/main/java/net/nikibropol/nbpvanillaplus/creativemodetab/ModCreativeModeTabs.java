package net.nikibropol.nbpvanillaplus.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class ModCreativeModeTabs {

    public static final CreativeModeTab NBP_MOD_CONTENT_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "mod_content"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack((ModItems.ECHO_INGOT)))
                    .title(Component.translatable("creativemodetab.nbp-vanilla-plus.mod_content"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.REINFORCED_OBSIDIAN);
                        output.accept(ModBlocks.OBSIDIAN_REDSTONE_LAMP);
                        output.accept(ModBlocks.ECHO_MAGMA);
                        output.accept(ModBlocks.ECHO_BLOCK);

                        output.accept(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE);

                        output.accept(ModItems.ECHO_FRAGMENT);
                        output.accept(ModItems.FUSED_ECHO_SHARD);
                        output.accept(ModItems.RECOVERY_SHARD);
                        output.accept(ModItems.ECHO_SCRAP);
                        output.accept(ModItems.ECHO_INGOT);

                        output.accept(ModItems.ECHO_SWORD);
                        output.accept(ModItems.ECHO_PICKAXE);
                        output.accept(ModItems.ECHO_SHOVEL);
                        output.accept(ModItems.ECHO_AXE);
                        output.accept(ModItems.ECHO_HOE);
                        output.accept(ModItems.ECHO_SPEAR);
                        output.accept(ModItems.ECHO_BOW);

                        output.accept(ModItems.ECHO_HELMET);
                        output.accept(ModItems.ECHO_CHESTPLATE);
                        output.accept(ModItems.ECHO_LEGGINGS);
                        output.accept(ModItems.ECHO_BOOTS);

                        output.accept(ModItems.ECHO_HORSE_ARMOR);
                        output.accept(ModItems.ECHO_NAUTILUS_ARMOR);

                        output.accept(ModItems.STRANGE_BEETROOT_SEEDS);
                        output.accept(ModItems.DREAM_SEEDS);
                        output.accept(ModBlocks.DREAM_PETALS_GREEN);
                        output.accept(ModBlocks.DREAM_PETALS_BLUE);
                        output.accept(ModBlocks.DREAM_PETALS_PURPLE);
                        output.accept(ModBlocks.DREAM_PETALS_LIGHT_BLUE);

                        output.accept(ModItems.ECHOBERRY);
                        output.accept(ModItems.AMETHYST_SWEET_BERRIES);
                        output.accept(ModItems.STRANGE_BEETROOT);

                        output.accept(ModItems.COBBLER);

                    })
                    .build());


    public static void registerModCreativeTabs(){
        NBPVanillaPlus.LOGGER.info("Registering Creative Mode Tabs for " + NBPVanillaPlus.MOD_ID);
    }
}
