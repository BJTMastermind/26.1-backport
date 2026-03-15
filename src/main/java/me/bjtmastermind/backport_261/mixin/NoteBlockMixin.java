package me.bjtmastermind.backport_261.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.bjtmastermind.backport_261.instrument.ModNoteBlockInstruments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

@Mixin(NoteBlock.class)
public abstract class NoteBlockMixin {
    @Shadow @Final public static EnumProperty<NoteBlockInstrument> INSTRUMENT;

    @Inject(method = "setInstrument", at = @At("HEAD"), cancellable = true)
    private void checkModInstruments(LevelReader levelReader, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = levelReader.getBlockState(blockPos.below());
        if (isCopperBlock(state)) {
            cir.setReturnValue(blockState.setValue(INSTRUMENT, ModNoteBlockInstruments.TRUMPET));
        } else if (isExposedCopperBlock(state)) {
            cir.setReturnValue(blockState.setValue(INSTRUMENT, ModNoteBlockInstruments.TRUMPET_EXPOSED));
        } else if (isWeatheredCopperBlock(state)) {
            cir.setReturnValue(blockState.setValue(INSTRUMENT, ModNoteBlockInstruments.TRUMPET_WEATHERED));
        } else if (isOxidizedCopperBlock(state)) {
            cir.setReturnValue(blockState.setValue(INSTRUMENT, ModNoteBlockInstruments.TRUMPET_OXIDIZED));
        }
    }

    private boolean isCopperBlock(BlockState state) {
        return (
            state.is(Blocks.COPPER_BLOCK) ||
            state.is(Blocks.CHISELED_COPPER) ||
            state.is(Blocks.CUT_COPPER) ||
            state.is(Blocks.CUT_COPPER_STAIRS) ||
            state.is(Blocks.CUT_COPPER_SLAB) ||
            state.is(Blocks.WAXED_COPPER_BLOCK) ||
            state.is(Blocks.WAXED_CHISELED_COPPER) ||
            state.is(Blocks.WAXED_CUT_COPPER) ||
            state.is(Blocks.WAXED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.WAXED_CUT_COPPER_SLAB)
        );
    }

    private boolean isExposedCopperBlock(BlockState state) {
        return (
            state.is(Blocks.EXPOSED_COPPER) ||
            state.is(Blocks.EXPOSED_CHISELED_COPPER) ||
            state.is(Blocks.EXPOSED_CUT_COPPER) ||
            state.is(Blocks.EXPOSED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.EXPOSED_CUT_COPPER_SLAB) ||
            state.is(Blocks.WAXED_EXPOSED_COPPER) ||
            state.is(Blocks.WAXED_EXPOSED_CHISELED_COPPER) ||
            state.is(Blocks.WAXED_EXPOSED_CUT_COPPER) ||
            state.is(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)
        );
    }

    private boolean isWeatheredCopperBlock(BlockState state) {
        return (
            state.is(Blocks.WEATHERED_COPPER) ||
            state.is(Blocks.WEATHERED_CHISELED_COPPER) ||
            state.is(Blocks.WEATHERED_CUT_COPPER) ||
            state.is(Blocks.WEATHERED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.WEATHERED_CUT_COPPER_SLAB) ||
            state.is(Blocks.WAXED_WEATHERED_COPPER) ||
            state.is(Blocks.WAXED_WEATHERED_CHISELED_COPPER) ||
            state.is(Blocks.WAXED_WEATHERED_CUT_COPPER) ||
            state.is(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)
        );
    }

    private boolean isOxidizedCopperBlock(BlockState state) {
        return (
            state.is(Blocks.OXIDIZED_COPPER) ||
            state.is(Blocks.OXIDIZED_CHISELED_COPPER) ||
            state.is(Blocks.OXIDIZED_CUT_COPPER) ||
            state.is(Blocks.OXIDIZED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.OXIDIZED_CUT_COPPER_SLAB) ||
            state.is(Blocks.WAXED_OXIDIZED_COPPER) ||
            state.is(Blocks.WAXED_OXIDIZED_CHISELED_COPPER) ||
            state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER) ||
            state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS) ||
            state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)
        );
    }
}
