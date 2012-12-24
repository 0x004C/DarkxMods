package darkx;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkx.darkxcore.lib.Log;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;
import darkx.darkxdebug.ItemDebug;

@Mod(modid="Darkx|DarkxDebug", name="DEBUG", version="1.0.0", dependencies=Reference.DEPENDENCY_CORE)
//@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)

public class DarkxDebug {
	
	public Item itemDebug = new ItemDebug();

        // The instance of your mod that Forge uses.
	@Instance("Darkx|DarkxDebug")
	public static DarkxDebug instance;
	
	public static CommonProxy proxy = DarkxCore.proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Log.log("DarkxDebug loaded.");
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		LanguageRegistry.addName(itemDebug, "Debug Item");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
