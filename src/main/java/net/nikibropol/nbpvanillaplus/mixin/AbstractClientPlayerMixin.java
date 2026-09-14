package net.nikibropol.nbpvanillaplus.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    @Shadow
    private @Nullable PlayerInfo playerInfo;

    public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);

    }
    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
    public void getFieldOfViewModifier(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> info) {
        float modifier = 1.0F;

        if (this.isUsingItem()) {
            if (this.getUseItem().is(ModItems.ECHO_BOW)) {
                float scale = Math.min(this.getTicksUsingItem() / 20.0F, 1.0F);
                modifier *= 1.0F - Mth.square(scale) * 0.15F;
                info.setReturnValue(Mth.lerp(effectScale, 1.0F, modifier));
            }
        }
    }

}
