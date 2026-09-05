package net.nikibropol.nbpvanillaplus.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.item.custom.CobblerItem;

import java.util.function.Function;

public class ModItems {

    public static final Item ECHO_INGOT = registerItem("echo_ingot", properties -> new Item(properties.fireResistant()));
    public static final Item ECHO_SCRAP = registerItem("echo_scrap", properties -> new Item(properties.fireResistant()));
    public static final Item ECHO_UPGRADE_SMITHING_TEMPLATE = registerItem("echo_upgrade_smithing_template", properties -> new Item(properties.fireResistant()));
    public static final Item AMETHYST_UPGRADE_SMITHING_TEMPLATE = registerItem("amethyst_upgrade_smithing_template", Item::new);

    public static final Item COBBLER = registerItem("cobbler", properties -> new CobblerItem(properties.durability(128)));
    public static final Item ECHO_SWORD = registerItem("echo_sword",
            properties -> new Item(properties.sword(ModToolMaterials.ECHO_INGOT, 3f, -2.4f).fireResistant()));
    public static final Item ECHO_PICKAXE = registerItem("echo_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.ECHO_INGOT, 0f, -2.8f).fireResistant()));
    public static final Item ECHO_AXE = registerItem("echo_axe",
            properties -> new AxeItem(ModToolMaterials.ECHO_INGOT, 6f, -3.0f, properties.fireResistant()));
    public static final Item ECHO_SHOVEL = registerItem("echo_shovel",
            properties -> new ShovelItem(ModToolMaterials.ECHO_INGOT, -1f, -3.0f, properties.fireResistant()));
    public static final Item ECHO_HOE = registerItem("echo_hoe",
            properties -> new HoeItem(ModToolMaterials.ECHO_INGOT, -7f, 0f, properties.fireResistant()));
    public static final Item ECHO_SPEAR = registerItem("echo_spear",
            properties -> new Item(properties.spear(ModToolMaterials.ECHO_INGOT, 1.35f, 2.5f, 0.25f,
                    2f, 8.0f, 4f, 5.1f, 7f, 4.6f).fireResistant()));


    public static final Item ECHO_HELMET = registerItem("echo_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.HELMET).fireResistant()));
    public static final Item ECHO_CHESTPLATE = registerItem("echo_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.CHESTPLATE).fireResistant()));
    public static final Item ECHO_LEGGINGS = registerItem("echo_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.LEGGINGS).fireResistant()));
    public static final Item ECHO_BOOTS = registerItem("echo_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL, ArmorType.BOOTS).fireResistant()));

    public static final Item ECHO_HORSE_ARMOR = registerItem("echo_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL).fireResistant()));
    public static final Item ECHO_NAUTILUS_ARMOR = registerItem("echo_nautilus_armor",
            properties -> new Item(properties.nautilusArmor(ModArmorMaterials.ECHO_ARMOR_MATERIAL).fireResistant()));

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))));
    }

    public static void registerModItems(){
        NBPVanillaPlus.LOGGER.info("Registering Mod Items for " + NBPVanillaPlus.MOD_ID);

        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(ECHO_INGOT);
            output.accept(ECHO_UPGRADE_SMITHING_TEMPLATE);
        });*/
    }
}
