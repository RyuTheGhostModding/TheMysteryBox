package com.ryutheghost.themysterybox.block;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MysteryBoxBlock extends Block {
    public MysteryBoxBlock(Properties pProperties) {
        super(pProperties);
    }

    private boolean isBroken = false;

    private static final Random RANDOM = new Random();

    private static final Item[] ITEMS_LIST = new Item[]{
                    Items.ENCHANTED_GOLDEN_APPLE,
                    Items.GOLDEN_APPLE,
                    Items.APPLE,

                    Items.COOKED_MUTTON,
                    Items.COOKED_COD,
                    Items.COOKED_CHICKEN,
                    Items.COOKED_BEEF,
                    Items.COOKED_RABBIT,
                    Items.COOKED_PORKCHOP,
                    Items.COOKED_SALMON,

                    Items.CARVED_PUMPKIN,
                    Items.PUMPKIN_SEEDS,
                    Items.MELON_SEEDS,
                    Items.GLISTERING_MELON_SLICE,
                    Items.MELON,
                    Items.PUMPKIN,
                    Items.MELON_SLICE,

                    Items.GOLDEN_CARROT,
                    Items.CARROT,
                    Items.POISONOUS_POTATO,
                    Items.POTATO,
                    Items.BAKED_POTATO,
                    Items.BEETROOT,
                    Items.BEETROOT_SEEDS,
                    Items.WHEAT,
                    Items.WHEAT_SEEDS,

                    Items.BREAD,
                    Items.CAKE,
                    Items.PUMPKIN_PIE,

                    Items.DIAMOND_HELMET,
                    Items.DIAMOND_CHESTPLATE,
                    Items.DIAMOND_LEGGINGS,
                    Items.DIAMOND_BOOTS,
                    Items.GOLDEN_HELMET,
                    Items.GOLDEN_CHESTPLATE,
                    Items.GOLDEN_LEGGINGS,
                    Items.GOLDEN_BOOTS,
                    Items.NETHERITE_HELMET,
                    Items.NETHERITE_CHESTPLATE,
                    Items.NETHERITE_LEGGINGS,
                    Items.NETHERITE_BOOTS,
                    Items.LEATHER_HELMET,
                    Items.LEATHER_CHESTPLATE,
                    Items.LEATHER_LEGGINGS,
                    Items.LEATHER_BOOTS,

                    Items.DIAMOND_HOE,
                    Items.GOLDEN_HOE,
                    Items.IRON_HOE,
                    Items.STONE_HOE,
                    Items.WOODEN_HOE,
                    Items.NETHERITE_HOE,
                    Items.DIAMOND_PICKAXE,
                    Items.GOLDEN_PICKAXE,
                    Items.IRON_PICKAXE,
                    Items.STONE_PICKAXE,
                    Items.WOODEN_PICKAXE,
                    Items.NETHERITE_PICKAXE,
                    Items.DIAMOND_AXE,
                    Items.GOLDEN_AXE,
                    Items.IRON_AXE,
                    Items.STONE_AXE,
                    Items.WOODEN_AXE,
                    Items.NETHERITE_AXE,
                    Items.DIAMOND_SHOVEL,
                    Items.GOLDEN_SHOVEL,
                    Items.IRON_SHOVEL,
                    Items.STONE_SHOVEL,
                    Items.WOODEN_SHOVEL,
                    Items.NETHERITE_SHOVEL,
                    Items.DIAMOND_SWORD,
                    Items.GOLDEN_SWORD,
                    Items.IRON_SWORD,
                    Items.STONE_SWORD,
                    Items.WOODEN_SWORD,
                    Items.NETHERITE_SWORD,

                    Items.DIAMOND,
                    Items.GOLD_INGOT,
                    Items.IRON_INGOT,
                    Items.GOLD_NUGGET,
                    Items.IRON_NUGGET,
                    Items.AMETHYST_SHARD,
                    Items.EMERALD,
                    Items.LAPIS_LAZULI,
                    Items.EMERALD_BLOCK,
                    Items.LAPIS_BLOCK,
                    Items.GOLD_BLOCK,
                    Items.IRON_BLOCK,
                    Items.AMETHYST_BLOCK,
                    Items.DIAMOND_BLOCK,
    };
    private static final List<String> good_translation_keys_messages = Arrays.asList("goodluck.message.one",
            "goodluck.message.two",
            "goodluck.message.three",
            "goodluck.message.four",
            "goodluck.message.five");

    private static final List<String> bad_translation_keys_messages = Arrays.asList("badluck.message.one",
            "badluck.message.two",
            "badluck.message.three",
            "badluck.message.four",
            "badluck.message.five");

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if(Screen.hasShiftDown()){
            pTooltip.add(Component.translatable("mysterybox_shift_key_down"));
        }else{
            pTooltip.add(Component.translatable("mysterybox_shift_key_not_down"));
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    private boolean ismessageSent = false;

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!isBroken) {
            // The block is being broken for the first time

            Item[] item = {ITEMS_LIST[RANDOM.nextInt(ITEMS_LIST.length)]};
            for(Item tobeadded: item) {
                player.getInventory().add(new ItemStack(tobeadded.getDefaultInstance().getItem()));
            }
            if(!ismessageSent){
                    int index = new Random().nextInt(good_translation_keys_messages.size());
                    player.sendSystemMessage(Component.translatable(good_translation_keys_messages.get(index)));
                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    player.playSound(SoundEvents.CHEST_OPEN, 1f, 1f);
                    ismessageSent = true;
            }else{
                ismessageSent = false;
            }
            isBroken = true;

        }

            // ...

            // Reset isBroken to false
            isBroken = false;

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
