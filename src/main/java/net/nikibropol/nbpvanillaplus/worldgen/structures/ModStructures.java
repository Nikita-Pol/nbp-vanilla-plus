package net.nikibropol.nbpvanillaplus.worldgen.structures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModStructures {
    public static final Map<String, List<Pair<String, Integer>>> HOUSES = Map.of(
            "green", List.of(
                    Pair.of("dream_green_house_a", 3)),
            "purple", List.of(
                    Pair.of("dream_purple_house_a", 3)),
            "blue", List.of(
                    Pair.of("dream_blue_house_a", 3)
                    /*Pair.of("dream_blue_house_b", 2)*/),
            "light_blue", List.of(
                    Pair.of("dream_light_blue_house_a", 3)));

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, path);
    }

    public static ResourceKey<StructureTemplatePool> pool(String color) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, id("dream_" + color + "_house"));
    }

    public static ResourceKey<Structure> structure(String color) {
        return ResourceKey.create(Registries.STRUCTURE, id("dream_" + color + "_house"));
    }

    public static ResourceKey<StructureSet> set(String color) {
        return ResourceKey.create(Registries.STRUCTURE_SET, id("dream_" + color + "_house"));
    }

    public static TagKey<Biome> biomeTag(String color) {
        return TagKey.create(Registries.BIOME, id("has_structure/dream_" + color + "_house"));
    }

    public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
        var pools = context.lookup(Registries.TEMPLATE_POOL);
        var empty = context.lookup(Registries.PROCESSOR_LIST).getOrThrow(ProcessorLists.EMPTY);
        for (var e : HOUSES.entrySet()) {
            List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> elements = new ArrayList<>();
            for (var v : e.getValue()) {
                elements.add(Pair.of(StructurePoolElement.single(id(v.getFirst()).toString(), empty), v.getSecond()));
            }
            context.register(pool(e.getKey()), new StructureTemplatePool(
                    pools.getOrThrow(net.minecraft.data.worldgen.Pools.EMPTY),
                    elements,
                    StructureTemplatePool.Projection.RIGID));
        }
    }

    public static void bootstrapStructures(BootstrapContext<Structure> context) {
        var biomes = context.lookup(Registries.BIOME);
        var pools = context.lookup(Registries.TEMPLATE_POOL);
        for (String c : HOUSES.keySet()) {
            context.register(structure(c), new JigsawStructure(
                    new Structure.StructureSettings(
                            biomes.getOrThrow(biomeTag(c)),
                            Map.of(),
                            GenerationStep.Decoration.SURFACE_STRUCTURES,
                            TerrainAdjustment.BEARD_THIN),
                    pools.getOrThrow(pool(c)),
                    2,
                    ConstantHeight.of(VerticalAnchor.absolute(2)),
                    false,
                    Heightmap.Types.WORLD_SURFACE_WG));
        }
    }

    public static void bootstrapSets(BootstrapContext<StructureSet> context) {
        var structures = context.lookup(Registries.STRUCTURE);
        for (String color : HOUSES.keySet()) {
            context.register(set(color), new StructureSet(
                    structures.getOrThrow(structure(color)),
                    new RandomSpreadStructurePlacement(20, 12, RandomSpreadType.LINEAR, 1987654321 + color.hashCode())));
        }
    }
}
