package com.ryutheghost.themysterybox.util;

import com.ryutheghost.themysterybox.block.registry.MysteryBoxModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnLevelJoined {

    // Subscribe to the PlayerLoggedInEvent
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Play a sound when the player logs in
        event.getEntity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);

        // Send a system message to the player
        event.getEntity().sendSystemMessage(Component.nullToEmpty(event.getEntity().getName().getString() + " §aThanks for downloading my mod."));

        // Send a system message with update information to the player
        event.getEntity().sendSystemMessage(Component.nullToEmpty("§aIf you'd like to get notified when an update is posted visit:" +
                "\n§bhttps://www.youtube.com/@ryutheghost/about\n§aSubscribe for more updates."));

        // Check if the player has the Mystery Box in their inventory
        if(event.getEntity().getInventory().contains(new ItemStack(MysteryBoxModBlocks.MYSTERY_BOX_BLOCK.get()))) {
            // If the player has the Mystery Box, send a system message
            event.getEntity().sendSystemMessage(Component.nullToEmpty("§aSoon, I plan on adding support for CurseForge mod dependencies, " +
                    "so I can add more items and blocks and extend the functionality of this mod."));
        } else {
            // If the player doesn't have the Mystery Box, send a system message and give it to the player
            event.getEntity().sendSystemMessage(Component.nullToEmpty("§aHave a Mystery Box on the house."));
            event.getEntity().getInventory().add(new ItemStack(MysteryBoxModBlocks.MYSTERY_BOX_BLOCK.get()));
        }
    }
}
