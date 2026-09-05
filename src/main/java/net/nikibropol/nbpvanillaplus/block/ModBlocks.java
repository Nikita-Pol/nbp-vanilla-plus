package net.nikibropol.nbpvanillaplus.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.custom.EchoMagmaBlock;
import net.nikibropol.nbpvanillaplus.block.custom.EchoVinesBlock;
import net.nikibropol.nbpvanillaplus.block.custom.EchoVinesPlantBlock;
import net.nikibropol.nbpvanillaplus.block.custom.StrangeBeetrootCropBlock;

import java.util.function.Function;

public class ModBlocks {

    public static final Block ECHO_BLOCK = registerBlock("echo_block",
            properties -> new Block(properties.strength(55f, 1400f)
                    .lightLevel(statex -> 3)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
    public static final Block ECHO_MAGMA = registerBlock("echo_magma",
            properties -> new EchoMagmaBlock(properties.strength(0.5f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(statex -> 5)
                    .strength(0.5F)
                    .isValidSpawn((statex, blockGetter, blockPos, entityType) -> entityType.fireImmune())
                    .emissiveRendering(var0 -> true)));
    public static final Block REINFORCED_OBSIDIAN = registerBlock("reinforced_obsidian",
            properties -> new Block(properties.strength(200f, 5000f)
                    .requiresCorrectToolForDrops()));

    public static final Block ECHO_VINES = registerBlockWithoutBlockItem("echo_vines",
            properties -> new EchoVinesBlock(properties.randomTicks().noCollision().sound(SoundType.GLOW_LICHEN)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> state.getValue(BlockStateProperties.BERRIES) ? 10 : 0)));

    public static final Block ECHO_VINES_PLANT = registerBlockWithoutBlockItem("echo_vines_plant",
            properties -> new EchoVinesPlantBlock(properties.randomTicks().noCollision().sound(SoundType.GLOW_LICHEN)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> state.getValue(BlockStateProperties.BERRIES) ? 10 : 0)));

    public  static final Block STRANGE_BEETROOT_CROP = registerBlockWithoutBlockItem("strange_beetroot_crop",
    properties -> new StrangeBeetrootCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY)));



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        registerBlockItem(name, toRegister);

        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))));
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }
    public static void registerModBlocks(){
        NBPVanillaPlus.LOGGER.info("Registering Mod Blocks for " + NBPVanillaPlus.MOD_ID);
    }
}
