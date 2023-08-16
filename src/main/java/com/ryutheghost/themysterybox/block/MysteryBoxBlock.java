package com.ryutheghost.themysterybox.block;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
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

import com.tiviacz.travelersbackpack.init.ModItems;

public class MysteryBoxBlock extends Block {
    public MysteryBoxBlock(Properties pProperties) {
        super(pProperties);
    }

    private boolean isBroken = false;

    private static final Random RANDOM = new Random();

    // Array of items that can be obtained from the Mystery Box block
    private static final Item[] ITEMS_LIST = new Item[]{
                    // List of items...
                    // This array contains items like golden apples, cooked meats, seeds, tools, and resources
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
                    Items.DRAGON_EGG,
                    Items.DRAGON_HEAD,
                    Items.SHEARS,
                    Items.SHIELD,
                    Items.FISHING_ROD,
                    Items.FLINT_AND_STEEL,
                    Items.FIRE_CHARGE,
                    Items.FLINT,
                    Items.WOLF_SPAWN_EGG,
                    Items.BONE,
                    Items.BONE_MEAL,
                    Items.CAT_SPAWN_EGG,
                    Items.TROPICAL_FISH,
                    Items.PUFFERFISH,
                    Items.INK_SAC,
                    Items.GLOW_INK_SAC,
                    Items.GLOWSTONE_DUST,
                    Items.GLOWSTONE,
                    Items.REDSTONE,
                    Items.NETHER_STAR,
                    Items.EXPERIENCE_BOTTLE,
                    Items.HONEY_BOTTLE,
                    Items.HONEYCOMB,
                    Items.ENCHANTED_BOOK,
                    Items.ENCHANTING_TABLE,
                    Items.END_CRYSTAL,
                    Items.ENDER_CHEST,
                    Items.ENDER_EYE,
                    Items.ENDER_PEARL,
                    Items.CHEST,
                    Items.FURNACE
    };

    private static final EntityType<?>[] MONSTERS = new EntityType<?>[]{
      EntityType.CAVE_SPIDER,
      EntityType.SPIDER,
      EntityType.WITHER,
      EntityType.WARDEN,
      EntityType.CREEPER,
      EntityType.ZOMBIE,
      EntityType.SKELETON,
      EntityType.WITHER_SKELETON,
      EntityType.BLAZE,
      EntityType.GHAST
    };


    private static final List<String> good_translation_keys_messages = Arrays.asList(
            "goodluck.message.one",   // Translation key for a good luck message
            "goodluck.message.two",   // Translation key for a good luck message
            "goodluck.message.three", // Translation key for a good luck message
            "goodluck.message.four",  // Translation key for a good luck message
            "goodluck.message.five"   // Translation key for a good luck message
    );

    private static final List<String> bad_translation_keys_messages = Arrays.asList(
            "badluck.message.one",    // Translation key for a bad luck message
            "badluck.message.two",    // Translation key for a bad luck message
            "badluck.message.three",  // Translation key for a bad luck message
            "badluck.message.four",   // Translation key for a bad luck message
            "badluck.message.five"    // Translation key for a bad luck message
    );

