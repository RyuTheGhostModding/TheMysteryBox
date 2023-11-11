package com.ryutheghost.themysterybox.block;

import com.ryutheghost.themysterybox.particles.ModParticles;
import com.ryutheghost.themysterybox.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SuperUnluckyMysteryBoxBlock extends Block {
    public SuperUnluckyMysteryBoxBlock(Properties pProperties) {
        super(Objects.requireNonNull(pProperties));
    }

    private static final Random RANDOM = new Random();

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
            pTooltip.add(Component.translatable("mysterybox_shift_key_not_down")); // Add a translation component indicating shift key is not down
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag); // Call the superclass's appendHoverText method
    }

    public boolean isbadluckmessageSent = false; // Boolean field to keep track of whether a message has been sent or not
    public boolean hasSpawnedExplosion = false; // Boolean field to check if it is an explosion
    public boolean hasSpawnedMonster = false; // Boolean field to check if it is a monster
    public boolean hasGivenBadEffect = false; // Boolean field to check if it is a bad potion effect
    public boolean hasTPToAir = false; // Boolean field to check if a teleport has been given to a player
    public boolean hasClearedInventory = false; // Boolean field to check if a player's inventory has been cleared
    public boolean hasNukedWorld = false; // Boolean field to check if a player has been nuked
    public boolean hasRainedArrows = false; // Boolean field to check if arrows have rained on a player
    public boolean isBadLuck = true; // Boolean field to check if it is bad luck
    public boolean isBroken = false; // Boolean field to check if it is broken
    public boolean isExplosion = true; // Boolean field to check if it is an explosion
    public boolean isMobSpawn = false; // Boolean field to check if it is a monster
    public boolean isTPToAir = false; // Boolean field to check if it is a teleport
    public boolean isClearedInventory = false; // Boolean field to check if a player's inventory is cleared
    public boolean isBadEffect = false; // Boolean field to check if it is a bad effect
    public boolean isNuke = false; // Boolean field to check if a player is nuked
    public boolean isRainingArrows = false; // Boolean field to check if arrows are raining on a player
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
                                        player.sendSystemMessage(Component.nullToEmpty("§4Oh no! Not an explosion."));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Spawn an explosion on open
                                    SpawnExplosionAtPlayer(level, pos, player, true, player);
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
                                        player.sendSystemMessage(Component.nullToEmpty("§4Oh no! Prepare to fight the monster that spawned."));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Spawn a monster on open
                                    SpawnMonsterAtPlayer((ServerLevel) level, pos.above(), MobSpawnType.SPAWNER, player, player);
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
                                    GiveBadpotionEffectToPlayer(player, 500, 2, false, true, level, pos, player);
                                    // Sets isBadEffect to false
                                    isBadEffect = false;
                                    isTPToAir = true;
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
                        }if (isBadLuck) {
                            //Various condition checks
                            if (isTPToAir) {
                                // Check if the block teleported the player in the air
                                if (!hasTPToAir) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                        player.sendSystemMessage(Component.nullToEmpty("§4Look out below!\n§4Looks like you're catching some serious air my friend."));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Teleport the player on open
                                    TPToAir(pos, (ServerPlayer) player, (ServerLevel) level, level, player);
                                    // Sets isTPToAir to false
                                    isTPToAir = false;
                                    isClearedInventory = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasTPToAir = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isTPToAir = false;
                            }
                        }if (isBadLuck) {
                            //Various condition checks
                            if (isClearedInventory) {
                                // Check if the player lost their inventory
                                if (!hasClearedInventory) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                        player.sendSystemMessage(Component.nullToEmpty("§4Oh no! You've lost everything in your inventory."));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Clear the inventory of the player on open
                                    ClearInventory(player, level, pos, player);
                                    // Sets isClearedInventory to false
                                    isClearedInventory = false;
                                    isNuke = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasClearedInventory = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isClearedInventory = false;
                            }
                        }if (isBadLuck) {
                            //Various condition checks
                            if (isNuke) {
                                // Check if the player has nuked the world
                                if (!hasNukedWorld) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                        player.sendSystemMessage(Component.nullToEmpty("§4Oh no! You've unleashed a Nuke upon this world."));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Nuke the world of the player on open
                                    SpawnNukeAtPlayer(level, pos, player, true, player);
                                    // Sets isNuke to false
                                    isNuke = false;
                                    isRainingArrows = true;
                                }
                                // Reset the default boolean values
                                else {
                                    hasNukedWorld = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isNuke = false;
                            }
                        }
                        if (isBadLuck) {
                            //Various condition checks
                            if (isRainingArrows) {
                                // Check if the player had arrows rained on them
                                if (!hasRainedArrows) {
                                    if(!isbadluckmessageSent){
                                        // Generate a random index to get a random translation key for a bad luck message
                                        int index = new Random().nextInt(Objects.requireNonNull(bad_translation_keys_messages).size());
                                        // Send the bad luck message to the player
                                        Objects.requireNonNull(player).sendSystemMessage(Component.translatable(Objects.requireNonNull(bad_translation_keys_messages.get(index))));
                                        player.sendSystemMessage(Component.nullToEmpty("§4Oh no! It's raining arrows! Duck and cover!"));
                                    }else{
                                        isbadluckmessageSent = false;
                                    }
                                    // Rain Arrows in the world of the player on open
                                    SpawnArrowRainAtPlayer(level, pos, player, player);
                                    // Sets isRainingArrows to false
                                    isRainingArrows = false;
                                }
                                // Reset the default boolean values
                                else {
                                    hasRainedArrows = false;
                                }
                            }
                            // Reset the default boolean values
                            else {
                                isRainingArrows = false;
                            }
                        }
                        else{
                            isBadLuck = true;
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

    private void spawnBadLuckParticles(LivingEntity pContext, BlockPos positionClicked, BlockState blockState) {
        for(int i = 0; i < 20; i++) {
            ServerLevel level = (ServerLevel) pContext.level();

            level.sendParticles(ModParticles.SKULL_PARTICLES.get(),
                    positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d, 1,
                    Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    // Spawn the explosion method
    public void SpawnExplosionAtPlayer(Level level, BlockPos pos, Player player, boolean SpawnFire, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck) {
                    if(!hasSpawnedExplosion){
                        float radius = Objects.requireNonNull(explosion_radius)[Objects.requireNonNull(RANDOM).nextInt(explosion_radius.length)];
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        // Select the player and spawn an explosion from the mysterybox
                        level.explode(player, player.getX(), player.getY(), player.getZ(), radius, SpawnFire, Level.ExplosionInteraction.BLOCK);
                        // Set isBroken and hasSpawnedExplosion to true to indicate that the block has been broken
                        hasSpawnedExplosion = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                }else{
                    isBadLuck = true;
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
    public void SpawnMonsterAtPlayer(ServerLevel level, BlockPos pos, MobSpawnType type, Player player, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck){
                    if(!hasSpawnedMonster){
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        int index1 = RANDOM.nextInt(MONSTERS.length);
                        EntityType<?> monster = MONSTERS[RANDOM.nextInt(index1)];
                        level.addFreshEntity(monster.spawn(level, new BlockPos(pos.getX(), pos.above().getY() + 2, pos.getZ()), type));
                        // Set isBroken and hasSpawnedMonster to true to indicate that the block has been broken
                        hasSpawnedMonster = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                }else{
                    isBadLuck = true;
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
    public void GiveBadpotionEffectToPlayer(Player player, int time, int amplifier, boolean isAmbient, boolean HideParticles, Level level, BlockPos pos, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck){
                    if(!hasGivenBadEffect){
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        int index1 = RANDOM.nextInt(BAD_EFFECTS.length);
                        MobEffect bad = BAD_EFFECTS[RANDOM.nextInt(index1)];
                        player.addEffect(new MobEffectInstance(bad, time, amplifier, isAmbient, HideParticles));
                        // Set isBroken and hasGivenBadEffect to true to indicate that the block has been broken
                        hasGivenBadEffect = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                }else{
                    isBadLuck = true;
                }
            }else{
                isLuck = false;
            }
        }else{
            isBroken = false;
        }
        return;
    }

    public void TPToAir(BlockPos pos, ServerPlayer player, ServerLevel level, Level level1, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck) {
                    if (!hasTPToAir) {
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, player.blockPosition().above(), ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.PLAYERS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        BlockPos pos1 = player.getOnPos().containing(player.getBlockX(), player.getBlockY(), player.getBlockZ());
                        player.teleportTo(pos1.getX(), pos1.getY() + 3000.0d, pos1.getZ());
                        // Set isBroken and hasGivenBadEffect to true to indicate that the block has been broken
                        hasTPToAir = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }

                }else{
                    isBadLuck = true;
                }
            }else{
                isLuck = false;
            }
        }else{
            isBroken = false;
        }
        return;
    }

    public void ClearInventory(Player player, Level level, BlockPos pos, LivingEntity entity) {
        if (!isBroken) {
            if (!isLuck) {
                if (isBadLuck) {
                    if (!hasClearedInventory) {
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        player.getInventory().clearContent();
                        // Set isBroken and hasClearedInventory to true to indicate that the block has been broken
                        hasClearedInventory = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                } else {
                    isBadLuck = true;
                }
            } else {
                isLuck = false;
            }
        }else{
            isBroken = false;
        }
        return;
    }

    public void SpawnNukeAtPlayer(Level level, BlockPos pos, Player player, boolean SpawnFire, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck) {
                    if(!hasNukedWorld){
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.NUKE_ALERT.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }
                        // Select the player and spawn a nuke from the mysterybox
                        level.explode(player, player.getX(), player.getY(), player.getZ(), 50.0f, SpawnFire, Level.ExplosionInteraction.BLOCK);
                        for (int i = 1; i <= 1500; i++) {
                            double x = player.getX() + i;
                            double y = player.getY();
                            double z = player.getZ();

                            level.explode(player, x, y, z, 50.0f, SpawnFire, Level.ExplosionInteraction.BLOCK);
                        }
                        for (int i = 1; i <= 1500; i++) {
                            double x = player.getX();
                            double y = player.getY() - i;
                            double z = player.getZ();

                            level.explode(player, x, y, z, 50.0f, SpawnFire, Level.ExplosionInteraction.BLOCK);
                        }
                        // Set isBroken and hasNukedWorld to true to indicate that the block has been broken
                        hasNukedWorld = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                }else{
                    isBadLuck = true;
                }
            }else{
                isLuck = false;
            }
        }else{
            isBroken = false;
        }
        return;
    }

    public void SpawnArrowRainAtPlayer(Level level, BlockPos pos, Player player, LivingEntity entity){
        if(!isBroken){
            if(!isLuck){
                if(isBadLuck) {
                    if(!hasRainedArrows){
                        // Play sounds to indicate the successful opening of the mystery box
                        level.playSound(null, pos, ModSounds.MYSTERY_BOX_BAD_LUCK.get(), SoundSource.BLOCKS, 1f, 1f);
                        // Spawn Skull Particles to indicate the successful opening of the mystery box
                        entity = player;
                        for(int i = 0; i <= pos.getY() + 4; i++) {
                            BlockState blockState = entity.level().getBlockState(pos.above(i));
                            spawnBadLuckParticles(entity, pos, blockState);
                            break;
                        }

                        // Select the player and spawn a rain of arrows from the mysterybox
                        // Create the Arrow Projectile Entity at the specified coordinates
                        // Please replace 'YourArrowEntity' with your actual Arrow Projectile Entity class
                        Arrow arrow = new Arrow(level, player.getX(), player.getY() + 100, player.getZ());

                        // Spawn the entity into the world
                        level.addFreshEntity(arrow);
                        for (int i = 1; i <= 500; i++) {
                            double x = player.getX() + i;
                            double y = player.getY() + 100;
                            double z = player.getZ();

                            // Create the Arrow Projectile Entity at the specified coordinates
                            // Please replace 'YourArrowEntity' with your actual Arrow Projectile Entity class
                            Arrow arrow1 = new Arrow(level, x, y, z);

                            // Spawn the entity into the world
                            level.addFreshEntity(arrow1);
                        }
                        // Set isBroken and hasRainedArrows to true to indicate that the block has been broken
                        hasRainedArrows = true;
                        isBroken = true;
                        isBadLuck = false;
                        isLuck = true;
                    }
                }else{
                    isBadLuck = true;
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
