package com.ryutheghost.themysterybox.block;

import com.progwml6.ironchest.common.block.IronChestsBlocks;
import com.progwml6.ironchest.common.block.regular.AbstractIronChestBlock;
import com.ryutheghost.themysterybox.sound.ModSounds;
import com.tiviacz.travelersbackpack.init.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.data.SoundDefinition;
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
            Items.SEA_LANTERN,
            Items.PRISMARINE_SHARD,
            Items.PRISMARINE_CRYSTALS,
            Items.SMOKER,
            Items.DROPPER,
            Items.HOPPER,
            Items.CAULDRON,
            Items.HOPPER_MINECART,
            Items.REDSTONE_LAMP,
            Items.REDSTONE_TORCH,
            Items.TORCH,
            Items.REPEATER,
            Items.COMPARATOR,
            Items.COMPASS,
            Items.COMPOSTER,
            Items.MAP,
            Items.ITEM_FRAME,
            Items.GLOW_ITEM_FRAME,
            Items.OAK_BOAT,
            Items.OAK_BUTTON,
            Items.OAK_DOOR,
            Items.OAK_FENCE,
            Items.OAK_CHEST_BOAT,
            Items.OAK_FENCE_GATE,
            Items.OAK_HANGING_SIGN,
            Items.OAK_LEAVES,
            Items.OAK_PLANKS,
            Items.OAK_LOG,
            Items.OAK_PRESSURE_PLATE,
            Items.OAK_SAPLING,
            Items.OAK_SIGN,
            Items.OAK_SLAB,
            Items.OAK_STAIRS,
            Items.OAK_TRAPDOOR,
            Items.OAK_WOOD,
            Items.STRIPPED_OAK_LOG,
            Items.STRIPPED_OAK_WOOD,
            Items.DARK_OAK_BOAT,
            Items.DARK_OAK_BUTTON,
            Items.DARK_OAK_DOOR,
            Items.DARK_OAK_FENCE,
            Items.DARK_OAK_CHEST_BOAT,
            Items.DARK_OAK_FENCE_GATE,
            Items.DARK_OAK_HANGING_SIGN,
            Items.DARK_OAK_LEAVES,
            Items.DARK_OAK_PLANKS,
            Items.DARK_OAK_LOG,
            Items.DARK_OAK_PRESSURE_PLATE,
            Items.DARK_OAK_SAPLING,
            Items.DARK_OAK_SIGN,
            Items.DARK_OAK_SLAB,
            Items.DARK_OAK_STAIRS,
            Items.DARK_OAK_TRAPDOOR,
            Items.DARK_OAK_WOOD,
            Items.STRIPPED_DARK_OAK_LOG,
            Items.STRIPPED_DARK_OAK_WOOD,
            Items.SPRUCE_BOAT,
            Items.SPRUCE_BUTTON,
            Items.SPRUCE_DOOR,
            Items.SPRUCE_FENCE,
            Items.SPRUCE_CHEST_BOAT,
            Items.SPRUCE_FENCE_GATE,
            Items.SPRUCE_HANGING_SIGN,
            Items.SPRUCE_LEAVES,
            Items.SPRUCE_PLANKS,
            Items.SPRUCE_LOG,
            Items.SPRUCE_PRESSURE_PLATE,
            Items.SPRUCE_SAPLING,
            Items.SPRUCE_SIGN,
            Items.SPRUCE_SLAB,
            Items.SPRUCE_STAIRS,
            Items.SPRUCE_TRAPDOOR,
            Items.SPRUCE_WOOD,
            Items.STRIPPED_SPRUCE_LOG,
            Items.STRIPPED_SPRUCE_WOOD,
            Items.ACACIA_BOAT,
            Items.ACACIA_BUTTON,
            Items.ACACIA_DOOR,
            Items.ACACIA_FENCE,
            Items.ACACIA_CHEST_BOAT,
            Items.ACACIA_FENCE_GATE,
            Items.ACACIA_HANGING_SIGN,
            Items.ACACIA_LEAVES,
            Items.ACACIA_PLANKS,
            Items.ACACIA_LOG,
            Items.ACACIA_PRESSURE_PLATE,
            Items.ACACIA_SAPLING,
            Items.ACACIA_SIGN,
            Items.ACACIA_SLAB,
            Items.ACACIA_STAIRS,
            Items.ACACIA_TRAPDOOR,
            Items.ACACIA_WOOD,
            Items.STRIPPED_ACACIA_LOG,
            Items.STRIPPED_ACACIA_WOOD,
            Items.BIRCH_BOAT,
            Items.BIRCH_BUTTON,
            Items.BIRCH_DOOR,
            Items.BIRCH_FENCE,
            Items.BIRCH_CHEST_BOAT,
            Items.BIRCH_FENCE_GATE,
            Items.BIRCH_HANGING_SIGN,
            Items.BIRCH_LEAVES,
            Items.BIRCH_PLANKS,
            Items.BIRCH_LOG,
            Items.BIRCH_PRESSURE_PLATE,
            Items.BIRCH_SAPLING,
            Items.BIRCH_SIGN,
            Items.BIRCH_SLAB,
            Items.BIRCH_STAIRS,
            Items.BIRCH_TRAPDOOR,
            Items.BIRCH_WOOD,
            Items.STRIPPED_BIRCH_LOG,
            Items.STRIPPED_BIRCH_WOOD,
            Items.JUNGLE_BOAT,
            Items.JUNGLE_BUTTON,
            Items.JUNGLE_DOOR,
            Items.JUNGLE_FENCE,
            Items.JUNGLE_CHEST_BOAT,
            Items.JUNGLE_FENCE_GATE,
            Items.JUNGLE_HANGING_SIGN,
            Items.JUNGLE_LEAVES,
            Items.JUNGLE_PLANKS,
            Items.JUNGLE_LOG,
            Items.JUNGLE_PRESSURE_PLATE,
            Items.JUNGLE_SAPLING,
            Items.JUNGLE_SIGN,
            Items.JUNGLE_SLAB,
            Items.JUNGLE_STAIRS,
            Items.JUNGLE_TRAPDOOR,
            Items.JUNGLE_WOOD,
            Items.STRIPPED_JUNGLE_LOG,
            Items.STRIPPED_JUNGLE_WOOD,
            Items.BLACK_BED,
            Items.BROWN_BED,
            Items.BLUE_BED,
            Items.RED_BED,
            Items.GREEN_BED,
            Items.WHITE_BED,
            Items.WHITE_DYE,
            Items.BROWN_DYE,
            Items.RED_DYE,
            Items.BLACK_DYE,
            Items.GREEN_DYE,
            Items.BLUE_DYE,
            Items.PISTON,
            Items.STICKY_PISTON,
            Items.SLIME_BALL,
            Items.SLIME_BLOCK,
            Items.HONEY_BLOCK,
            Items.VINE,
            Items.COCOA_BEANS,
            Items.BOOKSHELF,
            Items.BOOK,
            Items.WRITABLE_BOOK,
            Items.KNOWLEDGE_BOOK,
            Items.CHISELED_BOOKSHELF,
            Items.LADDER,
            Items.LEVER,
            Items.NETHERRACK,
            Items.BLACK_WOOL,
            Items.WHITE_WOOL,
            Items.BROWN_WOOL,
            Items.GREEN_WOOL,
            Items.BLUE_WOOL,
            Items.RED_WOOL,
            Items.GLASS,
            Items.SAND


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
            EntityType.MULE,
            EntityType.BEE,
            EntityType.RABBIT,
            EntityType.AXOLOTL,
            EntityType.FROG,
            EntityType.PANDA,
            EntityType.FOX, EntityType.MOOSHROOM,
            EntityType.ALLAY,
            EntityType.CAMEL
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
            MobEffects.BAD_OMEN,
            MobEffects.DARKNESS
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

    public boolean isgoodluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not
    public boolean isbadluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not
    public boolean hasGivenItem = false; // Boolean field to check if it is a vanilla item
    public boolean hasGivenBackpack = false; // Boolean field to check if it is a backpack
    public boolean hasGivenIronChest = false; // Boolean field to check if it is an iron chest
    public boolean hasSpawnedExplosion = false; // Boolean field to check if it is a explosion
    public boolean hasSpawnedMonster = false; // Boolean field to check if it is a monster
    public boolean hasSpawnedPet = false; // Boolean field to check if it is a pet
    public boolean hasGivenBadEffect = false; // Boolean field to check if it is a bad potion effect
    public boolean hasGivenGoodEffect = false; // Boolean field to check if it is a good potion effect
    public boolean isGoodLuck = true; // Boolean field to check if it is good luck
    public boolean isBadLuck = false; // Boolean field to check if it is bad luck
    public boolean isDirtChest = true; // Boolean field to check if it is dirt
    public boolean isIronChest = false; // Boolean field to check if it is iron
    public boolean isGoldChest = false; // Boolean field to check if it is gold
    public boolean isCopperChest = false; // Boolean field to check if it is copper
    public boolean isDiamondChest = false; // Boolean field to check if it is diamond
    public boolean isObsidianChest = false; // Boolean field to check if it is obsidian
    public boolean isCrystalChest = false; // Boolean field to check if it is crystal
    public boolean isTrappedDirtChest = false; // Boolean field to check if it is dirt
    public boolean isTrappedIronChest = false; // Boolean field to check if it is iron
    public boolean isTrappedGoldChest = false; // Boolean field to check if it is gold
    public boolean isTrappedCopperChest = false; // Boolean field to check if it is copper
    public boolean isTrappedDiamondChest = false; // Boolean field to check if it is diamond
    public boolean isTrappedObsidianChest = false; // Boolean field to check if it is obsidian
    public boolean isTrappedCrystalChest = false; // Boolean field to check if it is crystal
    public boolean isBroken = false; // Boolean field to check if it is broken
    public boolean isItem = true; // Boolean field to check if it is a vanilla item
    public boolean isBackpack = false; // Boolean field to check if it is a backpack
    public boolean isIronChestsChest = false; // Boolean field to check if it is an iron chests chest
    public boolean isExplosion = true; // Boolean field to check if it is a explosion
    public boolean isMobSpawn = false; // Boolean field to check if it is a monster
    public boolean isPetSpawn = false; // Boolean field to check if it is a pet
    public boolean isBadEffect = false; // Boolean field to check if it is a bad effect
    public boolean isGoodEffect = false; // Boolean field to check if it is a good effect
    public boolean isLuck = false; // Boolean field to check if it is luck

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
                    if (!isLuck) {
                        // Check if is Good Luck
                        if (isGoodLuck) {
                            //Various condition checks
                            if (isItem) {
                                // Check if is item
                                if (!hasGivenItem) {
                                    if (!isgoodluckmessageSent) {
                                        // Generate a random index to get a random translation key for a good luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                        // Send the good luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                        // Set ismessageSent to true to prevent sending duplicate messages
                                        isgoodluckmessageSent = true;
                                    } else {
                                        isgoodluckmessageSent = false;
                                    }
                                    // Give a vanilla item
                                    GiveItemToPlayer(player, level, pos);
                                    // Sets isItem to false
                                    isItem = false;
                                    // Sets isBackpack to true
                                    isBackpack = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasGivenItem = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isItem = true;
                            }
                        }if (isGoodLuck) {
                            //Various condition checks
                            if (isBackpack) {
                                // Check if is backpack item
                                if (!hasGivenBackpack) {
                                    if (!isgoodluckmessageSent) {
                                        // Generate a random index to get a random translation key for a good luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                        // Send the good luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                        // Set ismessageSent to true to prevent sending duplicate messages
                                        isgoodluckmessageSent = true;
                                    } else {
                                        isgoodluckmessageSent = false;
                                    }
                                    //Give a backpack item
                                    GiveBackpackItemToPlayer(player, level, pos);
                                    // Sets isBackpack to false
                                    isBackpack = false;
                                    // Sets isIronChestsChest to true
                                    isIronChestsChest = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasGivenBackpack = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isBackpack = false;
                            }
                        }if (isGoodLuck) {
                            //Various condition checks
                            if (isIronChestsChest) {
                                //Check if is iron chests chest item
                                if (!hasGivenIronChest) {
                                    if (!isgoodluckmessageSent) {
                                        // Generate a random index to get a random translation key for a good luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                        // Send the good luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                        // Set ismessageSent to true to prevent sending duplicate messages
                                        isgoodluckmessageSent = true;
                                    } else {
                                        isgoodluckmessageSent = false;
                                    }
                                    // Give a iron chests chest item
                                    GiveIronChestsItemToPlayer(player, level, pos);
                                    // Sets isIronChestsChest to false
                                    isIronChestsChest = false;
                                    // Sets isGoodEffect to true
                                    isGoodEffect = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasGivenIronChest = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isIronChestsChest = false;
                            }
                        }if (isGoodLuck) {
                            //Various condition checks
                            if (isGoodEffect) {
                                //Check if is iron chests chest item
                                if (!hasGivenGoodEffect) {
                                    if (!isgoodluckmessageSent) {
                                        // Generate a random index to get a random translation key for a good luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                        // Send the good luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                        // Set ismessageSent to true to prevent sending duplicate messages
                                        isgoodluckmessageSent = true;
                                    } else {
                                        isgoodluckmessageSent = false;
                                    }
                                    // Give a good potioneffect
                                    GiveGoodpotionEffectToPlayer(player, 500, 2, false, true, level, pos);
                                    // Sets isGoodEffect to false
                                    isGoodEffect = false;
                                    // Sets isPetSpawn to true
                                    isPetSpawn = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasGivenGoodEffect = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isGoodEffect = false;
                            }
                        }if (isGoodLuck) {
                            //Various condition checks
                            if (isPetSpawn) {
                                //Check if the pet has spawned
                                if (!hasSpawnedPet) {
                                    if (!isgoodluckmessageSent) {
                                        // Generate a random index to get a random translation key for a good luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(good_translation_keys_messages).size());
                                        // Send the good luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(good_translation_keys_messages.get(index))));
                                        // Set ismessageSent to true to prevent sending duplicate messages
                                        isgoodluckmessageSent = true;
                                    } else {
                                        isgoodluckmessageSent = false;
                                    }
                                    // Spawn a pet on open
                                    SpawnPetAtPlayer((ServerLevel) level, pos, MobSpawnType.SPAWNER, player, level);
                                    // Sets isPetSpawn to false
                                    isPetSpawn = false;
                                }
                                // Reset the default boolean values
                                else {
                                    hasSpawnedPet = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isPetSpawn = false;
                            }
                        }else{
                            isGoodLuck = true;
                        }
                    }if (!isLuck) {
                        // Check if is Bad Luck
                        if (isBadLuck) {
                            //Various condition checks
                            if (isExplosion) {
                                // Check if the explosion has spawned
                                if (!hasSpawnedExplosion) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Spawn a explosion on open
                                    SpawnExplosionAtPlayer(level, pos, player, true);
                                    // Sets isExplosion to false
                                    isExplosion = false;
                                    // Sets isMobSpawn to true
                                    isMobSpawn = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasSpawnedExplosion = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isExplosion = true;
                            }
                        }if (isBadLuck) {
                            //Various condition checks
                            if (isMobSpawn) {
                                // Check if the monster has spawned
                                if (!hasSpawnedMonster) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Spawn a monster on open
                                    SpawnMonsterAtPlayer((ServerLevel) level, pos, MobSpawnType.SPAWNER, player);
                                    // Sets isMobSpawn to false
                                    isMobSpawn = false;
                                    // Sets isBadEffect to true
                                    isBadEffect = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasSpawnedMonster = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isMobSpawn = false;
                            }
                        }if (isBadLuck) {
                            //Various condition checks
                            if (isBadEffect) {
                                // Check if the bad effect has been applied to the player
                                if (!hasGivenBadEffect) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Give a bad potion effect on open
                                    GiveBadpotionEffectToPlayer(player, 500, 2, false, true, level, pos);
                                    // Sets isBadEffect to false
                                    isBadEffect = false;
                                }
                                // Reset the default boolean values
                                else {
                                    hasGivenBadEffect = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isBadEffect = false;
                            }
                        }else{
                            isBadLuck = false;
                        }
                    }else{
                        isLuck = false;
                    }
                }else{
                    isBroken = false;
                }

            }

        }
        // Return the boolean method for onDestroyedByPlayer
        return true;
    }

    // Give the vanilla item method
    public void GiveItemToPlayer(Player player, Level level, BlockPos pos) {
        if(!isBroken){
                if(!isLuck){
                    if(isGoodLuck){
                        if(!hasGivenItem){
                                // Give the player an item from the ITEMS_LIST
                                Item item = Objects.requireNonNull(ITEMS_LIST)[Objects.requireNonNull(RANDOM).nextInt(ITEMS_LIST.length)];
                                player.getInventory().add(new ItemStack(Objects.requireNonNull(item)));
                                // Play sounds to indicate the successful opening of the mystery box
                                level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                            // Set isBroken and hasGivenItem to true to indicate that the block has been broken
                            isBroken = true;
                            hasGivenItem = true;
                            isGoodLuck = false;
                            isBadLuck = true;
                            isLuck = true;
                            // Give the player a vanilla item
                        }
                    }else{
                        isGoodLuck = true;
                    }
                }else{
                    isLuck = false;
                }
            }else {
            isBroken = false;
        }
        return;

    }

    // Give the Traveler's Backpack backpack method
    public void GiveBackpackItemToPlayer(Player player, Level level, BlockPos pos) {
        if(!isBroken) {
            if (!isLuck) {
                if (isGoodLuck) {
                    if (!hasGivenBackpack) {
                            Item backpack = ModItems.BACKPACKS.get(Objects.requireNonNull(RANDOM).nextInt(ModItems.BACKPACKS.size()));
                            // Play sounds to indicate the successful opening of the mystery box
                            level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                            player.getInventory().add(new ItemStack(backpack));
                        // Set isBroken and hasGivenBackpack to true to indicate that the block has been broken
                        isBroken = true;
                        hasGivenBackpack = true;
                        isGoodLuck = false;
                        isBadLuck = true;
                        isLuck = true;
                        // Give the player a Backpack item
                    }
                } else {
                    isGoodLuck = true;
                }
            } else {
                isLuck = false;
            }
        }else
        {
            isBroken = false;
        }
        return;
    }

    // Give the Iron Chests chest method
    public void GiveIronChestsItemToPlayer(Player player, Level level, BlockPos pos) {
        if(!isBroken){
                if(!isLuck){
                    if(isGoodLuck){
                        if(!hasGivenIronChest){
                            if (isDirtChest) {
                                AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.DIRT_CHEST).get();
                                Item item = chest.asItem().getDefaultInstance().getItem();
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
                                isTrappedDirtChest = false;
                                isTrappedIronChest = true;
                                // Set isBroken and hasGivenIronChest to true to indicate that the block has been broken
                                hasGivenIronChest = true;
                                isBroken = true;
                                isGoodLuck = false;
                                isBadLuck = true;
                            }else{
                                isTrappedDirtChest = false;
                            }
                        }
                        if(!hasGivenIronChest){
                            if (isTrappedIronChest) {
                                AbstractIronChestBlock chest = Objects.requireNonNull(IronChestsBlocks.TRAPPED_IRON_CHEST).get();
                                Item item = chest.asItem().getDefaultInstance().getItem();
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                                    // Play sounds to indicate the successful opening of the mystery box
                                    level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                    player.getInventory().add(new ItemStack(item));
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
                        isLuck = true;
                    }else{
                        isGoodLuck = true;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Give the good effect method
    public void GiveGoodpotionEffectToPlayer(Player player, int time, int amplifier, boolean isAmbient, boolean HideParticles, Level level, BlockPos pos){
        if(!isBroken){
                if(!isLuck){
                    if(isGoodLuck){
                        if(!hasGivenGoodEffect){
                                // Play sounds to indicate the successful opening of the mystery box
                                level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                int index1 = RANDOM.nextInt(GOOD_EFFECTS.length);
                                MobEffect good = GOOD_EFFECTS[RANDOM.nextInt(index1)];
                                player.addEffect(new MobEffectInstance(good, time, amplifier, isAmbient, HideParticles));
                                // Set isBroken and hasGivenGoodEffect to true to indicate that the block has been broken
                                hasGivenGoodEffect = true;
                                isBroken = true;
                                isGoodLuck = false;
                                isBadLuck = true;
                                isLuck = true;
                        }
                    }else{
                        isGoodLuck = true;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }
    // Spawn the pet method
    public void SpawnPetAtPlayer(ServerLevel level, BlockPos pos, MobSpawnType type, Player player, Level level2){
        if(!isBroken){
                if(!isLuck){
                    if(isGoodLuck){
                        if(!hasSpawnedPet){
                                // Play sounds to indicate the successful opening of the mystery box
                                level.playSound(null, pos, ModSounds.MYSTERY_BOX_GOOD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                int index1 = RANDOM.nextInt(PETS.length);
                                EntityType<?> pet = PETS[RANDOM.nextInt(index1)];
                                level.addFreshEntity(pet.spawn(level, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), type));
                                // Set isBroken and hasSpawnedPet to true to indicate that the block has been broken
                                hasSpawnedPet = true;
                                isBroken = true;
                                isGoodLuck = false;
                                isBadLuck = true;
                                isLuck = true;
                        }
                    }else{
                        isGoodLuck = true;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Spawn the explosion method
    public void SpawnExplosionAtPlayer(Level level, BlockPos pos, Player player, boolean SpawnFire){
        if(!isBroken){
                if(!isLuck){
                    if(isBadLuck) {
                        float radius = Objects.requireNonNull(explosion_radius)[Objects.requireNonNull(RANDOM).nextInt(explosion_radius.length)];
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Select the player and spawn an explosion from the mysterybox
                        level.explode(player, player.getX(), player.getY(), player.getZ(), radius, SpawnFire, Level.ExplosionInteraction.BLOCK);
                        // Set isBroken and hasSpawnedExplosion to true to indicate that the block has been broken
                        hasSpawnedExplosion = true;
                        isBroken = true;
                        isGoodLuck = true;
                        isBadLuck = false;
                        isLuck = true;
                    }else{
                        isBadLuck = false;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Spawn the monster method
    public void SpawnMonsterAtPlayer(ServerLevel level, BlockPos pos, MobSpawnType type, Player player){
        if(!isBroken){
                if(!isLuck){
                    if(isBadLuck){
                        if(!hasSpawnedMonster){
                                // Play sounds to indicate the successful opening of the mystery box
                                level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                int index1 = RANDOM.nextInt(MONSTERS.length);
                                EntityType<?> monster = MONSTERS[RANDOM.nextInt(index1)];
                                level.addFreshEntity(monster.spawn(level, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), type));
                                // Set isBroken and hasSpawnedMonster to true to indicate that the block has been broken
                                hasSpawnedMonster = true;
                                isBroken = true;
                                isGoodLuck = true;
                                isBadLuck = false;
                                isLuck = true;
                        }
                    }else{
                        isBadLuck = false;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }

    // Give the bad effect method
    public void GiveBadpotionEffectToPlayer(Player player, int time, int amplifier, boolean isAmbient, boolean HideParticles, Level level, BlockPos pos){
        if(!isBroken){
                if(!isLuck){
                    if(isBadLuck){
                        if(!hasGivenBadEffect){
                                // Play sounds to indicate the successful opening of the mystery box
                                level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                                int index1 = RANDOM.nextInt(BAD_EFFECTS.length);
                                MobEffect bad = BAD_EFFECTS[RANDOM.nextInt(index1)];
                                player.addEffect(new MobEffectInstance(bad, time, amplifier, isAmbient, HideParticles));
                                // Set isBroken and hasGivenBadEffect to true to indicate that the block has been broken
                                hasGivenBadEffect = true;
                                isBroken = true;
                                isGoodLuck = true;
                                isBadLuck = false;
                                isLuck = true;
                        }
                    }else{
                        isBadLuck = false;
                    }
                }else{
                    isLuck = false;
                }
        }else{
            isBroken = false;
        }
        return;
    }
}
