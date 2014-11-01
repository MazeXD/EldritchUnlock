package me.mazexd.eldritchunlock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import me.mazexd.eldritchunlock.command.UnlockCommand;
import me.mazexd.eldritchunlock.util.PlayerUtil;
import me.mazexd.eldritchunlock.util.ResearchUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import thaumcraft.common.config.Config;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class EldritchUnlock {

    public static final String[] ELDRITCH_RESEARCH = {"ELDRITCHMINOR", "ELDRITCHMAJOR"};

    public static UnlockMode MODE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
        config.load();

        Property property = config.get(Configuration.CATEGORY_GENERAL, "unlock-mode", UnlockMode.COMMAND.ordinal());
        property.comment = "Select how to unlock the eldritch tab:\n" +
                "   - [0] Command (Only server requires mod)\n" +
                "   - [1] Item (Server And Client requires mod)";

        int temp = property.getInt();
        if (temp < 0 || temp >= UnlockMode.values().length) {
            MODE = UnlockMode.COMMAND;
        } else {
            MODE = UnlockMode.values()[temp];
        }

        config.save();

        if (MODE != UnlockMode.ITEM || !Config.wuss) return;

        // TODO: Register item
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent e) {
        if (MODE != UnlockMode.COMMAND || !Config.wuss) return;

        e.registerServerCommand(new UnlockCommand());
    }

    public static boolean unlockEldritch(EntityPlayerMP player) {
        boolean unlocked = false;

        for (String research : ELDRITCH_RESEARCH) {
            unlocked = ResearchUtil.completeResearch(player, research) || unlocked;
        }

        if (unlocked) {
            PlayerUtil.playSound(player, "thaumcraft:learn");
            PlayerUtil.sendMessage(player, "~~ You have unlocked the eldritch tab ~~");
            return true;
        }

        PlayerUtil.sendMessage(player, "You have already unlocked the eldritch tab!");
        return false;
    }
}
