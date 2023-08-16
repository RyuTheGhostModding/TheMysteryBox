package com.ryutheghost.themysterybox.block.registry;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.block.MysteryBoxBlock;
import com.ryutheghost.themysterybox.item.registry.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    // DeferredRegister for Block
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MainMod.MODID);

    // RegistryObject for the Mystery Box block
    public static final RegistryObject<Block> MYSTERY_BOX_BLOCK = registerBlock("mystery_box",
            () -> new MysteryBoxBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    // Helper method to register a block and its corresponding item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        // Register the block
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        // Register the block item
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Helper method to register a block item
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        // Register the item with the same name as the block
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Register the blocks with the event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
