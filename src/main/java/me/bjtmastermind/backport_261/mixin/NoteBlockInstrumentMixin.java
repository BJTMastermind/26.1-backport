package me.bjtmastermind.backport_261.mixin;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.bjtmastermind.backport_261.instrument.ModNoteBlockInstruments;
import me.bjtmastermind.backport_261.sound_events.ModSoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

@Mixin(NoteBlockInstrument.class)
public abstract class NoteBlockInstrumentMixin {
    @Shadow @Final @Mutable private static NoteBlockInstrument[] $VALUES;

    @Invoker("<init>")
    private static NoteBlockInstrument create(String enumName, int ordinal, String name, Holder<SoundEvent> soundEvent, NoteBlockInstrument.Type type) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addEnum(CallbackInfo ci) {
        NoteBlockInstrument trumpet = create("TRUMPET", $VALUES.length, "trumpet", ModSoundEvents.NOTE_BLOCK_TRUMPET, NoteBlockInstrument.Type.BASE_BLOCK);
        add(trumpet);
        ModNoteBlockInstruments.TRUMPET = trumpet;

        NoteBlockInstrument trumpetExposed = create("TRUMPET_EXPOSED", $VALUES.length, "trumpet_exposed", ModSoundEvents.NOTE_BLOCK_TRUMPET_EXPOSED, NoteBlockInstrument.Type.BASE_BLOCK);
        add(trumpetExposed);
        ModNoteBlockInstruments.TRUMPET_EXPOSED = trumpetExposed;

        NoteBlockInstrument trumpetWeathered = create("TRUMPET_WEATHERED", $VALUES.length, "trumpet_weathered", ModSoundEvents.NOTE_BLOCK_TRUMPET_WEATHERED, NoteBlockInstrument.Type.BASE_BLOCK);
        add(trumpetWeathered);
        ModNoteBlockInstruments.TRUMPET_WEATHERED = trumpetWeathered;

        NoteBlockInstrument trumpetOxidized = create("TRUMPET_OXIDIZED", $VALUES.length, "trumpet_oxidized", ModSoundEvents.NOTE_BLOCK_TRUMPET_OXIDIZED, NoteBlockInstrument.Type.BASE_BLOCK);
        add(trumpetOxidized);
        ModNoteBlockInstruments.TRUMPET_OXIDIZED = trumpetOxidized;
    }

    private static NoteBlockInstrument add(NoteBlockInstrument instrument) {
        NoteBlockInstrument[] newValues = Arrays.copyOf($VALUES, $VALUES.length + 1);
        newValues[newValues.length - 1] = instrument;
        $VALUES = newValues;
        return instrument;
    }
}
