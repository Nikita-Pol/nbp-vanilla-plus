package net.nikibropol.nbpvanillaplus.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower DREAM_GREEN_WOOD = new TreeGrower(NBPVanillaPlus.MOD_ID + ":dream_green",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DREAM_GREEN_WOOD_KEY), Optional.empty());
    public static final TreeGrower DREAM_PURPLE_WOOD = new TreeGrower(NBPVanillaPlus.MOD_ID + ":dream_purple",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DREAM_PURPLE_WOOD_KEY), Optional.empty());
    public static final TreeGrower DREAM_BLUE_WOOD = new TreeGrower(NBPVanillaPlus.MOD_ID + ":dream_blue",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DREAM_BLUE_WOOD_KEY), Optional.empty());
    public static final TreeGrower DREAM_LIGHT_BLUE_WOOD = new TreeGrower(NBPVanillaPlus.MOD_ID + ":dream_light_blue",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DREAM_LIGHT_BLUE_WOOD_KEY), Optional.empty());

}
