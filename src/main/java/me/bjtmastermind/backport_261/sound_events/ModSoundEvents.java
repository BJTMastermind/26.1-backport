package me.bjtmastermind.backport_261.sound_events;

import me.bjtmastermind.backport_261.Backport261;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static final SoundEvent GOLDEN_DANDELION_USE = registerSound("item.golden_dandelion.use");
    public static final SoundEvent GOLDEN_DANDELION_UNUSE = registerSound("item.golden_dandelion.unuse");
    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_TRUMPET = registerSoundWithHolder("block.note_block.trumpet");
    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_TRUMPET_EXPOSED = registerSoundWithHolder("block.note_block.trumpet_exposed");
    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_TRUMPET_WEATHERED = registerSoundWithHolder("block.note_block.trumpet_weathered");
    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_TRUMPET_OXIDIZED = registerSoundWithHolder("block.note_block.trumpet_oxidized");

    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static Holder.Reference<SoundEvent> registerSoundWithHolder(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerModSoundEvents() {
        Backport261.LOGGER.info("Registering Mod Sound Events for " + Backport261.MOD_ID);
    }
}
