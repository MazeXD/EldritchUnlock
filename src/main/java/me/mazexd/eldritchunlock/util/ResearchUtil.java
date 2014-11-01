package me.mazexd.eldritchunlock.util;

import net.minecraft.entity.player.EntityPlayerMP;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;

public class ResearchUtil {

    private static ResearchManager getResearchManager() {
        return Thaumcraft.proxy.getResearchManager();
    }

    public static boolean completeResearch(EntityPlayerMP player, String research) {
        if (!ResearchManager.isResearchComplete(player.getCommandSenderName(), research)) {
            PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(research), player);
            getResearchManager().completeResearch(player, research);
            return true;
        }

        return false;
    }
}
