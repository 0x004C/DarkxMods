package darkx.darkxcore.proxy;

import cpw.mods.fml.common.SidedProxy;
import darkx.darkxcore.network.PacketDarkx;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet;

public class CommonProxy {
	// Client stuff
	public void registerRenderers() {}
	
	public void sendToPlayer(EntityPlayer entityplayer, PacketDarkx packet) {
		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
		player.playerNetServerHandler.sendPacketToPlayer(packet.getPacket());
	}

	public void sendToServer(Packet packet) {}
}
