package net.nikibropol.nbpvanillaplus.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_ECHO_TOOL = createTag("needs_echo_tool");
        public static final TagKey<Block> INCORRECT_FOR_ECHO_TOOL = createTag("incorrect_for_echo_tool");

        private static TagKey<Block> createTag(String name){
            return  TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> ECHO_REPAIR = createTag("echo_repair");
        private static TagKey<Item> createTag(String name){
            return  TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
        }
    }
}
