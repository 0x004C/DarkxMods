package darkx.darkxlock.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkx.darkxcore.lib.Defaults;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ModItems {
	
	public static Item lockIron;
	public static Item keyIron;

	public static void init() {
		lockIron = new ItemLockIron(Defaults.IRON_LOCK_ID);
		keyIron = new ItemKeyIron(Defaults.IRON_KEY_ID);
		
		LanguageRegistry.addName(lockIron, "Iron Lock");
		LanguageRegistry.addName(keyIron, "Iron Key");
		
		initRecipes();
	}
	
	public static void initRecipes() {
		GameRegistry.addRecipe(new ItemStack(lockIron), 
                new Object[] {"ii","ii", 
            Character.valueOf('i'), Item.ingotIron
        });
		GameRegistry.addRecipe(new ItemStack(keyIron), 
                new Object[] {"iii",
            Character.valueOf('i'), Item.ingotIron
        });
	}
}
