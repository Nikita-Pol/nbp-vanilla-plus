package net.nikibropol.nbpvanillaplus.block;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypeIds;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.custom.*;
import net.nikibropol.nbpvanillaplus.block.state.properties.ModWoodType;
import net.nikibropol.nbpvanillaplus.mixin.BlockEntityTypeAccessor;
import net.nikibropol.nbpvanillaplus.worldgen.tree.ModTreeGrowers;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;


public class ModBlocks {
    public static final Map<DyeColor, Block> REINFORCED_SHULKER_BOXES = new EnumMap<>(DyeColor.class);
    public static final Block REINFORCED_SHULKER_BOX;

    static {
        for (DyeColor color : DyeColor.values()) {
            REINFORCED_SHULKER_BOXES.put(color, registerBlock("reinforced_" + color.getSerializedName() + "_shulker_box",
                    properties -> new ReinforcedShulkerBoxBlock(color, properties
                            .strength(50f, 5000f).noOcclusion().dynamicShape()
                            .requiresCorrectToolForDrops()), Rarity.UNCOMMON, 1, true));
        }
        REINFORCED_SHULKER_BOX = registerBlock("reinforced_shulker_box",
                properties -> new ReinforcedShulkerBoxBlock(null, properties
                        .strength(50f, 5000f).noOcclusion().dynamicShape()
                        .requiresCorrectToolForDrops()), Rarity.UNCOMMON, 1, true);
    }

