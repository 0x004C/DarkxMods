package darkx.darkxauth.handlers;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.IPlayerTracker;
import darkx.DarkxCore;
import darkx.darkxauth.network.PacketAuthQ;
import darkx.darkxcore.lib.PacketIds;

public class PlayerTracker implements IPlayerTracker {
	

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if (FMLClientHandler.instance().getServer().isDedicatedServer())
			DarkxCore.proxy.sendToPlayer(player, new PacketAuthQ(PacketIds.AUTH_Q));	
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {	
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}

}
