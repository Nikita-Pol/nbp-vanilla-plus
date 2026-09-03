package net.nikibropol.nbpvanillaplus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class EchoMagmaBlock extends Block {
    public EchoMagmaBlock(Properties properties) {
        super(properties);
    }

        public void stepOn(Level level, BlockPos pos,BlockState onState,Entity entity) {
        if (!entity.isSteppingCarefully()) {
            if (entity instanceof Player player) {
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                if (boots.isEmpty() || !boots.is(ModItems.ECHO_BOOTS)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0, true, true));
                    player.hurt(level.damageSources().hotFloor(), 0.5F);
                }
            } else if (entity instanceof LivingEntity) {
                entity.hurt(level.damageSources().hotFloor(), 0.5F);
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

}
