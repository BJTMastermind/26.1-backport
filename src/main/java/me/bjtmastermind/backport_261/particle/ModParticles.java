package me.bjtmastermind.backport_261.particle;

import me.bjtmastermind.backport_261.Backport261;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {
    public static final SimpleParticleType PAUSE_MOB_GROWTH = registerParticle("pause_mob_growth", FabricParticleTypes.simple());
    public static final SimpleParticleType RESET_MOB_GROWTH = registerParticle("reset_mob_growth", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name), particleType);
    }

    public static void registerModParticles() {
        Backport261.LOGGER.info("Registering Mod Particles for " + Backport261.MOD_ID);
    }
}
