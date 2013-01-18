package darkx.darkxauth.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.integrated.IntegratedServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import darkx.DarkxCore;
import darkx.darkxauth.network.PacketAuthQ;
import darkx.darkxcore.lib.PacketIds;

public class PlayerTracker implements IPlayerTracker {
	
	@SideOnly(Side.SERVER)
	@Override
	public void onPlayerLogin(EntityPlayer player) {
			if (FMLServerHandler.instance().getServer().isDedicatedServer())
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
