package darkx.darkxsinput;

import darkx.DarkxSInput;
import darkx.darkxcore.lib.Defaults;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemFPSensor extends Item {

	public ItemFPSensor() {
		super(Defaults.ITEM_FINGER_PRINT_SENSOR_ID);
		maxStackSize = 16;
		setCreativeTab(CreativeTabs.tabRedstone);
        setIconIndex(0);
        setItemName("itemFBSensor");
	}
	
	public String getTextureFile () {
        return DarkxSInput.FPSENSOR_PNG;
}
	
	@Override //TODO: check if block has GUI to be opened, and apply crouch placing :)
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {		
		if (side == 0 | side == 1)
            return false;
        if (side == 2)
            --z;
        if (side == 3)
            ++z;
        if (side == 4)
            --x;
        if (side == 5)
            ++x;
        
        if (!world.isAirBlock(x, y, z))
        	return false;
        
		if (DarkxSInput.instance.sensor.canPlaceBlockAt(world, x, y, z))
        {
            --stack.stackSize;
            world.setBlockAndMetadataWithNotify(x, y, z, Defaults.FINGER_PRINT_SENSOR_ID, side - 2);
    		TileEntity te = world.getBlockTileEntity(x, y, z);
            if (te != null && te instanceof TileEntityFPSensor)
            {
                ((TileEntityFPSensor) te).setPrint(player);
                world.markBlockForUpdate(x, y, z);
            }
            return true;
        } else
        	return false;
    }

}
