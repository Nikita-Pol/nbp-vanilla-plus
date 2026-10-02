package net.nikibropol.nbpvanillaplus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.datagen.ModJukeboxSongs;
import net.nikibropol.nbpvanillaplus.food.ModFoods;
import net.nikibropol.nbpvanillaplus.item.custom.*;

import java.util.function.Consumer;
import java.util.function.Function;

import static net.nikibropol.nbpvanillaplus.item.custom.ModSmithingTemplateItem.createEchoUpgradeIconList;
import static net.nikibropol.nbpvanillaplus.item.custom.ModSmithingTemplateItem.createEchoUpgradeMaterialList;

public class ModItems {

    public static final Item ECHO_INGOT = registerItem("echo_ingot",
            properties -> new Item(properties.fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_SCRAP = registerItem("echo_scrap",
            properties -> new Item(properties.fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_FRAGMENT = registerItem("echo_fragment",
            properties -> new Item(properties.rarity(Rarity.UNCOMMON)));
    public static final Item FUSED_ECHO_SHARD = registerItem("fused_echo_shard",
            properties -> new Item(properties.rarity(Rarity.UNCOMMON)));
    public static final Item RECOVERY_SHARD = registerItem("recovery_shard",
            properties -> new RecoveryShardItem(properties.stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final Item ECHO_UPGRADE_SMITHING_TEMPLATE = registerItem("echo_upgrade_smithing_template",
            properties -> new ModSmithingTemplateItem(
                    Component.translatable("tooltip.nbp-vanilla-plus.echo_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE),
                    Component.translatable("tooltip.nbp-vanilla-plus.echo_upgrade_smithing_template.ingredients").withStyle(ChatFormatting.BLUE),
                    Component.translatable("tooltip.nbp-vanilla-plus.echo_upgrade_smithing_template.base_slot_description"),
                    Component.translatable("tooltip.nbp-vanilla-plus.echo_upgrade_smithing_template.additions_slot_description"),
                    createEchoUpgradeIconList(),
                    createEchoUpgradeMaterialList(),
                    properties.fireResistant()
                              .rarity(Rarity.UNCOMMON)
            ));

    public static final Item AMETHYST_UPGRADE_SMITHING_TEMPLATE = registerItem("amethyst_upgrade_smithing_template", Item::new);

    public static final Item ECHOBERRY = registerItem("echoberry",
            properties -> new BlockItem(ModBlocks.ECHO_VINES,
                    properties.food(ModFoods.ECHOBERRY, ModFoods.ECHOBERRY_CONSUMABLE).rarity(Rarity.UNCOMMON)));

    public static final Item AMETHYST_SWEET_BERRIES = registerItem("amethyst_sweet_berries",
            properties -> new BlockItem(ModBlocks.AMETHYST_SWEET_BERRIES_BUSH,
                    properties.useItemDescriptionPrefix()
                            .food(ModFoods.AMETHYST_SWEET_BERRIES, ModFoods.AMETHYST_SWEET_BERRIES_CONSUMABLE)));

    public static final Item STRANGE_BEETROOT = registerItem("strange_beetroot",
            properties -> new Item(properties.food(ModFoods.STRANGE_BEETROOT, ModFoods.STRANGE_BEETROOT_CONSUMABLE)));
    public static final Item STRANGE_BEETROOT_SEEDS = registerItem("strange_beetroot_seeds",
            properties -> new BlockItem(ModBlocks.STRANGE_BEETROOT_CROP, properties.useItemDescriptionPrefix()));
    public static final Item FLOWTAREM = registerItem("flowtarem",
            properties -> new Item(properties.food(ModFoods.FLOWTAREM, ModFoods.FLOWTAREM_CONSUMABLE)));
    public static final Item FLOWTAREM_SEEDS = registerItem("flowtarem_seeds",
            properties -> new BlockItem(ModBlocks.FLOWTAREM_CROP, properties.useItemDescriptionPrefix()));
    public static final Item RESIN_FLOWTAREM = registerItem("resin_flowtarem",
            properties -> new Item(properties.food(ModFoods.RESIN_FLOWTAREM, ModFoods.RESIN_FLOWTAREM_CONSUMABLE)));

    public static final Item BUNCH_OF_SUGAR_CANE = registerItem("bunch_of_sugar_cane",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_CHORUS_FRUIT = registerItem("bunch_of_chorus_fruit",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_CARROT = registerItem("bunch_of_carrot",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_APPLE = registerItem("bunch_of_apple",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_POTATO = registerItem("bunch_of_potato",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_BEETROOT = registerItem("bunch_of_beetroot",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_STRANGE_BEETROOT = registerItem("bunch_of_strange_beetroot",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_SWEET_BERRIES = registerItem("bunch_of_sweet_berries",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_AMETHYST_SWEET_BERRIES = registerItem("bunch_of_amethyst_sweet_berries",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_GLOW_BERRIES = registerItem("bunch_of_glow_berries",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_ECHO_BERRIES = registerItem("bunch_of_echo_berries",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_FLOWTAREM = registerItem("bunch_of_flowtarem",
            properties -> new Item(properties.useItemDescriptionPrefix()));
    public static final Item BUNCH_OF_RESIN_FLOWTAREM = registerItem("bunch_of_resin_flowtarem",
            properties -> new Item(properties.useItemDescriptionPrefix()));

    public static final Item DREAM_SEEDS = registerItem("dream_seeds",
            properties -> new PlaceOnWaterBlockItem(ModBlocks.DREAM_FLOWER, properties.useItemDescriptionPrefix()));

    public static final Item COBBLER = registerItem("cobbler", properties -> new CobblerItem(properties.durability(128)));
    public static final Item ECHO_SWORD = registerItem("echo_sword",
            properties -> new Item(properties.sword(ModToolMaterials.ECHO_INGOT, 3f, -2.4f)
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_PICKAXE = registerItem("echo_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.ECHO_INGOT, 0f, -2.8f)
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_AXE = registerItem("echo_axe",
            properties -> new AxeItem(ModToolMaterials.ECHO_INGOT, 6f, -3.0f, properties
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_SHOVEL = registerItem("echo_shovel",
            properties -> new ShovelItem(ModToolMaterials.ECHO_INGOT, -1f, -3.0f, properties
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_HOE = registerItem("echo_hoe",
            properties -> new HoeItem(ModToolMaterials.ECHO_INGOT, -7f, 0f, properties
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_SPEAR = registerItem("echo_spear",
            properties -> new Item(properties.spear(ModToolMaterials.ECHO_INGOT, 1.35f, 2.5f, 0.25f,
                    2f, 8.0f, 4f, 5.1f, 7f, 4.6f)
                    .fireResistant().rarity(Rarity.UNCOMMON)));


    public static final Item ECHO_HELMET = registerItem("echo_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.HELMET)
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_CHESTPLATE = registerItem("echo_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.CHESTPLATE)
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_LEGGINGS = registerItem("echo_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.LEGGINGS)
                    .fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_BOOTS = registerItem("echo_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.BOOTS)
                    .fireResistant().rarity(Rarity.UNCOMMON)));

    public static final Item ECHO_HORSE_ARMOR = registerItem("echo_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_NAUTILUS_ARMOR = registerItem("echo_nautilus_armor",
            properties -> new Item(properties.nautilusArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL).fireResistant().rarity(Rarity.UNCOMMON)));

    public static final Item ECHO_BOW = registerItem("echo_bow",
            properties -> new EchoBowItem(properties.durability(537).rarity(Rarity.UNCOMMON)));
    public static final Item ECHO_ARROW = registerItem("echo_arrow",
            properties -> new EchoArrowItem(properties.rarity(Rarity.UNCOMMON)));

    public static final Item EXTENDER = registerItem("extender",
            properties -> new ExtenderItem(properties.stacksTo(1).rarity(Rarity.RARE), 1));
    public static final Item EXTENDER_AMETHYST = registerItem("extender_amethyst",
            properties -> new ExtenderItem(properties.stacksTo(1).rarity(Rarity.RARE), 2));
    public static final Item EXTENDER_ECHO = registerItem("extender_echo",
            properties -> new ExtenderItem(properties.stacksTo(1).rarity(Rarity.RARE), 4));

    public static final Item ONLY_ONCE_MORE_MUSIC_DISC = registerItem("only_once_more_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.ONLY_ONCE_MORE_KEY)
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final Item DUSK_TO_DAWN_MUSIC_DISC = registerItem("dusk_to_dawn_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.DUSK_TO_DAWN_KEY)
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final Item BROKEN_ETERNITY_MUSIC_DISC = registerItem("broken_eternity_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.BROKEN_ETERNITY_KEY)
                    .stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final Item DREAM_GREEN_WOOD_SIGN = registerItem("dream_green_wood_sign",
            properties -> new SignItem(ModBlocks.DREAM_GREEN_WOOD_SIGN, ModBlocks.DREAM_GREEN_WOOD_WALL_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_GREEN_WOOD_HANGING_SIGN = registerItem("dream_green_wood_hanging_sign",
            properties -> new HangingSignItem(ModBlocks.DREAM_GREEN_WOOD_HANGING_SIGN, ModBlocks.DREAM_GREEN_WOOD_WALL_HANGING_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_PURPLE_WOOD_SIGN = registerItem("dream_purple_wood_sign",
            properties -> new SignItem(ModBlocks.DREAM_PURPLE_WOOD_SIGN, ModBlocks.DREAM_PURPLE_WOOD_WALL_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_PURPLE_WOOD_HANGING_SIGN = registerItem("dream_purple_wood_hanging_sign",
            properties -> new HangingSignItem(ModBlocks.DREAM_PURPLE_WOOD_HANGING_SIGN, ModBlocks.DREAM_PURPLE_WOOD_WALL_HANGING_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_BLUE_WOOD_SIGN = registerItem("dream_blue_wood_sign",
            properties -> new SignItem(ModBlocks.DREAM_BLUE_WOOD_SIGN, ModBlocks.DREAM_BLUE_WOOD_WALL_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_BLUE_WOOD_HANGING_SIGN = registerItem("dream_blue_wood_hanging_sign",
            properties -> new HangingSignItem(ModBlocks.DREAM_BLUE_WOOD_HANGING_SIGN, ModBlocks.DREAM_BLUE_WOOD_WALL_HANGING_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_LIGHT_BLUE_WOOD_SIGN = registerItem("dream_light_blue_wood_sign",
            properties -> new SignItem(ModBlocks.DREAM_LIGHT_BLUE_WOOD_SIGN, ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_SIGN, properties.stacksTo(16)));
    public static final Item DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN = registerItem("dream_light_blue_wood_hanging_sign",
            properties -> new HangingSignItem(ModBlocks.DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, ModBlocks.DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN, properties.stacksTo(16)));



    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))));
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))));
    }

    public static void registerModItems(){
        NBPVanillaPlus.LOGGER.info("Registering Mod Items for " + NBPVanillaPlus.MOD_ID);

        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(ECHO_INGOT);
            output.accept(ECHO_UPGRADE_SMITHING_TEMPLATE);
        });*/
    }
}
