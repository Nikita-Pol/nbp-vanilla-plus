package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.data.ModDataComponents;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import net.nikibropol.nbpvanillaplus.stat.ModStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerSwingMixin {
    private long lastAwardTick = -1;

    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;)V", at = @At("HEAD"))
    private void onSwing(InteractionHand hand, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (!(entity instanceof Player player)) return;
        if (player.level().isClientSide()) return;

        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(ModItems.EXTENDER) && !stack.is(ModItems.EXTENDER_AMETHYST) && !stack.is(ModItems.EXTENDER_ECHO))
            return;

        long currentTick = player.level().getGameTime();
        if (currentTick - lastAwardTick < 2) return;
        lastAwardTick = currentTick;

        player.awardStat(ModStats.CLICKED_WITH_EXTENDER, 1);
    }
}