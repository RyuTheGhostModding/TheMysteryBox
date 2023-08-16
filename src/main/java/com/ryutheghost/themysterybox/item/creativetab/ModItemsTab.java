package com.ryutheghost.themysterybox.item.creativetab;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.item.registry.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainMod.MODID);

    public static final RegistryObject<CreativeModeTab> MYSTERY_ITEMS_TAB = CREATIVE_MODE_TABS.register("unknown_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.QUESTION_MARK_ITEM.get()))
                    .title(Component.translatable("creativetab_unknown_items_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.QUESTION_MARK_ITEM.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