    /**
     * This method appends hover text to the item stack's tooltip.
     * If the player is holding shift, it adds a translation component indicating that the shift key is down.
     * Otherwise, it adds a translation component indicating that the shift key is not down.
     * It then calls the superclass's appendHoverText method.
     *
     * @param pStack   The item stack
     * @param pLevel   The block getter
     * @param pTooltip The tooltip list
     * @param pFlag    The tooltip flag
     */
    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (Screen.hasShiftDown()) {
            pTooltip.add(Component.translatable("mysterybox_shift_key_down"));     // Add translation component indicating shift key is down
        } else {
            pTooltip.add(Component.translatable("mysterybox_shift_key_not_down")); // Add translation component indicating shift key is not down
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag); // Call the superclass's appendHoverText method
    }

    private boolean isgoodluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not

    private boolean isbackluckmessageSent = false;
    private boolean hasGivenItem = false; // Boolean field to check if it is a vanilla item
    private boolean hasGivenBackpack = false; // Boolean field to check if it is a backpack

    private boolean hasSpawnedMonster = false; // Boolean field to check if it is a monster
    private boolean isGoodLuck = false; // Boolean field to check if it is good luck
    private boolean isBadLuck = false; // Boolean field to check if it is bad luck

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!isBroken) {
            // The block is being broken for the first time
            if(!isGoodLuck){
                //Check is Good Luck
                // Play sounds to indicate the successful opening of the mystery box
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                player.playSound(SoundEvents.CHEST_OPEN, 1f, 1f);
                if (!hasGivenItem) {
                    // Give the player an item from the ITEMS_LIST
                    Item[] item = {ITEMS_LIST[RANDOM.nextInt(ITEMS_LIST.length)]};
                    for (Item tobeadded : item) {
                        if(!hasGivenItem){
                            player.getInventory().add(new ItemStack(tobeadded.getDefaultInstance().getItem()));
                        }
                    }
                    if (!isgoodluckmessageSent) {
                        // Generate a random index to get a random translation key for a good luck message
                        int index = new Random().nextInt(good_translation_keys_messages.size());

                        // Send the good luck message to the player
                        player.sendSystemMessage(Component.translatable(good_translation_keys_messages.get(index)));

                        // Set ismessageSent to true to prevent sending duplicate messages
                        isgoodluckmessageSent = true;
                    }
                    hasGivenItem = true;
                    // Set isBroken to true to indicate that the block has been broken
                    isBroken = true;
                } else if (!hasGivenBackpack) {
                    // Give the player a backpack
                    Item[] backpack = {ModItems.BACKPACKS.get(RANDOM.nextInt(ModItems.BACKPACKS.toArray().length))};
                    for (Item tobeadded : backpack) {
                        if(!hasGivenBackpack){
                            player.getInventory().add(new ItemStack(tobeadded.getDefaultInstance().getItem()));
                        }
                    }
                    if (!isgoodluckmessageSent) {
                        // Generate a random index to get a random translation key for a good luck message
                        int index = new Random().nextInt(good_translation_keys_messages.size());

                        // Send the good luck message to the player
                        player.sendSystemMessage(Component.translatable(good_translation_keys_messages.get(index)));

                        // Set ismessageSent to true to prevent sending duplicate messages
                        isgoodluckmessageSent = true;
                    }

                    hasGivenBackpack = true;
                    // Set isBroken to true to indicate that the block has been broken
                    isBroken = true;
                } else {
                    // Reset the block
                    hasGivenItem = false;
                    hasGivenBackpack = false;
                    isgoodluckmessageSent = false;
                }

                isGoodLuck = true;
            }else if(!isBadLuck){
                // Check is Bad Luck
                // Play sounds to indicate the successful opening of the mystery box
                player.playSound(SoundEvents.WITHER_AMBIENT, 1f, 1f);
                player.playSound(SoundEvents.CHEST_OPEN, 1f, 1f);
                if(!hasSpawnedMonster){
                    // Select the player and spawn a monster from the MONSTERS list
                    EntityType<?>[] monster = {MONSTERS[RANDOM.nextInt(MONSTERS.length)]};
                    for(EntityType<?> type: monster){
                        if(!hasSpawnedMonster){
                            level.addFreshEntity(type.spawn((ServerLevel) level, pos, MobSpawnType.SPAWNER));
                        }
                    }
                        if(!isbackluckmessageSent) {
                            // Generate a random index to get a random translation key for a good luck message
                            int index = new Random().nextInt(bad_translation_keys_messages.size());
                            // Send the good luck message to the player
                            player.sendSystemMessage(Component.translatable(bad_translation_keys_messages.get(index)));
                            isbackluckmessageSent = true;
                        }


                    hasSpawnedMonster = true;
                    // Set isBroken to true to indicate that the block has been broken
                    isBroken = true;
                }else {
                    // Reset the block
                    hasSpawnedMonster = false;
                    isbackluckmessageSent = false;
                }
                isBadLuck = true;
            } else{
                // Reset the block
                isGoodLuck = false;
                isBadLuck = false;
            }
        }

        // ...

        // Reset isBroken to false
        isBroken = false;

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
