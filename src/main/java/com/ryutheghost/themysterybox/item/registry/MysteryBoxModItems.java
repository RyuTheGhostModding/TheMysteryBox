package com.ryutheghost.themysterybox.item.registry;

import com.ryutheghost.themysterybox.TheMysteryBoxMod;
import com.ryutheghost.themysterybox.item.QuestionMarkItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class MysteryBoxModItems {

    // Create a deferred register for items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Objects.requireNonNull(ForgeRegistries.ITEMS), TheMysteryBoxMod.MODID);

    // Define a registry object for the question mark item
    public static final RegistryObject<Item> QUESTION_MARK_ITEM = ITEMS.register("question_mark",
            () -> new QuestionMarkItem(new QuestionMarkItem.Properties()));

    // Register the deferred register with the event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
