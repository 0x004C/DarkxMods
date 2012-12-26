package darkx;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.darkxcore.lib.BlockInfo;
import darkx.darkxcore.lib.ItemInfo;
import darkx.darkxcore.lib.Log;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;
import darkx.darkxsinput.BlockFPSensor;
import darkx.darkxsinput.BlockObsidianPressurePlate;
import darkx.darkxsinput.ItemFPSensor;
import darkx.darkxsinput.RendererFPSensor;
import darkx.darkxsinput.TileEntityFPSensor;

@Mod(modid="Darkx|DarkxSInput", name="Darkx's Special Inputs", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)

public class DarkxSInput {
	
	public BlockInfo infoPlate = new BlockInfo(1337, "obsidianPressurePlate", null, 0);
	public BlockInfo infoSensor = new BlockInfo(1338, "fingerPrintSensor", "/resources/darkx/darkxsinput/fpsensorblock.png", 0);
	public ItemInfo infoItemSensor = new ItemInfo(28002, "fingerPrintSensorItem", "/resources/darkx/darkxsinput/fpsensoritem.png", 0);

	public Block obsidianPPlate;
	public Block sensor;
	public Item itemSensor;

        // The instance of your mod that Forge uses.
	@Instance("Darkx|DarkxSInput")
	public static DarkxSInput instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,
            serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy = DarkxCore.proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "darkx/special-inputs.cfg"));
		try {
			config.load();
			infoPlate.id = config.getBlock(infoPlate.name, infoPlate.id).getInt(infoPlate.id);
			infoSensor.id = config.getBlock(infoSensor.name, infoSensor.id).getInt(infoSensor.id);
			infoItemSensor.id = config.getItem(infoItemSensor.name, infoItemSensor.id).getInt(infoItemSensor.id);
		} 
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, "Darkx's Special Inputs couldn't read the config!");
		} 
		finally {
			config.save();
		}
		
		Log.log("Darkx's Special Inputs loaded.");
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		initClient();
		
		// FingerPrint Sensor
		itemSensor = new ItemFPSensor();
		
		// FingerPrint Sensor ---
		sensor = new BlockFPSensor();
		GameRegistry.registerBlock(sensor, infoSensor.name);
		GameRegistry.registerTileEntity(TileEntityFPSensor.class, "fpSensor");
		GameRegistry.addRecipe(new ItemStack(itemSensor), "r","i", 
				'r', new ItemStack(Item.redstone),
				'i', new ItemStack(Item.ingotIron));
		
		// Obsidian Pressure Plate
		obsidianPPlate = new BlockObsidianPressurePlate();
		GameRegistry.registerBlock(obsidianPPlate, infoPlate.name);
		GameRegistry.addRecipe(new ItemStack(obsidianPPlate), "oo", 
				'o', new ItemStack(Block.obsidian));
		
		// LanguageRegistry
		LanguageRegistry.addName(itemSensor, "Fingerprint Sensor");
	}
	
	//@SideOnly(Side.CLIENT)
	private void initClient() {
		infoSensor.renderId = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFPSensor.class, new RendererFPSensor());
		
		proxy.addTexture(infoSensor.texture.location);
		proxy.addTexture(infoItemSensor.texture.location);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
