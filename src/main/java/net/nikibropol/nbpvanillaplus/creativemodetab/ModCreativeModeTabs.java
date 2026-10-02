package net.nikibropol.nbpvanillaplus.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
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
                        output.accept(ModBlocks.DREAMED_OBSIDIAN);
                        output.accept(ModBlocks.DREAMED_POWERED_OBSIDIAN);
                        output.accept(ModBlocks.OBSIDIAN_REDSTONE_LAMP);
                        output.accept(ModBlocks.ECHO_MAGMA);
                        output.accept(ModBlocks.ECHO_BLOCK);
                        output.accept(ModBlocks.DREAM_ECHO_STATUE);

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
                        output.accept(ModItems.ECHO_ARROW);

                        output.accept(ModItems.ECHO_HELMET);
                        output.accept(ModItems.ECHO_CHESTPLATE);
                        output.accept(ModItems.ECHO_LEGGINGS);
                        output.accept(ModItems.ECHO_BOOTS);

                        output.accept(ModItems.ECHO_HORSE_ARMOR);
                        output.accept(ModItems.ECHO_NAUTILUS_ARMOR);

                        output.accept(ModItems.STRANGE_BEETROOT_SEEDS);
                        output.accept(ModItems.FLOWTAREM_SEEDS);
                        output.accept(ModItems.DREAM_SEEDS);

                        output.accept(ModItems.ECHOBERRY);
                        output.accept(ModItems.AMETHYST_SWEET_BERRIES);
                        output.accept(ModItems.STRANGE_BEETROOT);
                        output.accept(ModItems.FLOWTAREM);
                        output.accept(ModItems.RESIN_FLOWTAREM);

                        output.accept(ModItems.BUNCH_OF_SUGAR_CANE);
                        output.accept(ModItems.BUNCH_OF_CHORUS_FRUIT);
                        output.accept(ModItems.BUNCH_OF_CARROT);
                        output.accept(ModItems.BUNCH_OF_APPLE);
                        output.accept(ModItems.BUNCH_OF_POTATO);
                        output.accept(ModItems.BUNCH_OF_BEETROOT);
                        output.accept(ModItems.BUNCH_OF_SWEET_BERRIES);
                        output.accept(ModItems.BUNCH_OF_STRANGE_BEETROOT);
                        output.accept(ModItems.BUNCH_OF_AMETHYST_SWEET_BERRIES);
                        output.accept(ModItems.BUNCH_OF_GLOW_BERRIES);
                        output.accept(ModItems.BUNCH_OF_ECHO_BERRIES);
                        output.accept(ModItems.BUNCH_OF_FLOWTAREM);
                        output.accept(ModItems.BUNCH_OF_RESIN_FLOWTAREM);

                        output.accept(ModItems.COBBLER);
                        output.accept(ModItems.EXTENDER);

                        output.accept(ModItems.ONLY_ONCE_MORE_MUSIC_DISC);
                        output.accept(ModItems.DUSK_TO_DAWN_MUSIC_DISC);
                        output.accept(ModItems.BROKEN_ETERNITY_MUSIC_DISC);

                        output.accept(ModBlocks.DREAM_GREEN_WOOD);
                        output.accept(ModBlocks.DREAM_GREEN_LOG);
                        output.accept(ModBlocks.DREAM_GREEN_LEAVES);
                        output.accept(ModBlocks.STRIPPED_DREAM_GREEN_WOOD);
                        output.accept(ModBlocks.STRIPPED_DREAM_GREEN_LOG);
                        output.accept(ModBlocks.DREAM_GREEN_PLANKS);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_STAIRS);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_SLAB);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_BUTTON);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_PRESSURE_PLATE);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_FENCE);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_FENCE_GATE);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_DOOR);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_TRAPDOOR);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_SIGN);
                        output.accept(ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN);
                        output.accept(ModBlocks.DREAM_GREEN_SAPLING);
                        output.accept(ModBlocks.DREAM_PETALS_GREEN);

                        output.accept(ModBlocks.DREAM_PURPLE_WOOD);
                        output.accept(ModBlocks.DREAM_PURPLE_LOG);
                        output.accept(ModBlocks.DREAM_PURPLE_LEAVES);
                        output.accept(ModBlocks.STRIPPED_DREAM_PURPLE_WOOD);
                        output.accept(ModBlocks.STRIPPED_DREAM_PURPLE_LOG);
                        output.accept(ModBlocks.DREAM_PURPLE_PLANKS);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_STAIRS);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_SLAB);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_BUTTON);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_PRESSURE_PLATE);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_FENCE);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_FENCE_GATE);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_DOOR);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_TRAPDOOR);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_SIGN);
                        output.accept(ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN);
                        output.accept(ModBlocks.DREAM_PURPLE_SAPLING);
                        output.accept(ModBlocks.DREAM_PETALS_PURPLE);

                        output.accept(ModBlocks.DREAM_BLUE_WOOD);
                        output.accept(ModBlocks.DREAM_BLUE_LOG);
                        output.accept(ModBlocks.DREAM_BLUE_LEAVES);
                        output.accept(ModBlocks.STRIPPED_DREAM_BLUE_WOOD);
                        output.accept(ModBlocks.STRIPPED_DREAM_BLUE_LOG);
                        output.accept(ModBlocks.DREAM_BLUE_PLANKS);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_STAIRS);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_SLAB);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_BUTTON);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_PRESSURE_PLATE);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_FENCE);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_FENCE_GATE);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_DOOR);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_TRAPDOOR);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_SIGN);
                        output.accept(ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN);
                        output.accept(ModBlocks.DREAM_BLUE_SAPLING);
                        output.accept(ModBlocks.DREAM_PETALS_BLUE);

                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_LOG);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_LEAVES);
                        output.accept(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_WOOD);
                        output.accept(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_PLANKS);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_STAIRS);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SLAB);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_BUTTON);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE_GATE);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_DOOR);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_TRAPDOOR);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN);
                        output.accept(ModBlocks.DREAM_LIGHT_BLUE_SAPLING);
                        output.accept(ModBlocks.DREAM_PETALS_LIGHT_BLUE);

                        output.accept(ModBlocks.REINFORCED_SHULKER_BOX);
                        for (DyeColor color : DyeColor.values()) {
                            output.accept(ModBlocks.REINFORCED_SHULKER_BOXES.get(color));
                        }

                    })
                    .build());


    public static void registerModCreativeTabs(){
        NBPVanillaPlus.LOGGER.info("Registering Creative Mode Tabs for " + NBPVanillaPlus.MOD_ID);
    }
}
