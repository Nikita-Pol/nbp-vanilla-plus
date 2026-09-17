package net.nikibropol.nbpvanillaplus.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.custom.*;

import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.litBlockEmission;

public class ModBlocks {

    public static final Block ECHO_BLOCK = registerBlock("echo_block",
            properties -> new Block(properties.strength(75f, 2000f)
                    .lightLevel(statex -> 3)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)), Rarity.UNCOMMON);
    public static final Block ECHO_MAGMA = registerBlock("echo_magma",
            properties -> new EchoMagmaBlock(properties.strength(0.5f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(statex -> 5)
                    .strength(0.5F)
                    .isValidSpawn((statex, blockGetter, blockPos, entityType) -> entityType.fireImmune())
                    .emissiveRendering(var0 -> true)),
                    Rarity.UNCOMMON);
    public static final Block REINFORCED_OBSIDIAN = registerBlock("reinforced_obsidian",
            properties -> new Block(properties.strength(200f, 5000f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)));

    public static final Block OBSIDIAN_REDSTONE_LAMP = registerBlock("obsidian_redstone_lamp",
            properties -> new ObsidianRedstoneLampBlock(properties.strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
                    .lightLevel(state -> state.getValue(ObsidianRedstoneLampBlock.LIT) ? 15 : 0)));

    public static final Block ECHO_VINES = registerBlockWithoutBlockItem("echo_vines",
            properties -> new EchoVinesBlock(properties.randomTicks().noCollision().sound(SoundType.GLOW_LICHEN)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> state.getValue(BlockStateProperties.BERRIES) ? 10 : 0)));

    public static final Block ECHO_VINES_PLANT = registerBlockWithoutBlockItem("echo_vines_plant",
            properties -> new EchoVinesPlantBlock(properties.randomTicks().noCollision().sound(SoundType.GLOW_LICHEN)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> state.getValue(BlockStateProperties.BERRIES) ? 10 : 0)));

    public static final Block STRANGE_BEETROOT_CROP = registerBlockWithoutBlockItem("strange_beetroot_crop",
    properties -> new StrangeBeetrootCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY)));
    public static final Block AMETHYST_SWEET_BERRIES_BUSH = registerBlockWithoutBlockItem("amethyst_sweet_berries_bush",
    properties -> new AmethystSweetBerriesBushBlock(properties.randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH)
            .sound(SoundType.AMETHYST).pushReaction(PushReaction.DESTROY)));

    public static final Block DREAM_FLOWER = registerBlockWithoutBlockItem("dream_flower",
            properties -> new DreamFlowerBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)));

    public static final Block DREAM_PETALS_GREEN = registerBlock("dream_petals_green",
            properties -> new DreamPetalsBlock(properties.mapColor(MapColor.PLANT).instabreak()
                    .noCollision().sound(SoundType.PINK_PETALS)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_PETALS_BLUE = registerBlock("dream_petals_blue",
            properties -> new DreamPetalsBlock(properties.mapColor(MapColor.PLANT).instabreak()
                    .noCollision().sound(SoundType.PINK_PETALS)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_PETALS_PURPLE = registerBlock("dream_petals_purple",
            properties -> new DreamPetalsBlock(properties.mapColor(MapColor.PLANT).instabreak()
                    .noCollision().sound(SoundType.PINK_PETALS)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_PETALS_LIGHT_BLUE = registerBlock("dream_petals_light_blue",
            properties -> new DreamPetalsBlock(properties.mapColor(MapColor.PLANT).instabreak()
                    .noCollision().sound(SoundType.PINK_PETALS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final Block DREAM_ECHO_STATUE = registerBlock("dream_echo_statue",
            properties -> new DreamEchoStatueBlock(properties.strength(5f, 1200f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST).lightLevel(statex -> 7)
                    .pushReaction(PushReaction.IGNORE)
                    .isValidSpawn((statex, blockGetter, blockPos, entityType) -> entityType.fireImmune())),
                    Rarity.UNCOMMON);



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, Rarity rarity, Component... tooltips){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        registerBlockItem(name, toRegister, rarity, tooltips);

        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Rarity rarity, Component... tooltips){
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().rarity(rarity)
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))){
                    @Override
                    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay
                            display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        for (var component : tooltips){
                            builder.accept(component);
                        }
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                    }});
    }


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        registerBlockItem(name, toRegister);

        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)))));
    }


    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }
    public static void registerModBlocks(){
        NBPVanillaPlus.LOGGER.info("Registering Mod Blocks for " + NBPVanillaPlus.MOD_ID);
    }
}
