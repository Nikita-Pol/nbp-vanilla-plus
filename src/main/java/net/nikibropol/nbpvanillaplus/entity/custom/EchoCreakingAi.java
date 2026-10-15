package net.nikibropol.nbpvanillaplus.entity.custom;

import com.google.common.collect.ImmutableList;
import java.util.List;

import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.schedule.Activity;

public final class EchoCreakingAi {
    private EchoCreakingAi() {}

    public static List<ActivityData<Creaking>> getActivities(final Creaking creaking) {
        return List.of(
                ActivityData.create(Activity.CORE, 0, ImmutableList.of(
                        new Swim<>(0.8F), new LookAtTargetSink(45, 90), new MoveToTargetSink())),
                ActivityData.create(Activity.IDLE, 10, ImmutableList.of()),
                ActivityData.create(Activity.FIGHT, 10, ImmutableList.of())
        );
    }
}
