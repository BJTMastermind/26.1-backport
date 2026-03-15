package me.bjtmastermind.backport_261.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.bjtmastermind.backport_261.interfaces.EntityAccess;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityAccess {
    @Shadow private RandomSource random;

    @Shadow public abstract double getY(double d);
    @Shadow public abstract EntityType<?> getType();

    @SuppressWarnings("deprecation")
    private Holder<EntityType<?>> typeHolder() {
        return this.getType().builtInRegistryHolder();
    }

    public boolean is(final TagKey<EntityType<?>> tag) {
        return this.typeHolder().is(tag);
    }

    public double getRandomY(final double spread) {
        return this.getY((2.0 * this.random.nextDouble() - 1.0) * spread);
    }
}
