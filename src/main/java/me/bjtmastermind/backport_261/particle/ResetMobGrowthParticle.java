package me.bjtmastermind.backport_261.particle;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class ResetMobGrowthParticle extends SingleQuadParticle {

    public ResetMobGrowthParticle(
        ClientLevel clientLevel,
        double x, double y, double z,
        double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet)
    {
        super(clientLevel, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.first());
        this.friction = 0.0f;
        // this.x = (2.0 * this.random.nextDouble() - 1.0) * 1.0;
        // this.y = (2.0 * this.random.nextDouble() - 1.0) * 0.0;
        // this.z = (2.0 * this.random.nextDouble() - 1.0) * 1.0;
        this.lifetime = 40;
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(
            SimpleParticleType parameters, ClientLevel clientLevel,
            double x, double y, double z,
            double velocityX, double velocityY, double velocityZ, RandomSource randomSource)
        {
            return new ResetMobGrowthParticle(clientLevel, x, y, z, velocityX, velocityY, velocityZ, this.spriteSet);
        }
    }
}
