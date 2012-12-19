package darkx.darkxlock.item;

import darkx.darkxcore.lib.Reference;
import net.minecraft.src.Item;

public class ItemLock extends Item {

	public ItemLock(int id) {
		super(id- Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 1;
		setNoRepair();
	}

}
