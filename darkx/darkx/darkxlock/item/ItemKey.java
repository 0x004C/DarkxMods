package darkx.darkxlock.item;

import darkx.darkxcore.lib.Reference;
import net.minecraft.item.Item;

public class ItemKey extends Item {

	public ItemKey(int id) {
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 1;
		setNoRepair();
	}

}
