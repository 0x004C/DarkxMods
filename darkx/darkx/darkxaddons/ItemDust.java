package darkx.darkxaddons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import darkx.darkxcore.lib.ItemInfo;

public class ItemDust extends Item {
	
	public static ItemInfo info;

	public ItemDust(int id, ItemInfo info) {
		super(id);
		this.info = info;
		this.setItemName(info.name);
		setCreativeTab(CreativeTabs.tabMisc);
        setIconIndex(0);
        maxStackSize = 64;
		// TODO Auto-generated constructor stub
	}

}
