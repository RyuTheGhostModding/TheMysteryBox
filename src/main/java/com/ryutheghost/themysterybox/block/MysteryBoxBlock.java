package com.ryutheghost.themysterybox.block;

import com.progwml6.ironchest.common.block.IronChestsBlocks;
import com.progwml6.ironchest.common.block.regular.AbstractIronChestBlock;
import com.tiviacz.travelersbackpack.init.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("ALL")
public class MysteryBoxBlock extends Block {
    public MysteryBoxBlock(Properties pProperties) {
        super(Objects.requireNonNull(pProperties));
    }

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
            Items.FURNACE,
            Items.ACACIA_BOAT,
            Items.ACACIA_BUTTON,
            Items.ACACIA_DOOR,
            Items.ACACIA_FENCE,
            Items.ACACIA_CHEST_BOAT,
            Items.ACACIA_FENCE_GATE,
            Items.ACACIA_HANGING_SIGN,
            Items.ACACIA_LEAVES,
            Items.ACACIA_LOG,
            Items.ACACIA_PLANKS,
            Items.ACACIA_PRESSURE_PLATE,
            Items.ACACIA_SAPLING,
            Items.ACACIA_SIGN,
            Items.ACACIA_SLAB,
            Items.ACACIA_STAIRS,
            Items.ACACIA_TRAPDOOR,
            Items.ACACIA_WOOD,
            Items.STRIPPED_ACACIA_LOG,
            Items.STRIPPED_ACACIA_WOOD,
            Items.CHEST_MINECART,
            Items.TRAPPED_CHEST,
            Items.TRIPWIRE_HOOK,
            Items.OBSERVER,
            Items.OBSIDIAN,
            Items.STRING,
            Items.TNT,
            Items.TNT_MINECART,
            Items.RAIL,
            Items.ACTIVATOR_RAIL,
            Items.DETECTOR_RAIL,
            Items.POWERED_RAIL,
            Items.NAME_TAG,
            Items.FURNACE_MINECART,
            Items.BLAST_FURNACE,
            Items.GRINDSTONE,
            Items.CAMPFIRE,
            Items.SOUL_CAMPFIRE,
            Items.LANTERN,
            Items.JACK_O_LANTERN,
            Items.PEARLESCENT_FROGLIGHT,
            Items.OCHRE_FROGLIGHT,
            Items.VERDANT_FROGLIGHT,
            Items.SOUL_LANTERN,
            Items.SEA_LANTERN


    };

    private static final float[] explosion_radius = new float[]{
            5.0f,
            10.0f,
            15.0f,
            20.0f,
            25.0f,
            30.0f,
            35.0f,
            40.0f,
            45.0f,
            50.0f

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

    private static final EntityType<?>[] MONSTERS = new EntityType<?>[]{
            EntityType.ZOMBIE,
            EntityType.SKELETON,
            EntityType.WITHER_SKELETON,
            EntityType.WITCH,
            EntityType.BLAZE,
            EntityType.CREEPER,
            EntityType.GHAST,
            EntityType.MAGMA_CUBE,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER,
            EntityType.WITHER,
            EntityType.WARDEN,
            EntityType.SLIME,
            EntityType.DROWNED,
            EntityType.HUSK
    };

    private static final EntityType<?>[] PETS = new EntityType<?>[]{
            EntityType.WOLF,
            EntityType.CAT,
            EntityType.PARROT,
            EntityType.LLAMA,
            EntityType.HORSE,
            EntityType.DONKEY,
            EntityType.MULE
    };

    private static final MobEffect[] BAD_EFFECTS = new MobEffect[]{
            MobEffects.WITHER,
            MobEffects.WEAKNESS,
            MobEffects.CONFUSION,
            MobEffects.POISON,
            MobEffects.DIG_SLOWDOWN,
            MobEffects.MOVEMENT_SLOWDOWN,
            MobEffects.HUNGER,
            MobEffects.BLINDNESS,
            MobEffects.UNLUCK,
            MobEffects.BAD_OMEN
    };

    private static final MobEffect[] GOOD_EFFECTS = new MobEffect[]{
            MobEffects.DAMAGE_BOOST,
            MobEffects.MOVEMENT_SPEED,
            MobEffects.JUMP,
            MobEffects.DIG_SPEED,
            MobEffects.ABSORPTION,
            MobEffects.DAMAGE_RESISTANCE,
            MobEffects.FIRE_RESISTANCE,
            MobEffects.GLOWING,
            MobEffects.HEALTH_BOOST,
            MobEffects.INVISIBILITY,
            MobEffects.LUCK,
            MobEffects.NIGHT_VISION,
            MobEffects.REGENERATION,
            MobEffects.SATURATION,
            MobEffects.SLOW_FALLING,
            MobEffects.WATER_BREATHING
    };

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
    public void appendHoverText(@NotNull ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        if (Screen.hasShiftDown()) {
            pTooltip.add(Component.translatable("mysterybox_shift_key_down"));     // Add translation component indicating shift key is down
        } else {
            pTooltip.add(Component.translatable("mysterybox_shift_key_not_down")); // Add translation component indicating shift key is not down
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag); // Call the superclass's appendHoverText method
    }

    private boolean isgoodluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not
    private boolean isbadluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not
    private boolean hasGivenItem = false; // Boolean field to check if it is a vanilla item
    private boolean hasGivenBackpack = false; // Boolean field to check if it is a backpack
    private boolean hasGivenIronChest = false; // Boolean field to check if it is an iron chest
    private boolean hasSpawnedExplosion = false; // Boolean field to check if it is a explosion
    private boolean hasSpawnedMonster = false; // Boolean field to check if it is a monster
    private boolean hasSpawnedPet = false; // Boolean field to check if it is a pet
    private boolean hasGivenBadEffect = false; // Boolean field to check if it is a bad potion effect
    private boolean hasGivenGoodEffect = false; // Boolean field to check if it is a good potion effect
    private boolean isGoodLuck = true; // Boolean field to check if it is good luck
    private boolean isBadLuck = false; // Boolean field to check if it is bad luck
    private boolean isDirtChest = true; // Boolean field to check if it is dirt
    private boolean isIronChest = false; // Boolean field to check if it is iron
    private boolean isGoldChest = false; // Boolean field to check if it is gold
    private boolean isCopperChest = false; // Boolean field to check if it is copper
    private boolean isDiamondChest = false; // Boolean field to check if it is diamond
    private boolean isObsidianChest = false; // Boolean field to check if it is obsidian
    private boolean isCrystalChest = false; // Boolean field to check if it is crystal
    private boolean isTrappedDirtChest = false; // Boolean field to check if it is dirt
    private boolean isTrappedIronChest = false; // Boolean field to check if it is iron
    private boolean isTrappedGoldChest = false; // Boolean field to check if it is gold
    private boolean isTrappedCopperChest = false; // Boolean field to check if it is copper
    private boolean isTrappedDiamondChest = false; // Boolean field to check if it is diamond
    private boolean isTrappedObsidianChest = false; // Boolean field to check if it is obsidian
    private boolean isTrappedCrystalChest = false; // Boolean field to check if it is crystal
    private boolean isBroken = false; // Boolean field to check if it is broken
    private boolean isItem = true; // Boolean field to check if it is a vanilla item
    private boolean isBackpack = false; // Boolean field to check if it is a backpack
    private boolean isIronChestsChest = false; // Boolean field to check if it is an iron chests chest
    private boolean isExplosion = true; // Boolean field to check if it is a explosion
    private boolean isMobSpawn = false; // Boolean field to check if it is a monster
    private boolean isPetSpawn = false; // Boolean field to check if it is a pet
    private boolean isBadEffect = false; // Boolean field to check if it is a bad effect
    private boolean isGoodEffect = false; // Boolean field to check if it is a good effect
    private boolean isLuck = false; // Boolean field to check if it is luck

    @Override
    public boolean onDestroyedByPlayer(BlockState state, @NotNull Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        // Play the open sound
        Objects.requireNonNull(player).playSound(SoundEvents.CHEST_OPEN, 1f, 1f);
        // If it isn't on the client side
        if (!level.isClientSide()) {
            // If the block is broken
            if (Objects.requireNonNull(level).destroyBlock(Objects.requireNonNull(pos), false, player)) {
                // The block is being broken for the first time
                if (!isBroken) {
                    // Runs a luck check
                    if(!isLuck){
                            // Check if is Good Luck
                            if (isGoodLuck) {
                                //Various condition checks
                                if(isItem){
                                    // Check if is item
                                    if(!hasGivenItem){
                                        // Give a vanilla item
                                        GiveItemToPlayer(player);
                                        // Sets isItem to false
                                        isItem = false;
                                        // Sets isBackpack to true
                                        isBackpack = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasGivenItem = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isItem = true;
                                }
                            }
                            // Check if is Good Luck
                            if(isGoodLuck){
                                //Various condition checks
                                if(isBackpack){
                                    // Check if is backpack item
                                    if(!hasGivenBackpack){
                                        //Give a backpack item
                                        GiveBackpackItemToPlayer(player);
                                        // Sets isBackpack to false
                                        isBackpack = false;
                                        // Sets isIronChestsChest to true
                                        isIronChestsChest = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasGivenBackpack = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isBackpack = false;
                                }
                            }
                            // Check if is Good Luck
                            if(isGoodLuck){
                                //Various condition checks
                                if(isIronChestsChest){
                                    //Check if is iron chests chest item
                                    if(!hasGivenIronChest){
                                        // Give a iron chests chest item
                                        GiveIronChestsItemToPlayer(player);
                                        // Sets isIronChestsChest to false
                                        isIronChestsChest = false;
                                        // Sets isGoodEffect to true
                                        isGoodEffect = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasGivenIronChest = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isIronChestsChest = false;
                                }
                            }
                            // Check if is Good Luck
                            if(isGoodLuck){
                                //Various condition checks
                                if(isGoodEffect){
                                    //Check if is iron chests chest item
                                    if(!hasGivenGoodEffect){
                                        // Give a good potioneffect
                                        GiveGoodpotionEffectToPlayer(player, 300*20, 2, false, true);
                                        // Sets isGoodEffect to false
                                        isGoodEffect = false;
                                        // Sets isPetSpawn to true
                                        isPetSpawn = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasGivenGoodEffect = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isGoodEffect = false;
                                }
                            }
                            // Check if is Good Luck
                            if(isGoodLuck){
                                //Various condition checks
                                if(isPetSpawn){
                                    //Check if the pet has spawned
                                    if(!hasSpawnedPet){
                                        // Spawn a pet on open
                                        SpawnPetAtPlayer((ServerLevel) level, pos, MobSpawnType.SPAWNER, player);
                                        // Sets isPetSpawn to false
                                        isPetSpawn = false;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasSpawnedPet = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isPetSpawn = false;
                                }
                            }
                            // Check if is Bad Luck
                            if (isBadLuck) {
                                //Various condition checks
                                if(isExplosion){
                                    // Check if the explosion has spawned
                                    if(!hasSpawnedExplosion){
                                        // Spawn a explosion on open
                                        SpawnExplosionAtPlayer(level, pos, player, true);
                                        // Sets isExplosion to false
                                        isExplosion = false;
                                        // Sets isMobSpawn to true
                                        isMobSpawn = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasSpawnedExplosion = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isExplosion = true;
                                }
                            }
                            // Check if is Bad Luck
                            if (isBadLuck) {
                                //Various condition checks
                                if(isMobSpawn){
                                    // Check if the monster has spawned
                                    if(!hasSpawnedMonster){
                                        // Spawn a monster on open
                                        SpawnMonsterAtPlayer((ServerLevel) level, pos, MobSpawnType.SPAWNER, player);
                                        // Sets isMobSpawn to false
                                        isMobSpawn = false;
                                        // Sets isBadEffect to true
                                        isBadEffect = true;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasSpawnedMonster = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isMobSpawn = false;
                                }
                            }
                            // Check if is Bad Luck
                            if (isBadLuck) {
                                //Various condition checks
                                if(isBadEffect){
                                    // Check if the bad effect has been applied to the player
                                    if(!hasGivenBadEffect){
                                        // Give a bad potion effect on open
                                        GiveBadpotionEffectToPlayer(player, 120*20, 2, false, true);
                                        // Sets isBadEffect to false
                                        isBadEffect = false;
                                    }
                                    // Reset the default boolean values
                                    else{
                                        hasGivenBadEffect = false;
                                    }
                                }
                                // Reset the default boolean values
                                else{
                                    isBadEffect = false;
                                }
                            }else{
                                // Reset the default boolean values
                                isGoodLuck = true;
                                isBadLuck = false;
                            }
                            isLuck = true;
                    }
                    // Reset the default boolean values
                    else{
                        isLuck = false;
                    }
                }
                // Reset the isBroken check
                else{
                    isBroken = false;
                }
            }

        }
        // Return the boolean method for onDestroyedByPlayer
        return true;
    }

    // Give the vanilla item method
    public void GiveItemToPlayer(Player player) {
        if(!isBroken){
                if(isGoodLuck && !isBadLuck){
                    if(!hasGivenItem){
                        if (!isgoodluckmessageSent) {
                            // Generate a random index to get a random translation key for a good luck message
                            int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                            // Send the good luck message to the player
                            Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                            // Set ismessageSent to true to prevent sending duplicate messages
                            isgoodluckmessageSent = true;
                            // Give the player an item from the ITEMS_LIST
                            Item item = Objects.requireNonNull(ITEMS_LIST)[Objects.requireNonNull(RANDOM).nextInt(ITEMS_LIST.length)];
                            player.getInventory().add(new ItemStack(Objects.requireNonNull(item)));
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        } else {
                            isgoodluckmessageSent = false;
                        }
                        // Set isBroken and hasGivenItem to true to indicate that the block has been broken
                        isBroken = true;
                        hasGivenItem = true;
                        isGoodLuck = false;
                        isBadLuck = true;
                        // Give the player a vanilla item
                    }
                }
            }
        else {
            isBroken = false;
        }
        return;

    }

    // Give the Traveler's Backpack backpack method
    public void GiveBackpackItemToPlayer(Player player) {
        if(!isBroken){
                if(isGoodLuck && !isBadLuck){
                    if(!hasGivenBackpack){
                        if (!isgoodluckmessageSent) {
                            Item backpack = ModItems.BACKPACKS.get(Objects.requireNonNull(RANDOM).nextInt(ModItems.BACKPACKS.size()));
                            // Generate a random index to get a random translation key for a good luck message
                            int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                            // Send the good luck message to the player
                            player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                            // Set ismessageSent to true to prevent sending duplicate messages
                            isgoodluckmessageSent = true;
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            player.getInventory().add(new ItemStack(backpack));
                        }else {
                            isgoodluckmessageSent = false;
                        }
                        // Set isBroken and hasGivenBackpack to true to indicate that the block has been broken
                        isBroken = true;
                        hasGivenBackpack = true;
                        isGoodLuck = false;
                        isBadLuck = true;
                        // Give the player a Backpack item
                    }
                }
        }else{
            isBroken = false;
        }
        return;

    }

    // Give the Iron Chests chest method
    public void GiveIronChestsItemToPlayer(Player player) {
        if(!isBroken){
                if(isGoodLuck && !isBadLuck){
                    if(!hasGivenIronChest){
                        if (isDirtChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.DIRT_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isDirtChest = false;
                            isIronChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isDirtChest = true;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isIronChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.IRON_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isIronChest = false;
                            isGoldChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isIronChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isGoldChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.GOLD_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isGoldChest = false;
                            isCopperChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isGoldChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isCopperChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.COPPER_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isCopperChest = false;
                            isDiamondChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isCopperChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isDiamondChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.DIAMOND_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isDiamondChest = false;
                            isObsidianChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isDiamondChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isObsidianChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.OBSIDIAN_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isObsidianChest = false;
                            isCrystalChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isObsidianChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isCrystalChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.CRYSTAL_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isCrystalChest = false;
                            isDirtChest = false;
                            isTrappedDirtChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isCrystalChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedDirtChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_DIRT_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedDirtChest = false;
                            isTrappedIronChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedDirtChest = true;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedIronChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_IRON_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedIronChest = false;
                            isTrappedGoldChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedIronChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedGoldChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_GOLD_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedGoldChest = false;
                            isTrappedCopperChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedGoldChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedCopperChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_COPPER_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedCopperChest = false;
                            isTrappedDiamondChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedCopperChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedDiamondChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_DIAMOND_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedDiamondChest = false;
                            isTrappedObsidianChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedDiamondChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedObsidianChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_OBSIDIAN_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedObsidianChest = false;
                            isTrappedCrystalChest = true;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedObsidianChest = false;
                        }
                    }
                    if(!hasGivenIronChest){
                        if (isTrappedCrystalChest) {
                            AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_CRYSTAL_CHEST).get();
                            Item item = chest.asItem().getDefaultInstance().getItem();
                            if (!isgoodluckmessageSent) {
                                // Generate a random index to get a random translation key for a good luck message
                                int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                // Send the good luck message to the player
                                player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                // Set ismessageSent to true to prevent sending duplicate messages
                                isgoodluckmessageSent = true;
                                // Play sounds to indicate the successful opening of the mystery box
                                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                player.getInventory().add(new ItemStack(item));
                            } else {
                                isgoodluckmessageSent = false;
                            }
                            isTrappedCrystalChest = false;
                            isTrappedDirtChest = false;
                            // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                            hasGivenIronChest = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isTrappedCrystalChest = false;
                        }
                    }
                    // Give the player a iron chests chest item
                }
        }else{
            isBroken = false;
        }
        return;

    }

    // Give the good effect method
    public void GiveGoodpotionEffectToPlayer(Player player, int time, int amplifier, boolean isAmbient, boolean HideParticles){
        if(!isBroken){
                if(isGoodLuck && !isBadLuck){
                    if(!hasGivenGoodEffect){
                        if(!isgoodluckmessageSent){
                            // Generate a random index to get a random translation key for a good luck message
                            int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                            // Send the good luck message to the player
                            player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                            // Set ismessageSent to true to prevent sending duplicate messages
                            isgoodluckmessageSent = true;
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            int index1 = RANDOM.nextInt(GOOD_EFFECTS.length);
                            MobEffect good = GOOD_EFFECTS[RANDOM.nextInt(index1)];
                            player.addEffect(new MobEffectInstance(good, time, amplifier, isAmbient, HideParticles));
                            // Set isBroken and hasGivenGoodEffect to true to indicate that the block has been broken
                            hasGivenGoodEffect = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isgoodluckmessageSent = false;
                        }
                    }
                }
        }else{
            isBroken = false;
        }
        return;
    }
    // Spawn the pet method
    public void SpawnPetAtPlayer(ServerLevel level, BlockPos pos, MobSpawnType type, Player player){
        if(!isBroken){
                if(isGoodLuck && !isBadLuck){
                    if(!hasSpawnedPet){
                        if(!isgoodluckmessageSent){
                            // Generate a random index to get a random translation key for a good luck message
                            int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                            // Send the good luck message to the player
                            player.sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                            // Set ismessageSent to true to prevent sending duplicate messages
                            isgoodluckmessageSent = true;
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            int index1 = RANDOM.nextInt(PETS.length);
                            EntityType<?> pet = PETS[RANDOM.nextInt(index1)];
                            level.addFreshEntity(pet.spawn(level, pos, type));
                            // Set isBroken and hasSpawnedPet to true to indicate that the block has been broken
                            hasSpawnedPet = true;
                            isBroken = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                        }else{
                            isgoodluckmessageSent = false;
                        }
                    }
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Spawn the explosion method
    public void SpawnExplosionAtPlayer(Level level, BlockPos pos, Player player, boolean SpawnFire){
        if(!isBroken){
                if(isBadLuck && !isGoodLuck){
                    if(!hasSpawnedExplosion){
                        if(!isbadluckmessageSent){
                            float radius = Objects.requireNonNull(explosion_radius)[Objects.requireNonNull(RANDOM).nextInt(explosion_radius.length)];
                            // Generate a random index to get a random translation key for a bad luck message
                            int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                            // Send the bad luck message to the player
                            Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.WITHER_AMBIENT, 1f, 1f);
                            // Select the player and spawn an explosion from the mysterybox
                            level.explode(player, player.getX(), player.getY(), player.getZ(), radius, SpawnFire, Level.ExplosionInteraction.BLOCK);
                            // Set isBroken and hasSpawnedExplosion to true to indicate that the block has been broken
                            isbadluckmessageSent = true;
                            hasSpawnedExplosion = true;
                            isBroken = true;
                            isGoodLuck = true;
                            isBadLuck = false;
                        }else{
                            isbadluckmessageSent = false;
                        }
                    }
                }
        }else{
            isBroken = false;
        }
    }

    // Spawn the monster method
    public void SpawnMonsterAtPlayer(ServerLevel level, BlockPos pos, MobSpawnType type, Player player){
        if(!isBroken){
                if(isBadLuck && !isGoodLuck){
                    if(!hasSpawnedMonster){
                        if(!isbadluckmessageSent){
                            // Generate a random index to get a random translation key for a bad luck message
                            int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                            // Send the bad luck message to the player
                            Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.WITHER_AMBIENT, 1f, 1f);
                            int index1 = RANDOM.nextInt(MONSTERS.length);
                            EntityType<?> monster = MONSTERS[RANDOM.nextInt(index1)];
                            level.addFreshEntity(monster.spawn(level, pos, type));
                            // Set isBroken and hasSpawnedMonster to true to indicate that the block has been broken
                            isbadluckmessageSent = true;
                            hasSpawnedMonster = true;
                            isBroken = true;
                            isGoodLuck = true;
                            isBadLuck = false;
                        }else{
                            isbadluckmessageSent = false;
                        }
                    }
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Give the bad effect method
    public void GiveBadpotionEffectToPlayer(Player player, int time, int amplifier, boolean isAmbient, boolean HideParticles){
        if(!isBroken){
                if(isBadLuck && !isGoodLuck){
                    if(!hasGivenBadEffect){
                        if(!isbadluckmessageSent){
                            // Generate a random index to get a random translation key for a bad luck message
                            int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                            // Send the bad luck message to the player
                            Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                            // Set ismessageSent to true to prevent sending duplicate messages
                            isbadluckmessageSent = true;
                            // Play sounds to indicate the successful opening of the mystery box
                            player.playSound(SoundEvents.WITHER_AMBIENT, 1f, 1f);
                            int index1 = RANDOM.nextInt(BAD_EFFECTS.length);
                            MobEffect bad = BAD_EFFECTS[RANDOM.nextInt(index1)];
                            player.addEffect(new MobEffectInstance(bad, time, amplifier, isAmbient, HideParticles));
                            // Set isBroken and hasGivenBadEffect to true to indicate that the block has been broken
                            hasGivenBadEffect = true;
                            isBroken = true;
                            isGoodLuck = true;
                            isBadLuck = false;
                        }else{
                            isbadluckmessageSent = false;
                        }
                    }
                }
        }else{
            isBroken = false;
        }
        return;
    }
}
