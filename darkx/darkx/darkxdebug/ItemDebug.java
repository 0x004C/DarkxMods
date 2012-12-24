package darkx.darkxdebug;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemDebug extends Item {

	public ItemDebug() {
		super(27999);
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabMisc);
        setIconIndex(0);
        setItemName("itemDebug");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!world.isRemote) {
			//player.sendChatToPlayer("World: " + world.getWorldInfo().getWorldName());
			player.sendChatToPlayer("(X, Y, Z): " + x + ", " + y + ", " + z);
			player.sendChatToPlayer("Side: " + side + " (" + ForgeDirection.getOrientation(side) + ")");
			player.sendChatToPlayer("Block (ID, META): " +  world.getBlockId(x, y, z) + ", " + world.getBlockMetadata(x, y, z));
		}
		return false;
    }

}
