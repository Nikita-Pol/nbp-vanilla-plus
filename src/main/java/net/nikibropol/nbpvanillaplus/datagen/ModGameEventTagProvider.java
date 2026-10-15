package net.nikibropol.nbpvanillaplus.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.gameevent.GameEvent;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

public class ModGameEventTagProvider extends FabricTagsProvider<GameEvent> {
    public ModGameEventTagProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.GAME_EVENT, registries);
    }

    @Override
    protected void addTags(final HolderLookup.Provider registries) {
        tag(ModTags.GameEvents.ECHO_CREAKING_LISTENS)
                .add(GameEvent.STEP.key())
                .add(GameEvent.HIT_GROUND.key())
                .add(GameEvent.SWIM.key())
                .add(GameEvent.ENTITY_DAMAGE.key())
                .add(GameEvent.BLOCK_PLACE.key())
                .add(GameEvent.BLOCK_DESTROY.key())
                .add(GameEvent.PROJECTILE_SHOOT.key())
                .add(GameEvent.PROJECTILE_LAND.key())
                .add(GameEvent.EXPLODE.key());
    }
}
