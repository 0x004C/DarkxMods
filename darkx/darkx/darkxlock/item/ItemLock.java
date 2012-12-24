package darkx.darkxlock.item;

import net.minecraft.item.Item;
import darkx.darkxcore.lib.Reference;

public class ItemLock extends Item {

	public ItemLock(int id) {
		super(id- Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 1;
		setNoRepair();
	}

}
