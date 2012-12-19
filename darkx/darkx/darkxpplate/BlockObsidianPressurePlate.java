package darkx.darkxpplate;

import darkx.darkxcore.lib.Defaults;
import net.minecraft.src.BlockPressurePlate;
import net.minecraft.src.EnumMobType;
import net.minecraft.src.Material;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	public BlockObsidianPressurePlate() {
		super(Defaults.OBSIDIAN_PRESSURE_PLATE_ID, obsidian.blockIndexInTexture, EnumMobType.players, Material.rock);
	}
}
