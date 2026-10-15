package net.nikibropol.nbpvanillaplus.datagen;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.List;

public class ModLootHelper {
    public record Color(String name, ItemLike petals, ItemLike log, ItemLike planks,
                        ItemLike sapling, ItemLike wool, ItemLike disc_fragment, ItemLike dye, ItemLike candle) {}

    public static final List<Color> COLORS = List.of(
            new Color("green", ModBlocks.DREAM_PETALS_GREEN, ModBlocks.DREAM_GREEN_LOG,
                    ModBlocks.DREAM_GREEN_PLANKS, ModBlocks.DREAM_GREEN_SAPLING, Blocks.WOOL.green(),
                    ModItems.DISC_FRAGMENT_GREEN_DREAMS, Items.DYE.green(), Items.DYED_CANDLE.green()),
            new Color("purple", ModBlocks.DREAM_PETALS_PURPLE, ModBlocks.DREAM_PURPLE_LOG,
                    ModBlocks.DREAM_PURPLE_PLANKS, ModBlocks.DREAM_PURPLE_SAPLING, Blocks.WOOL.purple(),
                    ModItems.DISC_FRAGMENT_PURPLE_DREAMS, Items.DYE.purple(), Items.DYED_CANDLE.purple()),
            new Color("blue", ModBlocks.DREAM_PETALS_BLUE, ModBlocks.DREAM_BLUE_LOG,
                    ModBlocks.DREAM_BLUE_PLANKS, ModBlocks.DREAM_BLUE_SAPLING, Blocks.WOOL.blue(),
                    ModItems.DISC_FRAGMENT_BLUE_DREAMS, Items.DYE.blue(), Items.DYED_CANDLE.blue()),
            new Color("light_blue", ModBlocks.DREAM_PETALS_LIGHT_BLUE, ModBlocks.DREAM_LIGHT_BLUE_LOG,
                    ModBlocks.DREAM_LIGHT_BLUE_PLANKS, ModBlocks.DREAM_LIGHT_BLUE_SAPLING, Blocks.WOOL.lightBlue(),
                    ModItems.DISC_FRAGMENT_LIGHT_BLUE_DREAMS, Items.DYE.lightBlue(), Items.DYED_CANDLE.lightBlue()));

    public static LootPoolSingletonContainer.Builder<?> entry(ItemLike item, int weight, int min, int max) {
        var entr = LootItem.lootTableItem(item).setWeight(weight);
        if (max > 1) entr = entr.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        return entr;
    }

    public static LootPool.Builder rare(ItemLike item, float chance, int min, int max) {
        var entr = LootItem.lootTableItem(item);
        if (max > 1) entr = entr.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(chance))
                .add(entr);
    }
}