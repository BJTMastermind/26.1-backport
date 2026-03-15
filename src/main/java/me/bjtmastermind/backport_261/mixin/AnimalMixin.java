package me.bjtmastermind.backport_261.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import me.bjtmastermind.backport_261.interfaces.AgeableMobAccess;
import net.minecraft.world.entity.animal.Animal;

@Mixin(Animal.class)
public abstract class AnimalMixin {

    // Prevent age locked baby animals from being fed
    @Redirect(
        method = "mobInteract",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/animal/Animal;isBaby()Z"
        )
    )
    private boolean preventAgeLockedBabyFeeding(Animal mob) {
        return ((AgeableMobAccess)this).canAgeUp();
    }
}
