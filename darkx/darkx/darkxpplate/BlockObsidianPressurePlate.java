package darkx.darkxpplate;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import darkx.darkxcore.lib.Defaults;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	public BlockObsidianPressurePlate() {
		super(Defaults.OBSIDIAN_PRESSURE_PLATE_ID, obsidian.blockIndexInTexture, EnumMobType.players, Material.rock);
	}
}
