package darkx;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidStack;
import thermalexpansion.api.core.ItemRegistry;
import thermalexpansion.api.crafting.CraftingManagers;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkx.darkxaddons.ItemDust;
import darkx.darkxcore.lib.ItemInfo;
import darkx.darkxcore.lib.Log;
import darkx.darkxcore.lib.Reference;
import darkx.darkxcore.proxy.CommonProxy;

@Mod(modid="Darkx|DarkxAddons", name="Darkx's Addons", version=Reference.VERSION, dependencies=Reference.DEPENDENCY_CORE)
//@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)

public class DarkxAddons {

        // The instance of your mod that Forge uses.
	@Instance("Darkx|DarkxAddons")
	public static DarkxAddons instance;

	public static CommonProxy proxy = DarkxCore.proxy;
	
	public static ItemInfo infoDullDust = new ItemInfo(1339, "dullDust", null, 0);
	public static ItemInfo infoDullRed = new ItemInfo(1340, "dullRed", null, 0);
	
	public Item dullDust;
	public Item dullRed;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "darkx/addons.cfg"));
		try {
			config.load();
			infoDullDust.id = config.getBlock(infoDullDust.name, infoDullDust.id).getInt(infoDullDust.id);
			infoDullRed.id = config.getBlock(infoDullRed.name, infoDullRed.id).getInt(infoDullRed.id);
		} 
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, "Darkx's Addons couldn't read the config!");
		} 
		finally {
			config.save();
		}
		
		Log.log("Darkx's Addons loaded.");
	}

	@Init
	public void load(FMLInitializationEvent event) {
		dullDust = new ItemDust(infoDullDust.id, infoDullDust);
		dullRed = new ItemDust(infoDullRed.id, infoDullRed);
		
		GameRegistry.registerItem(dullDust, infoDullDust.name);
		GameRegistry.registerItem(dullRed, infoDullRed.name);
		
		LanguageRegistry.addName(dullDust, "Dull Dust");
		LanguageRegistry.addName(dullRed, "Redstone (Dull)");
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 2),
				new ItemStack(dullRed), new ItemStack(Item.lightStoneDust));
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		ItemStack sand = new ItemStack(Block.sand);
		ItemStack redstone = new ItemStack(Item.redstone);
		ItemStack snowball = new ItemStack(Item.snowball);
		ItemStack enderPearl = new ItemStack(Item.enderPearl);
		ItemStack stackDullDust = new ItemStack(dullDust);
		ItemStack stackDullRed = new ItemStack(dullRed);
		ItemStack liquidRedstone = ItemRegistry.getItem("liquidRedstone", 1);
		ItemStack liquidEnder = ItemRegistry.getItem("liquidEnder", 1);
		
		LiquidStack molten = new LiquidStack(liquidRedstone.itemID, 25, liquidRedstone.getItemDamage());
		LiquidStack ender = new LiquidStack(liquidEnder.itemID, 25, liquidEnder.getItemDamage());
		LiquidStack enderFull =  new LiquidStack(liquidEnder.itemID, 250, liquidEnder.getItemDamage());
		
		CraftingManagers.pulverizerManager.addRecipe(40, sand, stackDullDust, false);
		CraftingManagers.transposerManager.addFillRecipe(80, stackDullDust, stackDullRed, molten, false, false);
		CraftingManagers.transposerManager.addFillRecipe(80, stackDullRed, redstone, ender, true, false);
		CraftingManagers.transposerManager.addFillRecipe(80, snowball, enderPearl, enderFull, false, false);
	}
}