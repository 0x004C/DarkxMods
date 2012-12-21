package darkx.darkxsinput;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.darkxcore.lib.Defaults;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFPSensor extends BlockContainer {

	public BlockFPSensor() {
		super(Defaults.FINGER_PRINT_SENSOR_ID, blockSteel.blockIndexInTexture, Material.circuits);
	}

    public TileEntity createNewTileEntity(World par1World)
    {
    	return new TileEntityFPSensor();
    }   
	
	@Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
		TileEntity te = world.getBlockTileEntity(i, j, k);
        if (te != null && te instanceof TileEntityFPSensor)
        {
            ((TileEntityFPSensor) te).setPrint(entityliving);
            world.markBlockForUpdate(i, j, k);
        }
    }
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(!par1World.isRemote)
        {
			TileEntityFPSensor te = (TileEntityFPSensor) par1World.getBlockTileEntity(par2, par3, par4);
			if (te.getPrint(par5EntityPlayer, par1World)) {
				return true;
			} else
				return false;
        } else 
        	return false;
	}
}
