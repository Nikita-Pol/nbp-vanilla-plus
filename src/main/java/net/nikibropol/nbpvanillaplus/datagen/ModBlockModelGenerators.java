package net.nikibropol.nbpvanillaplus.datagen;

import com.mojang.math.Quadrant;
import com.mojang.math.Transformation;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.blockentity.ShulkerBoxRenderer;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.ShulkerBoxSpecialRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.DreamEchoStatueBlock;
import net.nikibropol.nbpvanillaplus.block.custom.DreamPetalsBlock;
import net.nikibropol.nbpvanillaplus.block.custom.DreamPortalBlock;
import org.jspecify.annotations.Nullable;

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

    public static void generateDreamEchoStatue(BlockModelGenerators generator, Block block) {
        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(ModBlocks.DREAM_ECHO_STATUE)
                        .with(PropertyDispatch.initial(DreamEchoStatueBlock.FACE, DreamEchoStatueBlock.FACING)
                                .generate((face, facing) -> {
                                    Quadrant xRot = switch (face) {
                                        case CEILING -> Quadrant.R180;
                                        case WALL -> Quadrant.R90;
                                        case FLOOR -> Quadrant.R0;
                                    };
                                    return new MultiVariant(WeightedList.of(
                                            new Variant(ModelLocationUtils.getModelLocation(ModBlocks.DREAM_ECHO_STATUE))
                                                    .withXRot(xRot)
                                                    .withYRot(quadrantFromDirection(facing))));
                                }))
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

    public static void createReinforcedShulkerBox(BlockModelGenerators blockModelGenerators, Block block, @Nullable DyeColor color) {
        blockModelGenerators.createParticleOnlyBlock(block);
        Item item = block.asItem();
        Identifier baseModel = ModelTemplates.SHULKER_BOX_INVENTORY.create(item, TextureMapping.particle(block), blockModelGenerators.modelOutput);
        Transformation transformation = ShulkerBoxRenderer.modelTransform(Direction.UP);
        ItemModel.Unbaked itemModel = color != null
                ? ItemModelUtils.specialModel(baseModel, transformation,
                new ShulkerBoxSpecialRenderer.Unbaked(
                        Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker_" + color.getSerializedName()),
                        0.0F))
                : ItemModelUtils.specialModel(baseModel, transformation,
                new ShulkerBoxSpecialRenderer.Unbaked(
                        Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker"),
                        0.0F));

        blockModelGenerators.itemModelOutput.accept(item, itemModel);
    }
    static void createSignModels(BlockModelGenerators blockModelGenerators, Block sign, Block wallSign, Block particle) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(sign))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(particle));

        MultiVariant r0 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils
                .getModelLocation(sign, "_rot_0"), mapping, blockModelGenerators.modelOutput));
        MultiVariant r1 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils
                .getModelLocation(sign, "_rot_1"), mapping, blockModelGenerators.modelOutput));
        MultiVariant r2 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils
                .getModelLocation(sign, "_rot_2"), mapping, blockModelGenerators.modelOutput));
        MultiVariant r3 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils
                .getModelLocation(sign, "_rot_3"), mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSign(sign, r0, r1, r2, r3));

        MultiVariant wall = BlockModelGenerators.plainVariant(ModelTemplates.WALL_SIGN
                .create(wallSign, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallSign, wall)
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
    }

    static void createHangingSignModels(BlockModelGenerators blockModelGenerators, Block hanging, Block wallHanging, Block particle) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(hanging))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(particle));

        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createHangingSign(hanging,
                BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_0.create(ModelLocationUtils
                        .getModelLocation(hanging, "_rot_0"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_1.create(ModelLocationUtils
                        .getModelLocation(hanging, "_rot_1"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_2.create(ModelLocationUtils
                        .getModelLocation(hanging, "_rot_2"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_3.create(ModelLocationUtils
                        .getModelLocation(hanging, "_rot_3"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0.create(ModelLocationUtils
                        .getModelLocation(hanging, "_attached_rot_0"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1.create(ModelLocationUtils
                        .getModelLocation(hanging, "_attached_rot_1"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2.create(ModelLocationUtils
                        .getModelLocation(hanging, "_attached_rot_2"), mapping, blockModelGenerators.modelOutput)),
                BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3.create(ModelLocationUtils
                        .getModelLocation(hanging, "_attached_rot_3"), mapping, blockModelGenerators.modelOutput))));

        MultiVariant wall = BlockModelGenerators.plainVariant(ModelTemplates.WALL_HANGING_SIGN
                .create(wallHanging, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallHanging, wall)
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
    }

    private static final TextureSlot PORTAL_SLOT = TextureSlot.create("portal");

    private static final ModelTemplate PORTAL_EW = new ModelTemplate(
            Optional.of(Identifier.withDefaultNamespace("block/nether_portal_ew")),
            Optional.of("_ew"), PORTAL_SLOT);
    private static final ModelTemplate PORTAL_NS = new ModelTemplate(
            Optional.of(Identifier.withDefaultNamespace("block/nether_portal_ns")),
            Optional.of("_ns"), PORTAL_SLOT);

    static void generateDreamPortal(BlockModelGenerators blockModelGenerators) {
        TextureMapping textureMapping = new TextureMapping()
                .put(PORTAL_SLOT, TextureMapping.getBlockTexture(ModBlocks.DREAM_PORTAL));

        Identifier ew = PORTAL_EW.create(ModBlocks.DREAM_PORTAL, textureMapping, blockModelGenerators.modelOutput);
        Identifier ns = PORTAL_NS.create(ModBlocks.DREAM_PORTAL, textureMapping, blockModelGenerators.modelOutput);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.DREAM_PORTAL)
                .with(PropertyDispatch.initial(DreamPortalBlock.AXIS)
                        .select(Direction.Axis.X, BlockModelGenerators.plainVariant(ns))
                        .select(Direction.Axis.Z, BlockModelGenerators.plainVariant(ew))));
    }
}
