package darkx.darkxsinput;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import darkx.DarkxSInput;

public class BlockObsidianPressurePlate extends BlockPressurePlate {

	public BlockObsidianPressurePlate() {
		super(DarkxSInput.instance.infoPlate.id, obsidian.blockIndexInTexture, EnumMobType.players, Material.rock);
		this.setHardness(5.0F);
		this.stepSound = new StepSound("stone", 1.0F, 1.0F);
		this.setBlockName("pressurePlate");
		this.setRequiresSelfNotify();
	}
}
