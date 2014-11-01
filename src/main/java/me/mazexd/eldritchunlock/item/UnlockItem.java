package me.mazexd.eldritchunlock.item;

import me.mazexd.eldritchunlock.EldritchUnlock;
import me.mazexd.eldritchunlock.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UnlockItem extends Item {

    public UnlockItem() {
        setMaxStackSize(1);
        setMaxDamage(0);
        setUnlocalizedName("eldritchUnlocker");
        setTextureName(ModInfo.ID + ":eldritchUnlocker");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote && EldritchUnlock.unlockEldritch((EntityPlayerMP) player)) {
            itemStack.stackSize -= 1;
        }

        return itemStack;
    }
}
