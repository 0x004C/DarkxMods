package darkx.darkxcore.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import darkx.darkxcore.network.PacketDarkx;

public class CommonProxy {
	// Client stuff
	public void registerRenderers() {}
	
	public void sendToPlayer(EntityPlayer entityplayer, PacketDarkx packet) {
		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
		player.playerNetServerHandler.sendPacketToPlayer(packet.getPacket());
	}
	
	public void sendMessageToPlayer(EntityPlayer entityplayer, String msg) {
		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
		player.sendChatToPlayer(msg);
	}

	public void sendToServer(Packet packet) {}

	public void addTexture(String string) {}
}
