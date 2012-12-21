package darkx.darkxcore.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import cpw.mods.fml.common.SidedProxy;
import darkx.darkxcore.network.PacketDarkx;

public class CommonProxy {
	// Client stuff
	public void registerRenderers() {}
	
	public void sendToPlayer(EntityPlayer entityplayer, PacketDarkx packet) {
		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
		player.playerNetServerHandler.sendPacketToPlayer(packet.getPacket());
	}

	public void sendToServer(Packet packet) {}
}
