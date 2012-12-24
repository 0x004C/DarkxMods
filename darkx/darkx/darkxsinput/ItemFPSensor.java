package darkx.darkxsinput;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkx.DarkxSInput;
import darkx.darkxcore.lib.ItemInfo;

public class ItemFPSensor extends Item {
	
	public static ItemInfo info;

	public ItemFPSensor() {
		super(DarkxSInput.instance.infoItemSensor.id);
		this.info = DarkxSInput.instance.infoItemSensor;
		maxStackSize = 16;
		setCreativeTab(CreativeTabs.tabRedstone);
        setIconIndex(0);
        setItemName(info.name);
	}
	
	public String getTextureFile () {
        return DarkxSInput.instance.infoItemSensor.texture.location;
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
            world.setBlockAndMetadataWithNotify(x, y, z, DarkxSInput.instance.infoSensor.id, side - 2);
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
