package darkx;

import java.util.logging.Level;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.ClientProxy;
import darkx.darkxcore.proxy.CommonProxy;


@Mod(modid = "darkxCore", name = "DarkxCore", version = Reference.VERSION)
//@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = true)
public class DarkxCore {

	    // The instance of your mod that Forge uses.
		@Instance("darkxCore")
	    public static DarkxCore instance;

	    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	    public static CommonProxy proxy;

		public static ClientProxy clientProxy;
			
		@PreInit
		public void preInit(FMLPreInitializationEvent event) {
			// Stub Method
		}
		
		@Init
		public void load(FMLInitializationEvent event) {
			proxy.registerRenderers();		
		}
		
		@PostInit
		public void postInit(FMLPostInitializationEvent event) {
			// Stub Method
		}
}
