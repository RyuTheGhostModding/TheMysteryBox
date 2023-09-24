package com.ryutheghost.themysterybox.block.creativetab;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.block.registry.MysteryBoxModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModBlocksTab {

    // DeferredRegister for CreativeModeTab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainMod.MODID);

    // RegistryObject for the custom Mystery Box creative mode tab
    public static final RegistryObject<CreativeModeTab> MYSTERY_BOX_TAB = CREATIVE_MODE_TABS.register("the_box_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Objects.requireNonNull(MysteryBoxModBlocks.MYSTERY_BOX_BLOCK).get())) // Set the icon to the Mystery Box block
                    .title(Component.translatable("creativetab_the_box_tab")) // Set the translation key for the tab title
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Objects.requireNonNull(MysteryBoxModBlocks.MYSTERY_BOX_BLOCK).get()); // Display the Mystery Box block in the tab
                        pOutput.accept(Objects.requireNonNull(MysteryBoxModBlocks.SUPER_LUCKY_MYSTERY_BOX_BLOCK).get()); // Display the Super Lucky Mystery Box block in the tab
                        pOutput.accept(Objects.requireNonNull(MysteryBoxModBlocks.SUPER_UNLUCKY_MYSTERY_BOX_BLOCK).get()); // Display the Super Unlucky Mystery Box block in the tab
                    })
                    .build());

    // Register the creative mode tab with the event bus
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
