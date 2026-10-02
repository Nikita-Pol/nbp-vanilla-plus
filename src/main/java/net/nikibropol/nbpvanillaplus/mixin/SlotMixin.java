package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.nikibropol.nbpvanillaplus.block.custom.ReinforcedShulkerBoxBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Shadow @Final public Container container;

    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void nbpVanillaPlus$blockShulkerNesting(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (container instanceof ShulkerBoxBlockEntity) {
            Block block = Block.byItem(stack.getItem());
            if (block instanceof ShulkerBoxBlock || block instanceof ReinforcedShulkerBoxBlock) {
                cir.setReturnValue(false);
            }
        }
    }
}