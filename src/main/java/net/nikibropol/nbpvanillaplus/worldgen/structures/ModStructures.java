package net.nikibropol.nbpvanillaplus.worldgen.structures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
import net.nikibropol.nbpvanillaplus.datagen.ModBiomeTagsProvider;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModStructures {

    public record StructureDefinition(String name,
                                      List<Pair<String, Integer>> variants,
                                      TagKey<Biome> biomeTag,
                                      int spacing, int separation, int salt,
                                      int depth, int yOffset,
                                      TerrainAdjustment terrainAdjustment) {
        public ResourceKey<StructureTemplatePool> poolKey() {
            return ResourceKey.create(Registries.TEMPLATE_POOL, createIdentifier(name));
        }

        public ResourceKey<Structure> structureKey() {
            return ResourceKey.create(Registries.STRUCTURE, createIdentifier(name));
        }

        public ResourceKey<StructureSet> structureSetKey() {
            return ResourceKey.create(Registries.STRUCTURE_SET, createIdentifier(name));
        }
    }

    public record VariantDefinition(String suffix, int weight) {}

    private static List<Pair<String, Integer>> createVariants(String baseName, VariantDefinition... variantDefinitions) {
        List<Pair<String, Integer>> variants = new ArrayList<>();
        for (VariantDefinition variantDefinition : variantDefinitions) {
            variants.add(Pair.of(baseName + "_" + variantDefinition.suffix(), variantDefinition.weight()));
        }
        return variants;
    }
    private static StructureDefinition createHouse(String color, VariantDefinition... variantDefinitions) {
        return new StructureDefinition("dream_" + color + "_house",
                createVariants("dream_" + color + "_house", variantDefinitions),
                ModBiomeTagsProvider.HOUSE_TAGS.get(color),
                24, 14, 1987654321 + color.hashCode(), 2, 2, TerrainAdjustment.BEARD_THIN);
    }

    private static StructureDefinition createLake(String color, int saltOffset, VariantDefinition... variantDefinitions) {
        return new StructureDefinition("dream_" + color + "_lake",
                createVariants("dream_" + color + "_lake", variantDefinitions),
                ModBiomeTagsProvider.LAKE_TAGS.get(color),
                15, 6, 3003000 + saltOffset, 1, -5, TerrainAdjustment.NONE);
    }

    private static StructureDefinition createSculkedHouse(String biomeType, int saltOffset, VariantDefinition... variantDefinitions) {
        return new StructureDefinition(biomeType + "_sculked_house",
                createVariants(biomeType + "_sculked_house", variantDefinitions),
                ModBiomeTagsProvider.SCULKED_HOUSE_TAGS.get(biomeType),
                64, 32, 4004000 + saltOffset, 2, 2, TerrainAdjustment.BEARD_THIN);
    }

    private static final VariantDefinition[] HOUSE_VARIANTS = {
            new VariantDefinition("a", 1),
            new VariantDefinition("b", 5),
            new VariantDefinition("c", 5),
            new VariantDefinition("d", 5),
            new VariantDefinition("e", 3),
            new VariantDefinition("f", 2),
            new VariantDefinition("g", 4),
            new VariantDefinition("h", 3)
    };

    private static final VariantDefinition[] LAKE_VARIANTS = {
            new VariantDefinition("a", 1),
            new VariantDefinition("b", 1),
            new VariantDefinition("c", 5),
            new VariantDefinition("d", 5),
            new VariantDefinition("e", 4),
            new VariantDefinition("f", 3)
    };

    private static final VariantDefinition[] PLAINS_SCULKED_VARIANTS = {
            new VariantDefinition("a", 10), new VariantDefinition("b", 10),
            new VariantDefinition("c", 10), new VariantDefinition("d", 10)
    };

    private static final VariantDefinition[] OTHER_SCULKED_VARIANTS = {
            new VariantDefinition("a", 10), new VariantDefinition("b", 10)
    };
    // After All Variants!!!
    public static final List<StructureDefinition> ALL = createAll();

    private static List<StructureDefinition> createAll() {
        List<StructureDefinition> all = new ArrayList<>();
        int saltOffset = 1;
        for (String color : ModBiomeTagsProvider.DREAM_BIOMES.keySet()) {
            all.add(createHouse(color, HOUSE_VARIANTS));
            all.add(createLake(color, saltOffset, LAKE_VARIANTS));
            saltOffset++;
        }

        all.add(createSculkedHouse("plains", 1, PLAINS_SCULKED_VARIANTS));
        all.add(createSculkedHouse("desert", 2, OTHER_SCULKED_VARIANTS));
        all.add(createSculkedHouse("savanna", 3, OTHER_SCULKED_VARIANTS));
        all.add(createSculkedHouse("snowy", 4, OTHER_SCULKED_VARIANTS));
        all.add(createSculkedHouse("taiga", 5, OTHER_SCULKED_VARIANTS));

        all.add(new StructureDefinition("pale_garden_tower",
                List.of(Pair.of("pale_garden_tower", 1)),
                ModBiomeTagsProvider.HAS_PALE_GARDEN_TOWER,
                30, 15, 2002001, 1, 0, TerrainAdjustment.BEARD_THIN));

        all.add(new StructureDefinition("pale_garden_house",
                List.of(Pair.of("pale_garden_house", 1)),
                ModBiomeTagsProvider.HAS_PALE_GARDEN_HOUSE,
                40, 20, 2002001, 2, 2, TerrainAdjustment.BEARD_THIN));



        return all;
    }
    private static Identifier createIdentifier(String path) {
        return Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, path);
    }

    public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
        var poolLookup = context.lookup(Registries.TEMPLATE_POOL);
        var emptyProcessorList = context.lookup(Registries.PROCESSOR_LIST).getOrThrow(ProcessorLists.EMPTY);
        for (StructureDefinition structureDefinition : ALL) {
            List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> poolElements = new ArrayList<>();
            for (Pair<String, Integer> variant : structureDefinition.variants()) {
                poolElements.add(Pair.of(
                        StructurePoolElement.single(createIdentifier(variant.getFirst()).toString(), emptyProcessorList),
                        variant.getSecond()));
            }
            context.register(structureDefinition.poolKey(), new StructureTemplatePool(
                    poolLookup.getOrThrow(net.minecraft.data.worldgen.Pools.EMPTY),
                    poolElements,
                    StructureTemplatePool.Projection.RIGID));
        }
    }

    public static void bootstrapStructures(BootstrapContext<Structure> context) {
        var biomeLookup = context.lookup(Registries.BIOME);
        var poolLookup = context.lookup(Registries.TEMPLATE_POOL);
        for (StructureDefinition structureDefinition : ALL) {
            context.register(structureDefinition.structureKey(), new JigsawStructure(
                    new Structure.StructureSettings(
                            biomeLookup.getOrThrow(structureDefinition.biomeTag()),
                            Map.of(),
                            GenerationStep.Decoration.SURFACE_STRUCTURES,
                            structureDefinition.terrainAdjustment()),
                    poolLookup.getOrThrow(structureDefinition.poolKey()),
                    structureDefinition.depth(),
                    ConstantHeight.of(VerticalAnchor.absolute(structureDefinition.yOffset())),
                    false,
                    Heightmap.Types.WORLD_SURFACE_WG));
        }
    }

    public static void bootstrapSets(BootstrapContext<StructureSet> context) {
        var structureLookup = context.lookup(Registries.STRUCTURE);
        for (StructureDefinition structureDefinition : ALL) {
            context.register(structureDefinition.structureSetKey(), new StructureSet(
                    structureLookup.getOrThrow(structureDefinition.structureKey()),
                    new RandomSpreadStructurePlacement(
                            structureDefinition.spacing(),
                            structureDefinition.separation(),
                            RandomSpreadType.LINEAR,
                            structureDefinition.salt())));
        }
    }
}
