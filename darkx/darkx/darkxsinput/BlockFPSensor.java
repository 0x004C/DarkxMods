package darkx.darkxsinput;

import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.DarkxSInput;
import darkx.darkxcore.lib.Defaults;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFPSensor extends BlockContainer {

	public BlockFPSensor() {
		super(Defaults.FINGER_PRINT_SENSOR_ID, blockSteel.blockIndexInTexture, Material.circuits);
		this.setTickRandomly(true);
		//this.setBlockBounds(0.0F, 0.4F, 0.375F, 0.0165F, 0.8F, 1.0F - 0.375F);
	}
	
	@Override
	public int tickRate()
	{
	         return 20;
	}
	@Override
	public boolean isOpaqueCube()
	{
	         return false;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
	         return false;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return DarkxSInput.instance.itemSensor.shiftedIndex;
    }
	
	// TODO: Check if the block attached to is still there
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
    {
        super.onNeighborBlockChange(world, x, y, z, par5);
    }
	
	@Override
	public boolean canProvidePower()
	{
	         return true;
	}
	
	@Override
    public boolean isProvidingWeakPower(IBlockAccess iblockaccess, int x, int y, int z, int direction)
    {
        return isProvidingStrongPower(iblockaccess, x, y, z, direction);
    }
	
	@Override
    public boolean isProvidingStrongPower(IBlockAccess iblockaccess, int x, int y, int z, int direction)
    {
		return true;
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
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        for (int face = 0; face < 6; face++){
                int side = Facing.faceToSide[face];
                if(world.isBlockSolidOnSide(x + Facing.offsetsXForSide[side], y + Facing.offsetsYForSide[side],  z + Facing.offsetsZForSide[side], ForgeDirection.getOrientation(face)))
                        return true;
        }
        return false;
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
		int side = par1IBlockAccess.getBlockMetadata(x, y, z);
		float minH = 0.4F;
		float maxH = 0.8F;
		float offW = 0.375F;
		float offD = 0.0165F;
		
		if (side == 2)
			this.setBlockBounds(offW, minH, 1.0F - offD, 1.0F - offW, maxH, 1.0F);
		else if (side == 3)
			this.setBlockBounds(offW, minH, 0.0F, 1.0F - offW, maxH, offD);
		else if (side == 4)
			this.setBlockBounds(1.0F - offD, minH, offW, 1.0F, maxH, 1.0F - offW); // Ei mihinkään
		else if (side == 5)
			this.setBlockBounds(0.0F, minH, offW, offD, maxH, 1.0F - offW); // Oikein
    }
}
