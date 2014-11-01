package me.mazexd.eldritchunlock.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class PlayerUtil {

    public static void sendMessage(EntityPlayerMP player, String message) {
        ChatComponentText component = new ChatComponentText(message);
        component.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);

        player.addChatMessage(component);
    }

    public static void playSound(EntityPlayerMP player, String soundName) {
        player.getEntityWorld().playSoundAtEntity(player, soundName, 0.75f, 1.0f);
    }
}
