package net.nikibropol.nbpvanillaplus.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.biome.Biomes;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

public class PaleFogClient {
    private static final int RADIUS = 8;       // horizontal radius
    private static final int FULL_AT = 480;     // Amount of blocks for full intensity
    private static float intensity = 0f, target = 0f;

    public static float intensity() { return intensity; }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientLevel level = client.level;
            if (level == null || client.player == null) { intensity = 0f; target = 0f; return; }
            if (level.getGameTime() % 10 == 0) {
                BlockPos pos = client.player.blockPosition();
                float treeTarget = Mth.clamp(count(level, pos) / (float) FULL_AT, 0f, 1f);
                float biomeTarget = level.getBiome(pos).is(Biomes.PALE_GARDEN) ? 1f : 0f;
                target = Math.max(treeTarget, biomeTarget);
            }
            float speed = target > intensity ? 0.1f : 0.025f; // Fast arrive : Slow disappear
            intensity += (target - intensity) * speed; //Smooth transition
        });
    }

    private static int count(ClientLevel level, BlockPos blockPos) {
        int number = 0;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int x = -RADIUS; x <= RADIUS; x++)
            for (int y = -4; y <= 6; y++)
                for (int z = -RADIUS; z <= RADIUS; z++) {
                    mutableBlockPos.set(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                    if (level.getBlockState(mutableBlockPos).is(ModTags.Blocks.PALE_FOG_SOURCES)) number++;
                }
        return number;
    }

    public static float nightFactor(long dayTime) {
        long ticks = Math.floorMod(dayTime, 24000L);
        if (ticks < 133) ticks += 24000;

        float f;
        if (ticks < 11867)
            f = 0f; // day
        else if (ticks < 13670)
            f = (ticks - 11867) / (13670f - 11867f);
        else if (ticks < 22330)
            f = 1f; // night
        else
            f = 1f - (ticks - 22330) / (24133f - 22330f);

        return f * f * (3f - 2f * f); // smooth transition
    }

    private static Holder<WorldClock> cachedClock;
    private static ClientLevel cachedFor;

    public static float greyAmount() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        ClientPacketListener connection = minecraft.getConnection();
        if (level == null || connection == null) return 0f;

        if (cachedClock == null || cachedFor != level) {
            cachedClock = level.registryAccess()
                    .lookupOrThrow(Registries.WORLD_CLOCK)
                    .getOrThrow(WorldClocks.OVERWORLD);
            cachedFor = level;
        }

        ClockManager clocks = connection.clockManager();
        long ticks = clocks.getTotalTicks(cachedClock);
        return intensity() * nightFactor(ticks);
    }
}
