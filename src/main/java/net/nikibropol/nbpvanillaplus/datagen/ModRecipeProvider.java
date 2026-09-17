package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> SMELTABLE_OBSIDIAN = List.of(Items.OBSIDIAN);

                oreSmelting(SMELTABLE_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.REINFORCED_OBSIDIAN, 5f, 1600, "reinforced_obsidian");

                shaped(RecipeCategory.MISC, ModBlocks.ECHO_MAGMA, 8)
                        .pattern("MMM")
                        .pattern("MEM")
                        .pattern("MMM")
                        .define('E', Items.ECHO_SHARD)
                        .define('M', Items.MAGMA_BLOCK)
                        .unlockedBy(getHasName(Items.MAGMA_BLOCK), has(Items.MAGMA_BLOCK))
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .group("echo_magma")
                        .save(output, "echo_magma_craft");

                shaped(RecipeCategory.MISC, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("DDD")
                        .pattern("DED")
                        .pattern("DMD")
                        .define('E', ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE)
                        .define('D', Items.DIAMOND)
                        .define('M', ModBlocks.ECHO_MAGMA)
                        .unlockedBy(getHasName(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE), has(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE))
                        .group("echo_upgrade_smithing_template")
                        .save(output, "echo_upgrade_smithing_template_craft_duplicate");

                shaped(RecipeCategory.MISC, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("DDD")
                        .pattern("DAD")
                        .pattern("DOD")
                        .define('A', ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE)
                        .define('D', Items.DIAMOND)
                        .define('O', ModBlocks.REINFORCED_OBSIDIAN)
                        .unlockedBy(getHasName(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE), has(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE))
                        .group("amethyst_upgrade_smithing_template")
                        .save(output, "amethyst_upgrade_smithing_template_craft_duplicate");

                shaped(RecipeCategory.MISC, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("EEE")
                        .pattern("ENE")
                        .pattern("EEE")
                        .define('E', ModItems.ECHO_SCRAP)
                        .define('N', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .unlockedBy(getHasName(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .group("echo_upgrade_smithing_template")
                        .save(output, "echo_upgrade_smithing_template_craft");

                shaped(RecipeCategory.MISC, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("AAA")
                        .pattern("ANA")
                        .pattern("AAA")
                        .define('N', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .define('A', Blocks.AMETHYST_BLOCK)
                        .unlockedBy(getHasName(Blocks.AMETHYST_BLOCK), has(Blocks.AMETHYST_BLOCK))
                        .unlockedBy(getHasName(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .group("amethyst_upgrade_smithing_template")
                        .save(output, "amethyst_upgrade_smithing_template_craft");

                shaped(RecipeCategory.MISC, ModItems.ECHO_SCRAP)
                        .pattern("EEE")
                        .pattern("ENE")
                        .pattern("EEE")
                        .define('E', Items.ECHO_SHARD)
                        .define('N', Items.NETHERITE_SCRAP)
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                        .group("echo_scrap")
                        .save(output, "echo_scrap_craft");

                shaped(RecipeCategory.MISC, Items.ECHO_SHARD, 4)
                        .pattern("VCE")
                        .pattern("SKD")
                        .pattern("BWG")
                        .define('V', Items.SCULK_VEIN)
                        .define('C', Blocks.SCULK_CATALYST)
                        .define('E', Items.EXPERIENCE_BOTTLE)
                        .define('S', Items.SCULK_SENSOR)
                        .define('K', Items.CALIBRATED_SCULK_SENSOR)
                        .define('D', Items.MUSIC_DISC_5)
                        .define('B', Blocks.SCULK)
                        .define('W', Items.SCULK_SHRIEKER)
                        .define('G', Items.ENCHANTED_GOLDEN_APPLE)
                        .unlockedBy(getHasName(Items.SCULK_VEIN), has(Items.SCULK_VEIN))
                        .unlockedBy(getHasName(Blocks.SCULK_CATALYST), has(Blocks.SCULK_CATALYST))
                        .unlockedBy(getHasName(Items.EXPERIENCE_BOTTLE), has(Items.EXPERIENCE_BOTTLE))
                        .unlockedBy(getHasName(Items.SCULK_SENSOR), has(Items.SCULK_SENSOR))
                        .unlockedBy(getHasName(Items.CALIBRATED_SCULK_SENSOR), has(Items.CALIBRATED_SCULK_SENSOR))
                        .unlockedBy(getHasName(Items.MUSIC_DISC_5), has(Items.MUSIC_DISC_5))
                        .unlockedBy(getHasName(Blocks.SCULK), has(Items.SCULK))
                        .unlockedBy(getHasName(Items.SCULK_SHRIEKER), has(Items.SCULK_SHRIEKER))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .group("echo_shard")
                        .save(output, "echo_shard_craft");

                shaped(RecipeCategory.MISC, ModItems.ECHOBERRY)
                        .pattern("SSS")
                        .pattern("SBS")
                        .pattern("SSS")
                        .define('S', Items.SCULK_VEIN)
                        .define('B', Items.GLOW_BERRIES)
                        .unlockedBy(getHasName(Items.SCULK_VEIN), has(Items.SCULK_VEIN))
                        .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                        .group("echoberry")
                        .save(output, "echoberry_sculk_craft");

                shaped(RecipeCategory.MISC, ModItems.ECHOBERRY, 8)
                        .pattern("BBB")
                        .pattern("BEB")
                        .pattern("BBB")
                        .define('E', Items.ECHO_SHARD)
                        .define('B', Items.GLOW_BERRIES)
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                        .group("echoberry")
                        .save(output, "echoberry_shard_craft");

                shaped(RecipeCategory.MISC, ModItems.AMETHYST_SWEET_BERRIES, 8)
                        .pattern("BBB")
                        .pattern("BAB")
                        .pattern("BBB")
                        .define('A', Items.AMETHYST_SHARD)
                        .define('B', Items.SWEET_BERRIES)
                        .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                        .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                        .group("amethyst_sweet_berries")
                        .save(output, "amethyst_sweet_berries_shard_craft");

                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.CARROT, RecipeCategory.FOOD, ModItems.BUNCH_OF_CARROT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.APPLE, RecipeCategory.FOOD, ModItems.BUNCH_OF_APPLE);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.POTATO, RecipeCategory.FOOD, ModItems.BUNCH_OF_POTATO);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.BEETROOT, RecipeCategory.FOOD, ModItems.BUNCH_OF_BEETROOT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.STRANGE_BEETROOT, RecipeCategory.FOOD, ModItems.BUNCH_OF_STRANGE_BEETROOT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.FOOD, ModItems.BUNCH_OF_SWEET_BERRIES);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.AMETHYST_SWEET_BERRIES, RecipeCategory.FOOD, ModItems.BUNCH_OF_AMETHYST_SWEET_BERRIES);

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.ECHO_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_BLOCK);

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.ECHO_FRAGMENT, RecipeCategory.MISC, Items.ECHO_SHARD);

                shapeless(RecipeCategory.REDSTONE, ModBlocks.OBSIDIAN_REDSTONE_LAMP)
                        .requires(Blocks.OBSIDIAN)
                        .requires(Blocks.REDSTONE_LAMP)
                        .unlockedBy(getHasName(Blocks.REDSTONE_LAMP), has(Blocks.REDSTONE_LAMP))
                        .group("obsidian_redstone_lamp")
                        .save(output, "obsidian_redstone_lamp_craft");

                shapeless(RecipeCategory.MISC, ModItems.FUSED_ECHO_SHARD)
                        .requires(Items.ECHO_SHARD, 2)
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .group("fused_echo_shard")
                        .save(output, "fused_echo_shard_craft");

                shapeless(RecipeCategory.TOOLS, ModItems.RECOVERY_SHARD)
                        .requires(ModItems.FUSED_ECHO_SHARD)
                        .requires(Items.RECOVERY_COMPASS)
                        .unlockedBy(getHasName(ModItems.FUSED_ECHO_SHARD), has(ModItems.FUSED_ECHO_SHARD))
                        .unlockedBy(getHasName(Items.RECOVERY_COMPASS), has(Items.RECOVERY_COMPASS))
                        .group("recovery_shard")
                        .save(output, "recovery_shard_craft");

                shapeless(RecipeCategory.COMBAT, ModItems.ECHO_ARROW)
                        .requires(ModItems.ECHO_FRAGMENT)
                        .requires(Items.ARROW)
                        .unlockedBy(getHasName(ModItems.ECHO_ARROW), has(ModItems.ECHO_ARROW))
                        .unlockedBy(getHasName(Items.ARROW), has(Items.ARROW))
                        .group("echo_arrow")
                        .save(output, "echo_arrow_craft");

                shaped(RecipeCategory.COMBAT, ModItems.ECHO_BOW)
                        .pattern(" E")
                        .pattern("EB")
                        .define('E', ModItems.FUSED_ECHO_SHARD)
                        .define('B', Items.BOW)
                        .unlockedBy(getHasName(ModItems.FUSED_ECHO_SHARD), has(ModItems.FUSED_ECHO_SHARD))
                        .group("echo_bow")
                        .save(output, "echo_bow_craft");

                shapeless(RecipeCategory.MISC, ModItems.DREAM_SEEDS)
                        .requires(ModBlocks.DREAM_PETALS_GREEN)
                        .requires(ModBlocks.DREAM_PETALS_BLUE)
                        .requires(ModBlocks.DREAM_PETALS_PURPLE)
                        .requires(ModBlocks.DREAM_PETALS_LIGHT_BLUE)
                        .unlockedBy(getHasName(ModItems.DREAM_SEEDS), has(ModItems.DREAM_SEEDS))
                        .group("dream_seeds")
                        .save(output, "dream_seeds_craft");

                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT, RecipeCategory.MISC, Items.HEAVY_CORE, ModItems.ECHO_INGOT, output);

                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_SWORD, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.ECHO_INGOT, ModItems.ECHO_PICKAXE, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.ECHO_INGOT, ModItems.ECHO_AXE, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.ECHO_INGOT, ModItems.ECHO_SHOVEL, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.ECHO_INGOT, ModItems.ECHO_HOE, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_SPEAR, output);

                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_HELMET, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_CHESTPLATE, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_LEGGINGS, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_BOOTS, output);

                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_HORSE_ARMOR, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT, ModItems.ECHO_INGOT, ModItems.ECHO_NAUTILUS_ARMOR, output);

                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModItems.EXTENDER, RecipeCategory.TOOLS, ModBlocks.REINFORCED_OBSIDIAN, ModItems.EXTENDER_AMETHYST, output);
                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, ModItems.EXTENDER_AMETHYST, RecipeCategory.TOOLS, ModItems.ECHO_INGOT, ModItems.EXTENDER_ECHO, output);

                shaped(RecipeCategory.MISC, Items.LIGHT, 16)
                        .pattern("IGR")
                        .pattern("LEH")
                        .pattern("FUS")
                        .define('E', ModBlocks.ECHO_MAGMA)
                        .define('I', Items.GLOW_INK_SAC)
                        .define('G', Items.GLOW_LICHEN)
                        .define('R', Items.END_ROD)
                        .define('L', Blocks.REDSTONE_LAMP)
                        .define('H', Blocks.CREAKING_HEART)
                        .define('F', Blocks.PEARLESCENT_FROGLIGHT)
                        .define('U', ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE)
                        .define('S', Blocks.SHROOMLIGHT)
                        .unlockedBy(getHasName(ModBlocks.ECHO_MAGMA), has(ModBlocks.ECHO_MAGMA))
                        .unlockedBy(getHasName(Items.GLOW_INK_SAC), has(Items.GLOW_INK_SAC))
                        .unlockedBy(getHasName(Items.GLOW_LICHEN), has(Items.GLOW_LICHEN))
                        .unlockedBy(getHasName(Items.END_ROD), has(Items.END_ROD))
                        .unlockedBy(getHasName(Blocks.REDSTONE_LAMP), has(Blocks.REDSTONE_LAMP))
                        .unlockedBy(getHasName(Blocks.CREAKING_HEART), has(Blocks.CREAKING_HEART))
                        .unlockedBy(getHasName(Blocks.PEARLESCENT_FROGLIGHT), has(Blocks.PEARLESCENT_FROGLIGHT))
                        .unlockedBy(getHasName(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE), has(ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE))
                        .unlockedBy(getHasName(Blocks.SHROOMLIGHT), has(Blocks.SHROOMLIGHT))
                        .group("invisible_light")
                        .save(output, "invisible_light_craft");

                shaped(RecipeCategory.MISC, Items.CHEST, 4)
                        .pattern("WWW")
                        .pattern("W W")
                        .pattern("WWW")
                        .define('W', ItemTags.LOGS)
                        .unlockedBy("has_log", has(ItemTags.LOGS))
                        .group("chest")
                        .save(output, "logs_to_chest_craft");

                shaped(RecipeCategory.MISC, Items.STICK, 16)
                        .pattern("W")
                        .pattern("W")
                        .define('W', ItemTags.LOGS)
                        .unlockedBy("has_log", has(ItemTags.LOGS))
                        .group("stick")
                        .save(output, "logs_to_stick_craft");

                shaped(RecipeCategory.MISC, Items.CHEST, 2)
                        .pattern("BBB")
                        .pattern("B B")
                        .pattern("BBB")
                        .define('B', ItemTags.BAMBOO_BLOCKS)
                        .unlockedBy("has_log", has(ItemTags.BAMBOO_BLOCKS))
                        .group("chest")
                        .save(output, "bamboo_logs_to_chest_craft");

                shaped(RecipeCategory.MISC, Items.STICK, 8)
                        .pattern("B")
                        .pattern("B")
                        .define('B', ItemTags.BAMBOO_BLOCKS)
                        .unlockedBy("has_log", has(ItemTags.BAMBOO_BLOCKS))
                        .group("stick")
                        .save(output, "bamboo_logs_to_stick_craft");

                shaped(RecipeCategory.MISC, ModItems.COBBLER)
                        .pattern("I")
                        .pattern("S")
                        .define('I', Items.IRON_INGOT)
                        .define('S', Items.STONE)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .unlockedBy("has_stone", has(Items.STONE))
                        .group("cobbler_craft")
                        .save(output, "cobbler_craft");

                shapeless(RecipeCategory.MISC, Items.DYE.lime())
                        .requires(ModBlocks.DREAM_PETALS_GREEN)
                        .unlockedBy("has_green_dream_petals",has(ModBlocks.DREAM_PETALS_GREEN))
                        .group("green_dye")
                        .save(output, "green_dye_from_green_dream_petals");

                shapeless(RecipeCategory.MISC, Items.DYE.blue())
                        .requires(ModBlocks.DREAM_PETALS_BLUE)
                        .unlockedBy("has_blue_dream_petals",has(ModBlocks.DREAM_PETALS_BLUE))
                        .group("blue_dye")
                        .save(output, "blue_dye_from_blue_dream_petals");

                shapeless(RecipeCategory.MISC, Items.DYE.purple())
                        .requires(ModBlocks.DREAM_PETALS_PURPLE)
                        .unlockedBy("has_purple_dream_petals",has(ModBlocks.DREAM_PETALS_PURPLE))
                        .group("purple_dye")
                        .save(output, "purple_dye_from_purple_dream_petals");

                shapeless(RecipeCategory.MISC, Items.DYE.lightBlue())
                        .requires(ModBlocks.DREAM_PETALS_LIGHT_BLUE)
                        .unlockedBy("has_light_blue_dream_petals",has(ModBlocks.DREAM_PETALS_LIGHT_BLUE))
                        .group("light_blue_dye")
                        .save(output, "light_blue_dye_from_light_blue_dream_petals");

            }
        };
    }
    private void Smithing(RecipeProvider provider, ItemLike TemplateItem, ItemLike baseItem, RecipeCategory category, ItemLike ingredient, ItemLike resultItem, RecipeOutput output) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TemplateItem),
                        Ingredient.of(baseItem),
                        Ingredient.of(ingredient),
                        category,
                        resultItem.asItem()
                )
                .unlocks("has_ingredient", provider.has(ingredient))
                .save(output, String.valueOf(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,
                        BuiltInRegistries.ITEM.getKey(resultItem.asItem()).getPath() + "_smithing")));
    }

    @Override
    public String getName() {
        return "NikiBroPol Vanilla Plus Recipes";
    }
}
