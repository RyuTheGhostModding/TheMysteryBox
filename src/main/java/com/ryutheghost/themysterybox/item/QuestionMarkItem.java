package com.ryutheghost.themysterybox.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuestionMarkItem extends Item {

    public QuestionMarkItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        // Check if the shift key is pressed
        if(Screen.hasShiftDown()){
            // If shift key is pressed, add a translatable component indicating it
            pTooltipComponents.add(Component.translatable("questionmark_shift_key_down"));
        }else{
            // If shift key is not pressed, add a translatable component indicating it
            pTooltipComponents.add(Component.translatable("questionmark_shift_key_not_down"));
        }
        // Call the super method to append any additional tooltip text
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
