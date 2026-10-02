package net.nikibropol.nbpvanillaplus.block.state.properties;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.Map;

public class ModWoodType {

    public static final WoodType DREAM_GREEN = register("dream_green");
    public static final WoodType DREAM_PURPLE = register("dream_purple");
    public static final WoodType DREAM_BLUE = register("dream_blue");
    public static final WoodType DREAM_LIGHT_BLUE = register("dream_light_blue");

    private static WoodType register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name);
        BlockSetType setType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(id);
        return WoodTypeBuilder.copyOf(WoodType.OAK).register(id, setType);
    }
    public static void registerModWoodTypes() {
        NBPVanillaPlus.LOGGER.info("Registering Mod Wood Types for " + NBPVanillaPlus.MOD_ID);
    }
}
