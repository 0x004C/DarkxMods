package darkx;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.darkxauth.handlers.PlayerTracker;
import darkx.darkxcore.lib.Log;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;

@Mod(modid="Darkx|DarkxAuth", name="DarkxAuth", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = darkx.darkxauth.handlers.PacketHandler.class)

public class DarkxAuth {

        // The instance of your mod that Forge uses.
	@Instance("Darkx|DarkxAuth")
	public static DarkxAuth instance;
	
	// Says where the client and server 'proxy' code is loaded.
	public static CommonProxy proxy = DarkxCore.proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Log.log("DarkxAuth loaded.");
	}
	
	@SideOnly(Side.SERVER)
	@Init
	public void load(FMLInitializationEvent event) {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
