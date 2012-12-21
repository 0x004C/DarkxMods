package darkx;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.StepSound;
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

import darkx.darkxpplate.BlockObsidianPressurePlate;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;

@Mod(modid="darkxPPlate", name="Darkx's Obsidian Pressure Plate", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)

public class DarkxPPlate {

        // The instance of your mod that Forge uses.
	@Instance("darkxPPlate")
	public static DarkxPPlate instance;
	
	public static CommonProxy proxy = DarkxCore.proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
		Block blockObsidianPressurePlate = new BlockObsidianPressurePlate().setHardness(0.5F).setStepSound(soundStoneFootstep).setBlockName("pressurePlate").setRequiresSelfNotify();
		GameRegistry.registerBlock(blockObsidianPressurePlate);
		GameRegistry.addRecipe(new ItemStack(blockObsidianPressurePlate), "oo", 
				'o', new ItemStack(Block.obsidian));
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
