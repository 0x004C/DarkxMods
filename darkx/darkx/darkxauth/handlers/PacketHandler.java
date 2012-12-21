package darkx.darkxauth.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import darkx.darkxauth.network.PacketAuthA;
import darkx.darkxauth.network.PacketAuthQ;
import darkx.darkxcore.lib.PacketIds;

public class PacketHandler implements IPacketHandler {

	public PacketHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		try {
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.AUTH_Q:
				System.out.println("AUTH_Q");
				PacketAuthQ packetAuthQ = new PacketAuthQ();
				packetAuthQ.execute(manager, player);
				break;
			case PacketIds.AUTH_A:
				System.out.println("AUTH_A");
				PacketAuthA packetAuthA = new PacketAuthA();
				packetAuthA.readData(data);
				packetAuthA.execute(manager, player);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
