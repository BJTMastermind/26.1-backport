package me.bjtmastermind.backport_261.interfaces;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public interface EntityAccess {
    boolean is(final TagKey<EntityType<?>> tag);
    double getRandomY(final double spread);
}
