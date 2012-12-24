package darkx.darkxsinput;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import darkx.DarkxSInput;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	public BlockObsidianPressurePlate() {
		super(DarkxSInput.instance.infoPlate.id, obsidian.blockIndexInTexture, EnumMobType.players, Material.rock);
	}
}
