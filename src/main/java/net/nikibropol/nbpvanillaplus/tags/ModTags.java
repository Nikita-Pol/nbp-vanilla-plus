package net.nikibropol.nbpvanillaplus.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import org.lwjgl.vulkan.VkDependencyInfoKHR;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_ECHO_TOOL = createTag("needs_echo_tool");
        public static final TagKey<Block> INCORRECT_FOR_ECHO_TOOL = createTag("incorrect_for_echo_tool");

        public static final TagKey<Block> DREAM_LOGS = createTag("dream_logs");
        public static final TagKey<Block> DREAM_GREEN_LOGS = createTag("dream_green_logs");
        public static final TagKey<Block> DREAM_PURPLE_LOGS = createTag("dream_purple_logs");
        public static final TagKey<Block> DREAM_BLUE_LOGS = createTag("dream_blue_logs");
        public static final TagKey<Block> DREAM_LIGHT_BLUE_LOGS = createTag("dream_light_blue_logs");

        public static final TagKey<Block> AMETHYST_BUSH_PLACEABLE = TagKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "amethyst_bush_placeable")
        );
        public static final TagKey<Block> ECHO_VINES_PLACEABLE = TagKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "echo_vines_placeable")
        );
        public static final TagKey<Block> DREAM_PETALS = TagKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dream_petals")
        );
        private static TagKey<Block> createTag(String name){
            return  TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> DREAM_LOGS = createTag("dream_logs");
        public static final TagKey<Item> DREAM_GREEN_LOGS = createTag("dream_green_logs");
        public static final TagKey<Item> DREAM_PURPLE_LOGS = createTag("dream_purple_logs");
        public static final TagKey<Item> DREAM_BLUE_LOGS = createTag("dream_blue_logs");
        public static final TagKey<Item> DREAM_LIGHT_BLUE_LOGS = createTag("dream_light_blue_logs");

        public static final TagKey<Item> ECHO_REPAIR = createTag("echo_repair");

        private static TagKey<Item> createTag(String name){
            return  TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
    }
    public static class Biomes{

        public static final TagKey<Biome> HAS_DREAM_GREEN_HOUSE = createTag("has_structure/dream_green_house");
        public static final TagKey<Biome> HAS_DREAM_PURPLE_HOUSE = createTag("has_structure/dream_purple_house");
        public static final TagKey<Biome> HAS_DREAM_BLUE_HOUSE = createTag("has_structure/dream_blue_house");
        public static final TagKey<Biome> HAS_DREAM_LIGHT_BLUE_HOUSE = createTag("has_structure/dream_light_blue_house");

        private static TagKey<Biome> createTag(String name){
            return  TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
    }

        public static class Trades{
        public static final TagKey<VillagerTrade> DREAMER_LEVEL_1 = createTag("dreamer/level_1");
        public static final TagKey<VillagerTrade> DREAMER_LEVEL_2 = createTag("dreamer/level_2");
        public static final TagKey<VillagerTrade> DREAMER_LEVEL_3 = createTag("dreamer/level_3");
        public static final TagKey<VillagerTrade> DREAMER_LEVEL_4 = createTag("dreamer/level_4");
        public static final TagKey<VillagerTrade> DREAMER_LEVEL_5 = createTag("dreamer/level_5");

        private static TagKey<VillagerTrade> createTag(String name){
            return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
        }
}