    public static final Block ECHO_BLOCK = registerBlock("echo_block",
            properties -> new Block(properties.strength(75f, 2000f)
                    .lightLevel(statex -> 3)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)), Rarity.UNCOMMON, 64, true);
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
                    .pushReaction(PushReaction.IGNORE)), Rarity.COMMON, 64, true);
    public static final Block DREAMED_OBSIDIAN = registerBlock("dreamed_obsidian",
            properties -> new Block(properties.strength(200f, 5000f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)), Rarity.COMMON, 64, true);
    public static final Block DREAMED_POWERED_OBSIDIAN = registerBlock("dreamed_powered_obsidian",
            properties -> new Block(properties.strength(250f, 6000f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)), Rarity.COMMON, 64, true);

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

    public static final Block FLOWTAREM_CROP = registerBlockWithoutBlockItem("flowtarem_crop",
    properties -> new FlowtaremCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
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

    public static final Block DREAM_GREEN_LOG = registerBlock("dream_green_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_GREEN_LOG = registerBlock("stripped_dream_green_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_WOOD = registerBlock("dream_green_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_GREEN_WOOD = registerBlock("stripped_dream_green_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_PLANKS = registerBlock("dream_green_planks",
            properties -> new Block(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_LEAVES = registerBlock("dream_green_leaves",
            properties -> new TintedParticleLeavesBlock(0.01f,
                    properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                            .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));
    public static final Block DREAM_GREEN_WOOD_STAIRS = registerBlock("dream_green_wood_stairs",
            properties -> new StairBlock(ModBlocks.DREAM_GREEN_PLANKS.defaultBlockState() ,properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_WOOD_SLAB = registerBlock("dream_green_wood_slab",
            properties -> new SlabBlock(properties.strength(0.5f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_WOOD_BUTTON = registerBlock("dream_green_wood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 25, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision()));
    public static final Block DREAM_GREEN_WOOD_PRESSURE_PLATE = registerBlock("dream_green_wood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK, properties.mapColor(MapColor.COLOR_GREEN).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5f).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_GREEN_WOOD_FENCE = registerBlock("dream_green_wood_fence",
            properties -> new FenceBlock(properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_WOOD_FENCE_GATE = registerBlock("dream_green_wood_fence_gate",
            properties -> new FenceGateBlock(ModWoodType.DREAM_GREEN, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_GREEN_WOOD_DOOR = registerBlock("dream_green_wood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_GREEN_WOOD_TRAPDOOR = registerBlock("dream_green_wood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_GREEN_WOOD_SIGN = registerBlockWithoutBlockItem("dream_green_wood_sign",
            properties -> new StandingSignBlock(ModWoodType.DREAM_GREEN, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_GREEN_WOOD_WALL_SIGN = registerBlockWithoutBlockItem("dream_green_wood_wall_sign",
            properties -> new WallSignBlock(ModWoodType.DREAM_GREEN, wallVariant(properties, DREAM_GREEN_WOOD_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_GREEN_WOOD_HANGING_SIGN = registerBlockWithoutBlockItem("dream_green_wood_hanging_sign",
            properties -> new CeilingHangingSignBlock(ModWoodType.DREAM_GREEN, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_GREEN_WOOD_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("dream_green_wood_wall_hanging_sign",
            properties -> new WallHangingSignBlock(ModWoodType.DREAM_GREEN, wallVariant(properties, DREAM_GREEN_WOOD_HANGING_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_GREEN_SAPLING = registerBlock("dream_green_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.DREAM_GREEN_WOOD, properties.randomTicks().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY).noCollision().mapColor(MapColor.PLANT)));
    public static final Block POTTED_DREAM_GREEN_SAPLING = registerBlockWithoutBlockItem("potted_dream_green_sapling",
            properties -> new FlowerPotBlock(DREAM_GREEN_SAPLING, properties.instabreak()
                    .pushReaction(PushReaction.DESTROY).noCollision()));

    public static final Block DREAM_PURPLE_LOG = registerBlock("dream_purple_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_PURPLE_LOG = registerBlock("stripped_dream_purple_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_WOOD = registerBlock("dream_purple_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_PURPLE_WOOD = registerBlock("stripped_dream_purple_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_PLANKS = registerBlock("dream_purple_planks",
            properties -> new Block(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_LEAVES = registerBlock("dream_purple_leaves",
            properties -> new TintedParticleLeavesBlock(0.01f,
                    properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                            .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));
    public static final Block DREAM_PURPLE_WOOD_STAIRS = registerBlock("dream_purple_wood_stairs",
            properties -> new StairBlock(ModBlocks.DREAM_GREEN_PLANKS.defaultBlockState() ,properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_WOOD_SLAB = registerBlock("dream_purple_wood_slab",
            properties -> new SlabBlock(properties.strength(0.5f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_WOOD_BUTTON = registerBlock("dream_purple_wood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 25, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision()));
    public static final Block DREAM_PURPLE_WOOD_PRESSURE_PLATE = registerBlock("dream_purple_wood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK, properties.mapColor(MapColor.COLOR_PURPLE).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5f).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_PURPLE_WOOD_FENCE = registerBlock("dream_purple_wood_fence",
            properties -> new FenceBlock(properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_WOOD_FENCE_GATE = registerBlock("dream_purple_wood_fence_gate",
            properties -> new FenceGateBlock(ModWoodType.DREAM_PURPLE, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_PURPLE_WOOD_DOOR = registerBlock("dream_purple_wood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_PURPLE_WOOD_TRAPDOOR = registerBlock("dream_purple_wood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_PURPLE_WOOD_SIGN = registerBlockWithoutBlockItem("dream_purple_wood_sign",
            properties -> new StandingSignBlock(ModWoodType.DREAM_PURPLE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_PURPLE_WOOD_WALL_SIGN = registerBlockWithoutBlockItem("dream_purple_wood_wall_sign",
            properties -> new WallSignBlock(ModWoodType.DREAM_PURPLE, wallVariant(properties, DREAM_PURPLE_WOOD_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_PURPLE_WOOD_HANGING_SIGN = registerBlockWithoutBlockItem("dream_purple_wood_hanging_sign",
            properties -> new CeilingHangingSignBlock(ModWoodType.DREAM_PURPLE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_PURPLE_WOOD_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("dream_purple_wood_wall_hanging_sign",
            properties -> new WallHangingSignBlock(ModWoodType.DREAM_PURPLE, wallVariant(properties, DREAM_PURPLE_WOOD_HANGING_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_PURPLE_SAPLING = registerBlock("dream_purple_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.DREAM_PURPLE_WOOD, properties.randomTicks().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY).noCollision().mapColor(MapColor.PLANT)));
    public static final Block POTTED_DREAM_PURPLE_SAPLING = registerBlockWithoutBlockItem("potted_dream_purple_sapling",
            properties -> new FlowerPotBlock(DREAM_PURPLE_SAPLING, properties.instabreak()
                    .pushReaction(PushReaction.DESTROY).noCollision()));

    public static final Block DREAM_BLUE_LOG = registerBlock("dream_blue_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_BLUE_LOG = registerBlock("stripped_dream_blue_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_WOOD = registerBlock("dream_blue_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_BLUE_WOOD = registerBlock("stripped_dream_blue_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_PLANKS = registerBlock("dream_blue_planks",
            properties -> new Block(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_LEAVES = registerBlock("dream_blue_leaves",
            properties -> new TintedParticleLeavesBlock(0.01f,
                    properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                            .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));
    public static final Block DREAM_BLUE_WOOD_STAIRS = registerBlock("dream_blue_wood_stairs",
            properties -> new StairBlock(ModBlocks.DREAM_GREEN_PLANKS.defaultBlockState() ,properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_WOOD_SLAB = registerBlock("dream_blue_wood_slab",
            properties -> new SlabBlock(properties.strength(0.5f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_WOOD_BUTTON = registerBlock("dream_blue_wood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 25, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision()));
    public static final Block DREAM_BLUE_WOOD_PRESSURE_PLATE = registerBlock("dream_blue_wood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK, properties.mapColor(MapColor.COLOR_BLUE).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5f).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_BLUE_WOOD_FENCE = registerBlock("dream_blue_wood_fence",
            properties -> new FenceBlock(properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_WOOD_FENCE_GATE = registerBlock("dream_blue_wood_fence_gate",
            properties -> new FenceGateBlock(ModWoodType.DREAM_BLUE, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_BLUE_WOOD_DOOR = registerBlock("dream_blue_wood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_BLUE_WOOD_TRAPDOOR = registerBlock("dream_blue_wood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_BLUE_WOOD_SIGN = registerBlockWithoutBlockItem("dream_blue_wood_sign",
            properties -> new StandingSignBlock(ModWoodType.DREAM_BLUE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_BLUE_WOOD_WALL_SIGN = registerBlockWithoutBlockItem("dream_blue_wood_wall_sign",
            properties -> new WallSignBlock(ModWoodType.DREAM_BLUE, wallVariant(properties, DREAM_BLUE_WOOD_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_BLUE_WOOD_HANGING_SIGN = registerBlockWithoutBlockItem("dream_blue_wood_hanging_sign",
            properties -> new CeilingHangingSignBlock(ModWoodType.DREAM_BLUE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_BLUE_WOOD_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("dream_blue_wood_wall_hanging_sign",
            properties -> new WallHangingSignBlock(ModWoodType.DREAM_BLUE, wallVariant(properties, DREAM_BLUE_WOOD_HANGING_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_BLUE_SAPLING = registerBlock("dream_blue_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.DREAM_BLUE_WOOD, properties.randomTicks().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY).noCollision().mapColor(MapColor.PLANT)));
    public static final Block POTTED_DREAM_BLUE_SAPLING = registerBlockWithoutBlockItem("potted_blue_green_sapling",
            properties -> new FlowerPotBlock(DREAM_BLUE_SAPLING, properties.instabreak()
                    .pushReaction(PushReaction.DESTROY).noCollision()));

    public static final Block DREAM_LIGHT_BLUE_LOG = registerBlock("dream_light_blue_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_LIGHT_BLUE_LOG = registerBlock("stripped_dream_light_blue_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_WOOD = registerBlock("dream_light_blue_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DREAM_LIGHT_BLUE_WOOD = registerBlock("stripped_dream_light_blue_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_PLANKS = registerBlock("dream_light_blue_planks",
            properties -> new Block(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_LEAVES = registerBlock("dream_light_blue_leaves",
            properties -> new TintedParticleLeavesBlock(0.01f,
                    properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                            .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));
    public static final Block DREAM_LIGHT_BLUE_WOOD_STAIRS = registerBlock("dream_light_blue_wood_stairs",
            properties -> new StairBlock(ModBlocks.DREAM_GREEN_PLANKS.defaultBlockState() ,properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_SLAB = registerBlock("dream_light_blue_wood_slab",
            properties -> new SlabBlock(properties.strength(0.5f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_BUTTON = registerBlock("dream_light_blue_wood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 25, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_PRESSURE_PLATE = registerBlock("dream_light_blue_wood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK, properties.mapColor(MapColor.COLOR_LIGHT_BLUE).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5f).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Block DREAM_LIGHT_BLUE_WOOD_FENCE = registerBlock("dream_light_blue_wood_fence",
            properties -> new FenceBlock(properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_FENCE_GATE = registerBlock("dream_light_blue_wood_fence_gate",
            properties -> new FenceGateBlock(ModWoodType.DREAM_LIGHT_BLUE, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_DOOR = registerBlock("dream_light_blue_wood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_TRAPDOOR = registerBlock("dream_light_blue_wood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties.strength(2f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_SIGN = registerBlockWithoutBlockItem("dream_light_blue_wood_sign",
            properties -> new StandingSignBlock(ModWoodType.DREAM_LIGHT_BLUE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_WALL_SIGN = registerBlockWithoutBlockItem("dream_light_blue_wood_wall_sign",
            properties -> new WallSignBlock(ModWoodType.DREAM_LIGHT_BLUE, wallVariant(properties, DREAM_LIGHT_BLUE_WOOD_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN = registerBlockWithoutBlockItem("dream_light_blue_wood_hanging_sign",
            properties -> new CeilingHangingSignBlock(ModWoodType.DREAM_LIGHT_BLUE, properties.strength(1f)
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN = registerBlockWithoutBlockItem("dream_light_blue_wood_wall_hanging_sign",
            properties -> new WallHangingSignBlock(ModWoodType.DREAM_LIGHT_BLUE, wallVariant(properties, DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, true)
                    .strength(1f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).ignitedByLava().noCollision().forceSolidOn()));
    public static final Block DREAM_LIGHT_BLUE_SAPLING = registerBlock("dream_light_blue_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.DREAM_LIGHT_BLUE_WOOD, properties.randomTicks().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY).noCollision().mapColor(MapColor.PLANT)));
    public static final Block POTTED_DREAM_LIGHT_BLUE_SAPLING = registerBlockWithoutBlockItem("potted_dream_light_blue_sapling",
            properties -> new FlowerPotBlock(DREAM_LIGHT_BLUE_SAPLING, properties.instabreak()
                    .pushReaction(PushReaction.DESTROY).noCollision()));

    public static final Block DREAM_PORTAL = registerBlockWithoutBlockItem("dream_portal", properties ->
            new DreamPortalBlock(properties.noCollision().noLootTable().strength(-1.0F).lightLevel(s -> 11)
            .pushReaction(PushReaction.BLOCK).noOcclusion()));

    public static void registerModSignBlocks() {
        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.SIGN),
                DREAM_GREEN_WOOD_SIGN, DREAM_GREEN_WOOD_WALL_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.HANGING_SIGN),
                DREAM_GREEN_WOOD_HANGING_SIGN, DREAM_GREEN_WOOD_WALL_HANGING_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.SIGN),
                DREAM_PURPLE_WOOD_SIGN, DREAM_PURPLE_WOOD_WALL_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.HANGING_SIGN),
                DREAM_PURPLE_WOOD_HANGING_SIGN, DREAM_PURPLE_WOOD_WALL_HANGING_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.SIGN),
                DREAM_BLUE_WOOD_SIGN, DREAM_BLUE_WOOD_WALL_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.HANGING_SIGN),
                DREAM_BLUE_WOOD_HANGING_SIGN, DREAM_BLUE_WOOD_WALL_HANGING_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.SIGN),
                DREAM_LIGHT_BLUE_WOOD_SIGN, DREAM_LIGHT_BLUE_WOOD_WALL_SIGN);

        addValidBlocks(BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(BlockEntityTypeIds.HANGING_SIGN),
                DREAM_LIGHT_BLUE_WOOD_HANGING_SIGN, DREAM_LIGHT_BLUE_WOOD_WALL_HANGING_SIGN);
    }

    private static void addValidBlocks(BlockEntityType<?> type, Block... blocks) {
        BlockEntityTypeAccessor accessor = (BlockEntityTypeAccessor) type;
        Set<Block> set = new HashSet<>(accessor.nbp$getValidBlocks());
        set.addAll(Arrays.asList(blocks));
        accessor.nbp$setValidBlocks(set);
    }

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

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, Rarity rarity, int stacksTo, boolean fireResistant){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        registerBlockItem(name, toRegister, rarity, stacksTo, fireResistant);

        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Rarity rarity, int stacksTo, boolean fireResistant) {
        Item.Properties properties = new Item.Properties().useBlockDescriptionPrefix().rarity(rarity).stacksTo(stacksTo)
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name)));
        if (fireResistant) {
            properties.fireResistant();
        }
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new BlockItem(block, properties));
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name), toRegister);
    }

    private static BlockBehaviour.Properties wallVariant(BlockBehaviour.Properties properties, Block standing, boolean copyMapColor) {
        return properties.overrideLootTable(standing.getLootTable())
                .overrideDescription(standing.getDescriptionId());
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static void registerModBlocks(){
        NBPVanillaPlus.LOGGER.info("Registering Mod Blocks for " + NBPVanillaPlus.MOD_ID);
    }

}
