package darkx.darkxsinput;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityFPSensor extends TileEntity {
	
	String owner = "";

	public boolean getPrint(EntityPlayer par5EntityPlayer, World world) {
		//world.notifyBlockChange(xCoord, yCoord, zCoord, 2);
		if(owner.equals(par5EntityPlayer.getEntityName()))
        {
			return true;
        }
		else
		{
			return false;
		}
	}

	public void setPrint(EntityLiving entityliving) {
		EntityPlayer entityPlayer = (EntityPlayer) entityliving;
		this.owner = entityPlayer.getEntityName();
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    this.owner = nbt.getString("owner");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);
	    nbt.setString("owner", owner);
	}

}
