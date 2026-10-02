package net.nikibropol.nbpvanillaplus.worldgen.dimension;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModPortals {

    public record Portal(Supplier<Block> frame, Supplier<Item> activator,
                         ResourceKey<Level> target, ResourceKey<Level> home) {

    }

    private static final List<Portal> PORTALS = new ArrayList<>();

    public static final Portal DREAMSCAPE = add(new Portal(
            () -> ModBlocks.DREAMED_POWERED_OBSIDIAN,
            () -> ModItems.FUSED_ECHO_SHARD,
            ModDimensions.DREAMSCAPE_LEVEL_KEY, Level.OVERWORLD));


    private static Portal add(Portal portal) {
        PORTALS.add(portal);
        return portal;
    }

    static {
        UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
            if (level.isClientSide()) return InteractionResult.PASS;
            if (!(player instanceof ServerPlayer serverPlayer)) return InteractionResult.PASS;

            var held = player.getItemInHand(hand);
            var clicked = level.getBlockState(hit.getBlockPos());
            for (Portal portal : PORTALS) {
                if (!held.is(portal.activator().get())) continue;
                if (!clicked.is(portal.frame().get())) continue;

                BlockPos inside = hit.getBlockPos().relative(hit.getDirection());
                for (Direction.Axis axis : new Direction.Axis[]{Direction.Axis.X, Direction.Axis.Z}) {
                    var shape = DreamPortalShape.find(level, inside, axis);
                    if (shape.isPresent()) {
                        shape.get().fill(level);
                        level.playSound(null, inside, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 1.0f);
                        if (!player.getAbilities().instabuild) held.shrink(1);
                        level.playSound(null, inside, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    private static InteractionResult teleport(ServerPlayer serverPlayer, Portal portal) {
        var server = serverPlayer.level().getServer();
        boolean inTarget = serverPlayer.level().dimension().equals(portal.target());
        ServerLevel level = server.getLevel(inTarget ? portal.home() : portal.target());
        if (level == null) return InteractionResult.PASS;

        int x = serverPlayer.getBlockX(), z = serverPlayer.getBlockZ();
        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z) + 1;
        serverPlayer.teleportTo(level, x + 0.5, y, z + 0.5, Set.of(),
                serverPlayer.getYRot(), serverPlayer.getXRot(), true);
        return InteractionResult.SUCCESS;
    }

    public static void registerPortals() {
        NBPVanillaPlus.LOGGER.info("Registering portals for " + NBPVanillaPlus.MOD_ID);
    }
}