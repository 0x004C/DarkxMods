package darkx.darkxcore.proxy;

import net.minecraft.network.packet.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {}
	
	@Override
	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getSendQueue().addToSendQueue(packet);
	}
	
	public void addTexture(String texture) {
		MinecraftForgeClient.preloadTexture(texture);
	}
	
}
