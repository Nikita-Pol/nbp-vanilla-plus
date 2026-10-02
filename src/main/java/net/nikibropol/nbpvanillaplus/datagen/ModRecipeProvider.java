package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.recipe.ReinforcedShulkerBoxColorRecipe;
import net.nikibropol.nbpvanillaplus.tags.ModTags;
import org.jetbrains.annotations.Nullable;

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

                shaped(RecipeCategory.FOOD, ModItems.ECHOBERRY)
                        .pattern("SSS")
                        .pattern("SBS")
                        .pattern("SSS")
                        .define('S', Items.SCULK_VEIN)
                        .define('B', Items.GLOW_BERRIES)
                        .unlockedBy(getHasName(Items.SCULK_VEIN), has(Items.SCULK_VEIN))
                        .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                        .group("echoberry")
                        .save(output, "echoberry_sculk_craft");

                shaped(RecipeCategory.FOOD, ModItems.ECHOBERRY, 8)
                        .pattern("BBB")
                        .pattern("BEB")
                        .pattern("BBB")
                        .define('E', Items.ECHO_SHARD)
                        .define('B', Items.GLOW_BERRIES)
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                        .group("echoberry")
                        .save(output, "echoberry_shard_craft");

                shaped(RecipeCategory.FOOD, ModItems.AMETHYST_SWEET_BERRIES, 8)
                        .pattern("BBB")
                        .pattern("BAB")
                        .pattern("BBB")
                        .define('A', Items.AMETHYST_SHARD)
                        .define('B', Items.SWEET_BERRIES)
                        .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                        .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                        .group("amethyst_sweet_berries")
                        .save(output, "amethyst_sweet_berries_shard_craft");

                shaped(RecipeCategory.FOOD, ModItems.RESIN_FLOWTAREM, 8)
                        .pattern("FFF")
                        .pattern("FRF")
                        .pattern("FFF")
                        .define('R', Items.RESIN_CLUMP)
                        .define('F', ModItems.FLOWTAREM)
                        .unlockedBy(getHasName(Items.RESIN_CLUMP), has(Items.RESIN_CLUMP))
                        .unlockedBy(getHasName(ModItems.FLOWTAREM), has(ModItems.FLOWTAREM))
                        .group("resin_flowtarem")
                        .save(output, "resin_flowtarem_craft");

                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.SUGAR_CANE, RecipeCategory.FOOD, ModItems.BUNCH_OF_SUGAR_CANE);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.CHORUS_FRUIT, RecipeCategory.FOOD, ModItems.BUNCH_OF_CHORUS_FRUIT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.CARROT, RecipeCategory.FOOD, ModItems.BUNCH_OF_CARROT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.APPLE, RecipeCategory.FOOD, ModItems.BUNCH_OF_APPLE);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.POTATO, RecipeCategory.FOOD, ModItems.BUNCH_OF_POTATO);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.BEETROOT, RecipeCategory.FOOD, ModItems.BUNCH_OF_BEETROOT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.STRANGE_BEETROOT, RecipeCategory.FOOD, ModItems.BUNCH_OF_STRANGE_BEETROOT);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.FOOD, ModItems.BUNCH_OF_SWEET_BERRIES);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.AMETHYST_SWEET_BERRIES, RecipeCategory.FOOD, ModItems.BUNCH_OF_AMETHYST_SWEET_BERRIES);
                nineBlockStorageRecipes(RecipeCategory.FOOD, Items.GLOW_BERRIES, RecipeCategory.FOOD, ModItems.BUNCH_OF_GLOW_BERRIES);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.ECHOBERRY, RecipeCategory.FOOD, ModItems.BUNCH_OF_ECHO_BERRIES);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.FLOWTAREM, RecipeCategory.FOOD, ModItems.BUNCH_OF_FLOWTAREM);
                nineBlockStorageRecipes(RecipeCategory.FOOD, ModItems.RESIN_FLOWTAREM, RecipeCategory.FOOD, ModItems.BUNCH_OF_RESIN_FLOWTAREM);

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

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DREAMED_OBSIDIAN)
                        .pattern("GPB")
                        .pattern("LRL")
                        .pattern("BPG")
                        .define('R', ModBlocks.REINFORCED_OBSIDIAN)
                        .define('G', ModBlocks.DREAM_PETALS_GREEN)
                        .define('P', ModBlocks.DREAM_PETALS_PURPLE)
                        .define('B', ModBlocks.DREAM_PETALS_BLUE)
                        .define('L', ModBlocks.DREAM_PETALS_LIGHT_BLUE)
                        .unlockedBy(getHasName(ModBlocks.REINFORCED_OBSIDIAN), has(ModBlocks.REINFORCED_OBSIDIAN))
                        .group("dreamed_obsidian")
                        .save(output, "dreamed_obsidian_craft");

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

                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.black(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"black", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.blue(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"blue", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.brown(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"brown", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.cyan(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"cyan", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.gray(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"gray", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.green(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"green", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.lightBlue(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"light_blue", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.lightGray(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"light_gray", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.lime(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"lime", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.magenta(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"magenta", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.orange(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"orange", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.pink(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"pink", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.purple(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"purple", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.red(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"red", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.white(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"white", output);
                Smithing(this, ModItems.AMETHYST_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DREAMED_OBSIDIAN, RecipeCategory.BUILDING_BLOCKS, Blocks.BED.yellow(), ModBlocks.DREAMED_POWERED_OBSIDIAN,"yellow", output);

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

                for (DyeColor color : DyeColor.values()) {
                    Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, vanillaShulkerBox(color), RecipeCategory.MISC,
                            ModBlocks.REINFORCED_OBSIDIAN, ModBlocks.REINFORCED_SHULKER_BOXES.get(color).asItem(), output);}

                Smithing(this, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, Items.SHULKER_BOX, RecipeCategory.MISC,
                        ModBlocks.REINFORCED_OBSIDIAN, ModBlocks.REINFORCED_SHULKER_BOX, output);

                SpecialRecipeBuilder.special(ReinforcedShulkerBoxColorRecipe::new)
                        .save(output, String.valueOf(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker_box_color")));

                woodFromLogs(ModBlocks.DREAM_GREEN_WOOD, ModBlocks.DREAM_GREEN_LOG);
                woodFromLogs(ModBlocks.STRIPPED_DREAM_GREEN_WOOD, ModBlocks.STRIPPED_DREAM_GREEN_LOG);
                planksFromLogs(ModBlocks.DREAM_GREEN_PLANKS, ModTags.Items.DREAM_GREEN_LOGS, 4);

                stairBuilder(ModBlocks.DREAM_GREEN_WOOD_STAIRS, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_stairs").save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DREAM_GREEN_WOOD_SLAB, ModBlocks.DREAM_GREEN_PLANKS);

                buttonBuilder(ModBlocks.DREAM_GREEN_WOOD_BUTTON, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_button").save(output);
                pressurePlate(ModBlocks.DREAM_GREEN_WOOD_PRESSURE_PLATE, ModBlocks.DREAM_GREEN_PLANKS);

                fenceBuilder(ModBlocks.DREAM_GREEN_WOOD_FENCE, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_fence").save(output);

                fenceBuilder(ModBlocks.DREAM_GREEN_WOOD_FENCE_GATE, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_fence_gate").save(output);

                doorBuilder(ModBlocks.DREAM_GREEN_WOOD_DOOR, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_door").save(output);

                trapdoorBuilder(ModBlocks.DREAM_GREEN_WOOD_TRAPDOOR, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_trapdoor").save(output);

                signBuilder(ModBlocks.DREAM_GREEN_WOOD_SIGN, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_sign").save(output);

                hangingSignBuilder(ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN, Ingredient.of(ModBlocks.DREAM_GREEN_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_GREEN_PLANKS), has(ModBlocks.DREAM_GREEN_PLANKS))
                        .group("dream_green_wood_hanging_sign").save(output);

                woodFromLogs(ModBlocks.DREAM_PURPLE_WOOD, ModBlocks.DREAM_PURPLE_LOG);
                woodFromLogs(ModBlocks.STRIPPED_DREAM_PURPLE_WOOD, ModBlocks.STRIPPED_DREAM_PURPLE_LOG);
                planksFromLogs(ModBlocks.DREAM_PURPLE_PLANKS, ModTags.Items.DREAM_PURPLE_LOGS, 4);

                stairBuilder(ModBlocks.DREAM_PURPLE_WOOD_STAIRS, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_stairs").save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DREAM_PURPLE_WOOD_SLAB, ModBlocks.DREAM_PURPLE_PLANKS);

                buttonBuilder(ModBlocks.DREAM_PURPLE_WOOD_BUTTON, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_button").save(output);
                pressurePlate(ModBlocks.DREAM_PURPLE_WOOD_PRESSURE_PLATE, ModBlocks.DREAM_PURPLE_PLANKS);

                fenceBuilder(ModBlocks.DREAM_PURPLE_WOOD_FENCE, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_fence").save(output);

                fenceBuilder(ModBlocks.DREAM_PURPLE_WOOD_FENCE_GATE, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_fence_gate").save(output);

                doorBuilder(ModBlocks.DREAM_PURPLE_WOOD_DOOR, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_door").save(output);

                trapdoorBuilder(ModBlocks.DREAM_PURPLE_WOOD_TRAPDOOR, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_trapdoor").save(output);

                signBuilder(ModBlocks.DREAM_PURPLE_WOOD_SIGN, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_sign").save(output);

                hangingSignBuilder(ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN, Ingredient.of(ModBlocks.DREAM_PURPLE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_PURPLE_PLANKS), has(ModBlocks.DREAM_PURPLE_PLANKS))
                        .group("dream_purple_wood_hanging_sign").save(output);

                woodFromLogs(ModBlocks.DREAM_BLUE_WOOD, ModBlocks.DREAM_BLUE_LOG);
                woodFromLogs(ModBlocks.STRIPPED_DREAM_BLUE_WOOD, ModBlocks.STRIPPED_DREAM_BLUE_LOG);
                planksFromLogs(ModBlocks.DREAM_BLUE_PLANKS, ModTags.Items.DREAM_BLUE_LOGS, 4);

                stairBuilder(ModBlocks.DREAM_BLUE_WOOD_STAIRS, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_stairs").save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DREAM_BLUE_WOOD_SLAB, ModBlocks.DREAM_BLUE_PLANKS);

                buttonBuilder(ModBlocks.DREAM_BLUE_WOOD_BUTTON, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_button").save(output);
                pressurePlate(ModBlocks.DREAM_BLUE_WOOD_PRESSURE_PLATE, ModBlocks.DREAM_BLUE_PLANKS);

                fenceBuilder(ModBlocks.DREAM_BLUE_WOOD_FENCE, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_fence").save(output);

                fenceBuilder(ModBlocks.DREAM_BLUE_WOOD_FENCE_GATE, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_fence_gate").save(output);

                doorBuilder(ModBlocks.DREAM_BLUE_WOOD_DOOR, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_door").save(output);

                trapdoorBuilder(ModBlocks.DREAM_BLUE_WOOD_TRAPDOOR, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_trapdoor").save(output);

                signBuilder(ModBlocks.DREAM_BLUE_WOOD_SIGN, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_sign").save(output);

                hangingSignBuilder(ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN, Ingredient.of(ModBlocks.DREAM_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_BLUE_PLANKS), has(ModBlocks.DREAM_BLUE_PLANKS))
                        .group("dream_blue_wood_hanging_sign").save(output);

                woodFromLogs(ModBlocks.DREAM_LIGHT_BLUE_WOOD, ModBlocks.DREAM_LIGHT_BLUE_LOG);
                woodFromLogs(ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_WOOD, ModBlocks.STRIPPED_DREAM_LIGHT_BLUE_LOG);
                planksFromLogs(ModBlocks.DREAM_LIGHT_BLUE_PLANKS, ModTags.Items.DREAM_LIGHT_BLUE_LOGS, 4);

                stairBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_STAIRS, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_stairs").save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DREAM_LIGHT_BLUE_WOOD_SLAB, ModBlocks.DREAM_LIGHT_BLUE_PLANKS);

                buttonBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_BUTTON, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_button").save(output);
                pressurePlate(ModBlocks.DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE, ModBlocks.DREAM_LIGHT_BLUE_PLANKS);

                fenceBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_fence").save(output);

                fenceBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_FENCE_GATE, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_fence_gate").save(output);

                doorBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_DOOR, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_door").save(output);

                trapdoorBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_TRAPDOOR, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_trapdoor").save(output);

                signBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_sign").save(output);

                hangingSignBuilder(ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, Ingredient.of(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DREAM_LIGHT_BLUE_PLANKS), has(ModBlocks.DREAM_LIGHT_BLUE_PLANKS))
                        .group("dream_light_blue_wood_hanging_sign").save(output);
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
    private void Smithing(RecipeProvider provider, ItemLike TemplateItem, ItemLike baseItem, RecipeCategory category, ItemLike ingredient, ItemLike resultItem, String from, RecipeOutput output) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TemplateItem),
                        Ingredient.of(baseItem),
                        Ingredient.of(ingredient),
                        category,
                        resultItem.asItem()
                )
                .unlocks("has_ingredient", provider.has(ingredient))
                .save(output, String.valueOf(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID,
                        BuiltInRegistries.ITEM.getKey(resultItem.asItem()).getPath() + "_" + from + "_smithing")));
    }

    private static Block vanillaShulkerBox(@Nullable DyeColor color) {
        if (color == null) return Blocks.SHULKER_BOX;
        return switch (color) {
            case WHITE -> Blocks.DYED_SHULKER_BOX.white();
            case ORANGE -> Blocks.DYED_SHULKER_BOX.orange();
            case MAGENTA -> Blocks.DYED_SHULKER_BOX.magenta();
            case LIGHT_BLUE -> Blocks.DYED_SHULKER_BOX.lightBlue();
            case YELLOW -> Blocks.DYED_SHULKER_BOX.yellow();
            case LIME -> Blocks.DYED_SHULKER_BOX.lime();
            case PINK -> Blocks.DYED_SHULKER_BOX.pink();
            case GRAY -> Blocks.DYED_SHULKER_BOX.gray();
            case LIGHT_GRAY -> Blocks.DYED_SHULKER_BOX.lightGray();
            case CYAN -> Blocks.DYED_SHULKER_BOX.cyan();
            case PURPLE -> Blocks.DYED_SHULKER_BOX.purple();
            case BLUE -> Blocks.DYED_SHULKER_BOX.blue();
            case BROWN -> Blocks.DYED_SHULKER_BOX.brown();
            case GREEN -> Blocks.DYED_SHULKER_BOX.green();
            case RED -> Blocks.DYED_SHULKER_BOX.red();
            case BLACK -> Blocks.DYED_SHULKER_BOX.black();
        };
    }

    @Override
    public String getName() {
        return "NikiBroPol Vanilla Plus Recipes";
    }
}
