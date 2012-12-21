package darkx.darkxauth.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

import darkx.DarkxCore;
import darkx.darkxauth.network.PacketAuthQ;
import darkx.darkxcore.lib.PacketIds;

public class PlayerTracker implements IPlayerTracker {
	

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		DarkxCore.proxy.sendToPlayer(player, new PacketAuthQ(PacketIds.AUTH_Q));	
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
