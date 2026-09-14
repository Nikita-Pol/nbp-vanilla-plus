package net.nikibropol.nbpvanillaplus.datagen;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.DreamPetalsBlock;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators{

    public ModBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    public static void generateEchoVines(BlockModelGenerators blockModelGenerators) {
        MultiVariant offHead = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.ECHO_VINES, "", ModelTemplates.CROSS, TextureMapping::cross));
        MultiVariant onHead = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.ECHO_VINES, "_lit", ModelTemplates.CROSS, TextureMapping::cross));
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(ModBlocks.ECHO_VINES)
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BERRIES, onHead, offHead))
        );

        MultiVariant offBody = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.ECHO_VINES_PLANT, "", ModelTemplates.CROSS, TextureMapping::cross));
        MultiVariant onBody = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.ECHO_VINES_PLANT, "_lit", ModelTemplates.CROSS, TextureMapping::cross));
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(ModBlocks.ECHO_VINES_PLANT)
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BERRIES, onBody, offBody))
        );
    }

    private static final ModelTemplate DREAM_PETALS_1 = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(
                    "nbp-vanilla-plus",
                    "block/template_dream_petals_1"
            )),
            Optional.of("_1"),
            TextureSlot.TEXTURE
    );

    private static final ModelTemplate DREAM_PETALS_2 = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(
                    "nbp-vanilla-plus",
                    "block/template_dream_petals_2"
            )),
            Optional.of("_2"),
            TextureSlot.TEXTURE
    );

    private static final ModelTemplate DREAM_PETALS_3 = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(
                    "nbp-vanilla-plus",
                    "block/template_dream_petals_3"
            )),
            Optional.of("_3"),
            TextureSlot.TEXTURE
    );

    private static final ModelTemplate DREAM_PETALS_4 = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(
                    "nbp-vanilla-plus",
                    "block/template_dream_petals_4"
            )),
            Optional.of("_4"),
            TextureSlot.TEXTURE
    );

    public static void generateDreamPetals(BlockModelGenerators blockModelGenerators, Block block) {
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block));


        Identifier model1 = DREAM_PETALS_1.create(block, textures, blockModelGenerators.modelOutput);
        Identifier model2 = DREAM_PETALS_2.create(block, textures, blockModelGenerators.modelOutput);
        Identifier model3 = DREAM_PETALS_3.create(block, textures, blockModelGenerators.modelOutput);
        Identifier model4 = DREAM_PETALS_4.create(block, textures, blockModelGenerators.modelOutput);

        Map<Integer, Identifier> modelsByAmount = Map.of(1, model1, 2, model2, 3, model3, 4, model4);

        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                        .with(PropertyDispatch.initial(DreamPetalsBlock.AMOUNT, DreamPetalsBlock.FACING)
                                .generate((amount, facing) -> new MultiVariant(
                                        WeightedList.of(new Variant(modelsByAmount.get(amount))
                                                .withYRot(quadrantFromDirection(facing))))))
        );
    }

    private static Quadrant quadrantFromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> Quadrant.R0;
            case EAST -> Quadrant.R90;
            case SOUTH -> Quadrant.R180;
            case WEST -> Quadrant.R270;
            default -> Quadrant.R0;
        };
    }
}
