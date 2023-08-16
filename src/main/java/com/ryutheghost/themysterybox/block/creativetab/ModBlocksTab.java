package com.ryutheghost.themysterybox.block.creativetab;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.block.registry.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainMod.MODID);

    public static final RegistryObject<CreativeModeTab> MYSTERY_BOX_TAB = CREATIVE_MODE_TABS.register("the_box_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MYSTERY_BOX_BLOCK.get()))
                    .title(Component.translatable("creativetab_the_box_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.MYSTERY_BOX_BLOCK.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
