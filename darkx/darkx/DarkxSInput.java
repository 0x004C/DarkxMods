package darkx;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.StepSound;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import cpw.mods.fml.common.registry.LanguageRegistry;

import darkx.darkxsinput.BlockFPSensor;
import darkx.darkxsinput.BlockObsidianPressurePlate;
import darkx.darkxsinput.ItemFPSensor;
import darkx.darkxsinput.TileEntityFPSensor;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.ClientProxy;
import darkx.darkxcore.proxy.CommonProxy;

@Mod(modid="darkxSInput", name="Darkx's Special Inputs", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)
//@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)

public class DarkxSInput {
	
	public StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
	public Block blockObsidianPressurePlate = new BlockObsidianPressurePlate().setHardness(0.5F).setStepSound(soundStoneFootstep).setBlockName("pressurePlate").setRequiresSelfNotify();
	public Block sensor = (new BlockFPSensor()).setHardness(0.5F).setStepSound(soundStoneFootstep).setBlockName("button").setRequiresSelfNotify();
	public Item itemSensor = new ItemFPSensor();
	
	public static String ITEMS_PNG = "/darkx/darkxsinput/items.png";

        // The instance of your mod that Forge uses.
	@Instance("darkxSInput")
	public static DarkxSInput instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,
            serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy = DarkxCore.proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		proxy.addTexture(ITEMS_PNG);
		
		GameRegistry.registerBlock(blockObsidianPressurePlate, "obsidianPressurePlate");
		GameRegistry.registerBlock(sensor, "fpSensor");
		
		GameRegistry.registerTileEntity(TileEntityFPSensor.class, "fpSensor");
		
		GameRegistry.addRecipe(new ItemStack(blockObsidianPressurePlate), "oo", 
				'o', new ItemStack(Block.obsidian));
		GameRegistry.addRecipe(new ItemStack(itemSensor), "i","i", 
				'i', new ItemStack(Block.blockSteel));
		
		LanguageRegistry.addName(itemSensor, "Fingerprint Sensor");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
