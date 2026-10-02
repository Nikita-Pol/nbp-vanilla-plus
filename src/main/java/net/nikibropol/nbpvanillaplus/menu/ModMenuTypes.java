package net.nikibropol.nbpvanillaplus.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.menu.custom.ReinforcedShulkerBoxMenu;

public class ModMenuTypes {

    public static final MenuType<ReinforcedShulkerBoxMenu> REINFORCED_SHULKER_BOX_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker_box_menu"),
                    new ExtendedMenuType<>(ReinforcedShulkerBoxMenu::new, BlockPos.STREAM_CODEC));



    public static void registerModMenuTypes(){
        NBPVanillaPlus.LOGGER.info("Registering ModMenuTypes for " + NBPVanillaPlus.MOD_ID);
    }

}
