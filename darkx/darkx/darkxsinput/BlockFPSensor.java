package darkx.darkxsinput;

import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.DarkxSInput;
import darkx.darkxcore.lib.Defaults;
import darkx.darkxcore.lib.Point;
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
		super.breakBlock(world, x, y, z, par5, par6);
		NotifyNeightbours(world, x, y, z);
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
    {
        if (!world.isRemote)
        {
        	int meta = world.getBlockMetadata(x, y, z);
        	Point point = getBlockAttachedTo(world, x, y, z);
        	if (world.isAirBlock(point.x, point.y, point.z)) {
        		this.dropBlockAsItem(world, x, y, z, meta, 0);
                world.setBlockWithNotify(x, y, z, 0);
                world.removeBlockTileEntity(x, y, z);
        		NotifyNeightbours(world, x, y, z);
        	}
            super.onNeighborBlockChange(world, x, y, z, par5);
        }
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
		int meta = iblockaccess.getBlockMetadata(x, y, z);
		if (meta > 4)
			return true; 
		else
			return false;
    }

    public TileEntity createNewTileEntity(World par1World)
    {
    	return new TileEntityFPSensor();
    }   
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(!par1World.isRemote)
        {
			TileEntityFPSensor te = (TileEntityFPSensor) par1World.getBlockTileEntity(par2, par3, par4);
			if (te.getPrint(par5EntityPlayer, par1World)) {
				
				int meta = par1World.getBlockMetadata(par2, par3, par4);
				if (meta < 4) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 4);
					//par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4); TODO: Add a nice renderer
					par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.6F);
					par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate());
				}
				return true;
			} else
				return false;
        } else 
        	return false;
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	int meta = par1World.getBlockMetadata(par2, par3, par4);
        	if (meta > 4)
        	{
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 4);
        		par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F);
                //par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4); TODO: Render here too
        	}
        }
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
		
		if (side == 0)
			this.setBlockBounds(offW, minH, 1.0F - offD, 1.0F - offW, maxH, 1.0F);
		else if (side == 1)
			this.setBlockBounds(offW, minH, 0.0F, 1.0F - offW, maxH, offD);
		else if (side == 2)
			this.setBlockBounds(1.0F - offD, minH, offW, 1.0F, maxH, 1.0F - offW);
		else if (side == 3)
			this.setBlockBounds(0.0F, minH, offW, offD, maxH, 1.0F - offW);
		
    }
	
	private Point getBlockAttachedTo(World world, int x, int y, int z) {
		ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) + 2);
		dir = dir.getOpposite();
		if (dir == ForgeDirection.NORTH)
			--z;
		if (dir == ForgeDirection.SOUTH)
			++z;
		if (dir == ForgeDirection.WEST)
			--x;
		if (dir == ForgeDirection.EAST)
			++x;
		Point point = new Point(x, y, z);
		System.out.println(x + "," + y + "," + z);
		return point;
	}
	
	private void NotifyNeightbours(World world, int x, int y, int z) {
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		Point point = new Point(x, y, z);
		Object[] nbs = point.getNeightbors();
		for (int i = 0; i < nbs.length; ++i)
		{
			Point nb = (Point)nbs[i];
			world.notifyBlocksOfNeighborChange(nb.x, nb.y, nb.z, this.blockID);
		}
	}
}
