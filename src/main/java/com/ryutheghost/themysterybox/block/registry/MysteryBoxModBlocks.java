package com.ryutheghost.themysterybox.block.registry;

import com.ryutheghost.themysterybox.TheMysteryBoxMod;
import com.ryutheghost.themysterybox.block.MysteryBoxBlock;
import com.ryutheghost.themysterybox.block.SuperLuckyMysteryBoxBlock;
import com.ryutheghost.themysterybox.block.SuperUnluckyMysteryBoxBlock;
import com.ryutheghost.themysterybox.item.registry.MysteryBoxModItems;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("UnusedReturnValue")
public class MysteryBoxModBlocks {

    // DeferredRegister for Block
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Objects.requireNonNull(ForgeRegistries.BLOCKS), TheMysteryBoxMod.MODID);

    // RegistryObject for the Mystery Box block
    public static final RegistryObject<Block> MYSTERY_BOX_BLOCK = registerNormalMysteryBox(
            () -> new MysteryBoxBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SUPER_LUCKY_MYSTERY_BOX_BLOCK = registerSuperLuckyMysteryBox(
            () -> new SuperLuckyMysteryBoxBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SUPER_UNLUCKY_MYSTERY_BOX_BLOCK = registerSuperUnLuckyMysteryBox(
            () -> new SuperUnluckyMysteryBoxBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    // Helper method to register a block and its corresponding item
    @SuppressWarnings("NullableProblems")
    private static <T extends Block> @NotNull @Nullable RegistryObject<T> registerNormalMysteryBox(Supplier<T> block) {
        // Register the block
        RegistryObject<T> toReturn = BLOCKS.register("mystery_box", block);
        // Register the block item
        registerBlockItem("mystery_box", Objects.requireNonNull(toReturn));
        return toReturn;
    }

    // Helper method to register a block and its corresponding item
    @SuppressWarnings("NullableProblems")
    private static <T extends Block> @NotNull @Nullable RegistryObject<T> registerSuperLuckyMysteryBox(Supplier<T> block) {
        // Register the block
        RegistryObject<T> toReturn = BLOCKS.register("super_lucky_mystery_box", block);
        // Register the block item
        registerBlockItem("super_lucky_mystery_box", Objects.requireNonNull(toReturn));
        return toReturn;
    }

    // Helper method to register a block and its corresponding item
    @SuppressWarnings("NullableProblems")
    private static <T extends Block> @NotNull @Nullable RegistryObject<T> registerSuperUnLuckyMysteryBox(Supplier<T> block) {
        // Register the block
        RegistryObject<T> toReturn = BLOCKS.register("super_unlucky_mystery_box", block);
        // Register the block item
        registerBlockItem("super_unlucky_mystery_box", Objects.requireNonNull(toReturn));
        return toReturn;
    }

    // Helper method to register a block item
    private static <T extends Block> @Nullable RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        // Register the item with the same name as the block
        return MysteryBoxModItems.ITEMS.register(name, () -> new BlockItem(Objects.requireNonNull(block).get(), new Item.Properties()));
    }

    // Register the blocks with the event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
