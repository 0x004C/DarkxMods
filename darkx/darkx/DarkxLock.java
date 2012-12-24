package darkx;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkx.darkxcore.lib.Log;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;

@Mod(modid="Darkx|DarkxLock", name="Darkx's Lock 'n Key", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)
//@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)

public class DarkxLock {

        // The instance of your mod that Forge uses.
	@Instance("Darkx|DarkxLock")
	public static DarkxLock instance;
	
	public static CommonProxy proxy = DarkxCore.proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Log.log("Darkx's Lock 'n Key loaded.");
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		//ModItems.init();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
