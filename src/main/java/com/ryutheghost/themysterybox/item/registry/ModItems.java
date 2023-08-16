package com.ryutheghost.themysterybox.item.registry;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.item.QuestionMarkItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MODID);

    public static final RegistryObject<Item> QUESTION_MARK_ITEM = ITEMS.register("question_mark",
            () -> new QuestionMarkItem(new QuestionMarkItem.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
