package darkx.darkxsinput;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import darkx.DarkxSInput;
import darkx.darkxcore.lib.BlockInfo;
import darkx.darkxcore.lib.Point;

public class BlockFPSensor extends BlockContainer {
	
	public static BlockInfo info;

	public BlockFPSensor() {
		super(DarkxSInput.instance.infoSensor.id, Material.circuits);
		this.info = DarkxSInput.instance.infoSensor;
		this.setHardness(0.5F);
		this.setBlockName(info.name);
		this.setTickRandomly(true);
	}
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
    	return new TileEntityFPSensor();
    }  
	
	@Override
	public boolean renderAsNormalBlock()
	{
	         return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	         return false;
	}
	
	@Override
    public int getRenderType() {
		return DarkxSInput.instance.infoSensor.renderId;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return DarkxSInput.instance.itemSensor.shiftedIndex;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
		super.breakBlock(world, x, y, z, par5, par6);
		NotifyNeightbours(world, x, y, z);
    }
	
	@Override
	public int tickRate()
	{
	         return 30;
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
		TileEntityFPSensor te = (TileEntityFPSensor) iblockaccess.getBlockTileEntity(x, y, z);
		if (te.isPowered)
			return true;
		else
			return false;
    } 
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (!par1World.isRemote)
        {
			TileEntityFPSensor te = (TileEntityFPSensor) par1World.getBlockTileEntity(par2, par3, par4);
			if (te.getPrint(par5EntityPlayer, par1World)) {
				if (!te.isPowered) {
					te.isPowered = true;
					NotifyNeightbours(par1World, par2, par3, par4);
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
        	TileEntityFPSensor te = (TileEntityFPSensor) par1World.getBlockTileEntity(par2, par3, par4);
        	if (te.isPowered)
        	{
        		te.isPowered = false;
        		NotifyNeightbours(par1World, par2, par3, par4);
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
		float minW = 1F;
		float maxW;
		float maxD;
		float offW = 1F - ((3F / 2F / 16F) / 2);
		float offD = 2F / 2F / 16F;
		
		minH = minH / 2 / 16;
		maxH = maxH / 2 / 16;
		minW = minW / 2 / 16;
		maxW = minW / 2 / 16;
		
		if (side % 6 == 2) // NORTH
			this.setBlockBounds(minW, minH, 1.0F - offD, maxW, maxH, 1.0F);
		else if (side % 6 == 3) // SOUTH
			this.setBlockBounds(minW, minH, 0.0F, maxW, maxH, offD);
		else if (side % 6 == 4) // WEST
			this.setBlockBounds(1.0F - offD, minH, minW, 1.0F, maxH, maxW);
		else if (side % 6 == 5) // EAST
			this.setBlockBounds(0.0F, minH, minW, offD, maxH, maxW);
		else
			return;
	
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}
	
	private Point getBlockAttachedTo(World world, int x, int y, int z) {
		ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
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
