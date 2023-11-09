package com.ryutheghost.themysterybox.util.events;

import com.ryutheghost.themysterybox.block.registry.MysteryBoxModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class OnLevelJoined {

    // Subscribe to the PlayerLoggedInEvent
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Play a sound when the player logs in
        Objects.requireNonNull(event.getEntity()).playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);

        // Send a system message to the player
        Objects.requireNonNull(event.getEntity()).sendSystemMessage(Component.nullToEmpty(Objects.requireNonNull(event.getEntity()).getName().getString() + " §aThanks for downloading my mod."));


            // If the player has joined, send a system message
            Objects.requireNonNull(event.getEntity()).sendSystemMessage(Component.nullToEmpty("§aSoon, I plan on adding support for CurseForge mod dependencies, " +
                    "so I can add more items and blocks and extend the functionality of this mod."));
    }
}
