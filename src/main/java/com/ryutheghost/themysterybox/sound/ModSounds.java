package com.ryutheghost.themysterybox.sound;

import com.ryutheghost.themysterybox.TheMysteryBoxMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheMysteryBoxMod.MODID);

    public static final RegistryObject<SoundEvent> MYSTERY_BOX_GOOD_LUCK = registerSoundEvents("mystery_box_good_luck");

    public static final RegistryObject<SoundEvent> MYSTERY_BOX_BAD_LUCK = registerSoundEvents("mystery_box_bad_luck");

    public static final RegistryObject<SoundEvent> NUKE_ALERT = registerSoundEvents("nuke_alert");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(TheMysteryBoxMod.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
