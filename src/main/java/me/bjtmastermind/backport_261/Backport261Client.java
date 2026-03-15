package me.bjtmastermind.backport_261;

import me.bjtmastermind.backport_261.block.ModBlocks;
import me.bjtmastermind.backport_261.particle.ModParticles;
import me.bjtmastermind.backport_261.particle.PauseMobGrowthParticle;
import me.bjtmastermind.backport_261.particle.ResetMobGrowthParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class Backport261Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.GOLDEN_DANDELION, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_GOLDEN_DANDELION, ChunkSectionLayer.CUTOUT);

        ParticleFactoryRegistry.getInstance().register(ModParticles.PAUSE_MOB_GROWTH, PauseMobGrowthParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.RESET_MOB_GROWTH, ResetMobGrowthParticle.Factory::new);
    }
}
