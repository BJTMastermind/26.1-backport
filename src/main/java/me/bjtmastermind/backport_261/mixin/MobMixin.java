package me.bjtmastermind.backport_261.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.bjtmastermind.backport_261.interfaces.MobAccess;
import net.minecraft.world.entity.Mob;

@Mixin(Mob.class)
public abstract class MobMixin implements MobAccess {
    @Shadow
    private boolean persistenceRequired;

    public void setPersistenceRequired(boolean persistenceRequired) {
        this.persistenceRequired = persistenceRequired;
    }
}
