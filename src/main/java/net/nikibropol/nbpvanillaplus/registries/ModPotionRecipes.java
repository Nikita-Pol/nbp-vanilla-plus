package net.nikibropol.nbpvanillaplus.registries;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.client.color.item.Potion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.potion.ModPotions;

public class ModPotionRecipes {
    public static void registerPotionRecipes(){
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(ModItems.ECHOBERRY), ModPotions.WEAK_SILENCE_POTION);
            builder.registerPotionRecipe(ModPotions.WEAK_SILENCE_POTION, Ingredient.of(Items.ECHO_SHARD), ModPotions.SHORT_SILENCE_POTION);
            builder.registerPotionRecipe(ModPotions.SHORT_SILENCE_POTION, Ingredient.of(ModBlocks.ECHO_MAGMA), ModPotions.SILENCE_POTION);
            builder.registerPotionRecipe(ModPotions.SILENCE_POTION, Ingredient.of(ModItems.FUSED_ECHO_SHARD), ModPotions.LONG_SILENCE_POTION);
        });
    }
}
