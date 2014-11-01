package me.mazexd.eldritchunlock.command;

import me.mazexd.eldritchunlock.EldritchUnlock;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class UnlockCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "eldritchunlock";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Use /eldritchunlock to unlock the eldritch tab";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) sender;
        EldritchUnlock.unlockEldritch(player);
    }
}
