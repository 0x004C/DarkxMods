package darkx.darkxauth.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import darkx.darkxcore.network.PacketDarkx;


public class PacketAuthA extends PacketDarkx {

	private int packetId;
	public String auth;
	
	public PacketAuthA() {}
	
	public PacketAuthA(int packetId, String auth) {
		this(packetId);
		this.auth = auth;
	}
	
	public PacketAuthA(int packetId) {
		this.packetId = packetId;
		this.isChunkDataPacket = false;
	}
	
	@Override
	public int getID() {
		return packetId;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.auth = data.readUTF();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeUTF(auth);
	}
	
	@SideOnly(Side.SERVER)
	@Override
	public void execute(INetworkManager network, Player player)
	{
		if (!auth.contains("AsylumCertified"))
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;
			thePlayer.playerNetServerHandler.kickPlayerFromServer("Et ole kirjautunut Asylum-launcherilla");
		}
	}

}
