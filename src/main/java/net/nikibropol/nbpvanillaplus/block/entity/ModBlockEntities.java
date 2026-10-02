package net.nikibropol.nbpvanillaplus.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.entity.custom.ReinforcedShulkerBoxBlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockEntities {

    public static final BlockEntityType<ReinforcedShulkerBoxBlockEntity> REINFORCED_SHULKER_BOX_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "reinforced_shulker_box_be"),
                    buildBoxType());

    /*public static final BlockEntityType<SignBlockEntity> DREAM_GREEN_WOOD_SIGN_BE =
            register(BlockEntityTypeIds.SIGN, SignBlockEntity::new, "dream_green_wood_sign_be", ModBlocks.DREAM_GREEN_WOOD_SIGN);
    public static final BlockEntityType<SignBlockEntity> DREAM_GREEN_WOOD_HANGING_SIGN_BE =
            register(BlockEntityTypeIds.HANGING_SIGN, HangingSignBlockEntity::new, "dream_green_wood_hanging_sign_be", ModBlocks.DREAM_GREEN_WOOD_WALL_HANGING_SIGN);*/

    private static BlockEntityType<ReinforcedShulkerBoxBlockEntity> buildBoxType() {
        List<Block> blocks = new ArrayList<>(ModBlocks.REINFORCED_SHULKER_BOXES.values());
        blocks.add(ModBlocks.REINFORCED_SHULKER_BOX);
        return FabricBlockEntityTypeBuilder.create(
                (pos, state) -> new ReinforcedShulkerBoxBlockEntity(pos, state, null),
                blocks.toArray(new Block[0])
        ).build();
    }
    /*private static <T extends BlockEntity> BlockEntityType<T> register(ResourceKey<BlockEntityType<?>> key, String path, Block... validBlocks) {
        Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, path);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, key, new BlockEntityType(factory, Set.of(validBlocks)));
    }*/


    public static void registerBlockEntities() {
        NBPVanillaPlus.LOGGER.info("Registering ModBlockEntities for " + NBPVanillaPlus.MOD_ID);
    }
}
