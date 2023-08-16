package com.ryutheghost.themysterybox.util;

import com.ryutheghost.themysterybox.block.registry.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Predicate;

public class OnLevelJoined {
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f, 1f);
        event.getEntity().sendSystemMessage(Component.nullToEmpty(event.getEntity().getName().getString() + " §aThanks for downloading my mod."));
        event.getEntity().sendSystemMessage(Component.nullToEmpty("§aIf you'd like to get notified when a update is posted visit:" + "\n§bhttps://www.youtube.com/@ryutheghost/about\n§aSubscribe for more updates."));
        if(event.getEntity().getInventory().contains(new ItemStack(ModBlocks.MYSTERY_BOX_BLOCK.get()))){
            event.getEntity().sendSystemMessage(Component.nullToEmpty("§aSoon I plan only adding support for curseforge mod dependencies so I can add as many items or blocks and more to the functionality of this mod."));
        }else{
            event.getEntity().sendSystemMessage(Component.nullToEmpty("§aHave a Mystery Box on the house."));
            event.getEntity().getInventory().add(new ItemStack(ModBlocks.MYSTERY_BOX_BLOCK.get()));
        }
    }
}
