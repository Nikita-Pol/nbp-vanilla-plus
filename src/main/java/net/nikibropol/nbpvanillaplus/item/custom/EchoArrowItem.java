package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nikibropol.nbpvanillaplus.entity.ModEntities;
import net.nikibropol.nbpvanillaplus.entity.custom.EchoArrow;
import org.jetbrains.annotations.Nullable;

public class EchoArrowItem extends ArrowItem {
    public EchoArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        EchoArrow arrow = new EchoArrow(ModEntities.ECHO_ARROW, level);
        arrow.setOwner(shooter);
        arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        return arrow;
    }
}
