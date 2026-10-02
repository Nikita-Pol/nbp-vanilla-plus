package net.nikibropol.nbpvanillaplus.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.function.Supplier;

public class ModRecipes {

    public static final RecipeSerializer<ReinforcedShulkerBoxColorRecipe> REINFORCED_SHULKER_BOX_COLOR =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker_box_color"),
                    new RecipeSerializer<>(
                            MapCodec.unit(ReinforcedShulkerBoxColorRecipe::new),
                            StreamCodec.unit(new ReinforcedShulkerBoxColorRecipe())
                    ));

    public static void registerModRecipes() {
        NBPVanillaPlus.LOGGER.info("Registering Mod Recipes for " + NBPVanillaPlus.MOD_ID);
    }
}
