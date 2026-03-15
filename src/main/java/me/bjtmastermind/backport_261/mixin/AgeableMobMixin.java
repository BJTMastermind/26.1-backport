package me.bjtmastermind.backport_261.mixin;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.bjtmastermind.backport_261.block.ModBlocks;
import me.bjtmastermind.backport_261.interfaces.AgeableMobAccess;
import me.bjtmastermind.backport_261.interfaces.EntityAccess;
import me.bjtmastermind.backport_261.interfaces.MobAccess;
import me.bjtmastermind.backport_261.particle.ModParticles;
import me.bjtmastermind.backport_261.sound_events.ModSoundEvents;
import me.bjtmastermind.backport_261.tags.ModTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

@Mixin(AgeableMob.class)
public abstract class AgeableMobMixin implements AgeableMobAccess {
    private static final EntityDataAccessor<Boolean> AGE_LOCKED = SynchedEntityData.defineId(AgeableMob.class, EntityDataSerializers.BOOLEAN);
    private static final int AGE_LOCK_COOLDOWN_TICKS = 40;
    private static final double AGE_LOCK_DOWNWARDS_MOVING_PARTICLE_Y_OFFSET = 0.2;
    protected int ageLockParticleTimer = 0;

    @Shadow public abstract boolean isBaby();
    @Shadow public abstract void setBaby(boolean isBaby);
    @Shadow public abstract int getAge();
    @Shadow public abstract void setAge(int age);

    protected InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (canUseGoldenDandelion(itemInHand, this.isBaby(), this.ageLockParticleTimer, (Mob)(Object)this)) {
            setAgeLocked((Mob)(Object)this, this::isAgeLocked, player, itemInHand, (mob) -> this.setAgeLockedData());
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    private static boolean canUseGoldenDandelion(final ItemStack itemInHand, final boolean isBaby, final int cooldown, final Mob mob) {
        return itemInHand.getItem() == ModBlocks.GOLDEN_DANDELION.asItem() && isBaby && cooldown == 0 && !((EntityAccess)mob).is(ModTags.CANNOT_BE_AGE_LOCKED);
    }

    private void setAgeLockedData() {
        this.setAgeLocked(!this.isAgeLocked());
        this.setAge(-24000);
        this.ageLockParticleTimer = AGE_LOCK_COOLDOWN_TICKS;
    }

    private static void setAgeLocked(final Mob mob, final Supplier<Boolean> isAgedLocked, final Player player, final ItemStack itemInHand, final Consumer<Mob> setAgeLockData) {
        setAgeLockData.accept(mob);
        itemInHand.consume(1, player);
        boolean isAgeLocked = (Boolean)isAgedLocked.get();
        ((MobAccess)mob).setPersistenceRequired(isAgeLocked);
        mob.level().playSound((Entity)null, mob.blockPosition(), isAgeLocked ? ModSoundEvents.GOLDEN_DANDELION_USE : ModSoundEvents.GOLDEN_DANDELION_UNUSE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    @Inject(
        method = "defineSynchedData",
        at = @At(
            value = "RETURN",
            target = "(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V"
        )
    )
    private void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(AGE_LOCKED, false);
    }

    public boolean canAgeUp() {
        return this.isBaby() && !this.isAgeLocked();
    }

    public void setAgeLocked(final boolean locked) {
        ((Entity)(Object)this).getEntityData().set(AGE_LOCKED, locked);
    }

    @Inject(method = "aiStep", at = @At("RETURN"))
    private void ageLockParticles(CallbackInfo ci) {
        this.ageLockParticleTimer = makeAgeLockedParticle(((AgeableMob)(Object)this).level(), ((Mob)(Object)this), this.ageLockParticleTimer, this.isAgeLocked());
    }

    private static int makeAgeLockedParticle(final Level level, final Mob mob, int ageLockParticleTimer, final boolean isAgeLocked) {
        if (ageLockParticleTimer > 0) {
            if (level.isClientSide() && ageLockParticleTimer % 2 == 0) {
                double yParticleOffset = isAgeLocked ? AGE_LOCK_DOWNWARDS_MOVING_PARTICLE_Y_OFFSET : 0.0;
                Vec3 spawnPosition = new Vec3(mob.getRandomX(1.0), ((EntityAccess)mob).getRandomY(0.2) + (double)mob.getBbHeight() + yParticleOffset, mob.getRandomZ(1.0));
                level.addParticle(isAgeLocked ? ModParticles.PAUSE_MOB_GROWTH : ModParticles.RESET_MOB_GROWTH, spawnPosition.x, spawnPosition.y, spawnPosition.z, 0.0, 0.0, 0.0);
            }
            --ageLockParticleTimer;
        }
        return ageLockParticleTimer;
    }

    public boolean isAgeLocked() {
        return ((Entity)(Object)this).getEntityData().get(AGE_LOCKED);
    }

    // Prevent age locked baby animals from aging
    @Inject(method = "setAge", at = @At("HEAD"), cancellable = true)
    private void stopBabyAging(int age, CallbackInfo ci) {
        if (this.isAgeLocked() && this.getAge() < 0 && age > this.getAge()) {
            this.setBaby(true);
            ci.cancel();
        }
    }

    // Add AgeLocked to entity's nbt
    @Inject(
        method = "addAdditionalSaveData",
        at = @At(
            value = "TAIL",
            target = "(Lnet/minecraft/world/level/storage/ValueOutput;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V"
        )
    )
    private void addAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        valueOutput.putBoolean("AgeLocked", this.isAgeLocked());
    }

    // Read AgeLocked from entity's nbt
    @Inject(
        method = "readAdditionalSaveData",
        at = @At(
            value = "TAIL",
            target = "(Lnet/minecraft/world/level/storage/ValueInput;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V"
        )
    )
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        this.setAgeLocked(valueInput.getBooleanOr("AgeLocked", false));
    }
}
