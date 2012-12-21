package darkx.darkxcore.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import darkx.darkxcore.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {}
	
	@Override
	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
	}
	
}
