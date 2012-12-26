package darkx.darkxsinput;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkx.DarkxSInput;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

public class RendererFPSensor extends TileEntitySpecialRenderer {
	private ModelBase model = new ModelBase() {};
	
    private ModelRenderer edgeBottom;
    private ModelRenderer edgeTop;
    private ModelRenderer panel;
    
    private float scale = 1F / 2 / 16;

    public RendererFPSensor() {
    	model.textureHeight = 64;
        model.textureWidth = 64;
    	
    	edgeBottom = new ModelRenderer(model, 0, 10);
    	edgeBottom.addBox(-3F, 15F, 14F, 6, 1, 2);
        edgeBottom.setRotationPoint(16F, 0F, 16F);
        edgeBottom.setTextureSize(64, 64);
        edgeBottom.mirror = true;
    	
    	edgeTop = new ModelRenderer(model, 0, 10);
    	edgeTop.addBox(-3F, 24F, 14F, 6, 1, 2);
        edgeTop.setRotationPoint(16F, 0F, 16F);
        edgeTop.setTextureSize(64, 64);
        edgeTop.mirror = true;
    	
    	panel = new ModelRenderer(model, 0, 0);
    	panel.addBox(-3F, 16F, 14F, 6, 8, 1);
        panel.setRotationPoint(16F, 0F, 16F);
        panel.setTextureSize(64, 64);
        panel.mirror = true;
    	
    }
    
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float var8) {
		TileEntityFPSensor sensor = (TileEntityFPSensor) entity;
		int meta = entity.getBlockMetadata();
		render(meta, x, y, z);
	}

	@SideOnly(Side.CLIENT) // Just to make sure
	private void render(int meta, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		
		float r = 0;
		if (meta % 4 == 0) {
			r = 0;
		} else if (meta % 4 == 1) {
			r = (float)(Math.PI);
		} else if (meta % 4 == 2) {
			r = (float)(Math.PI / 2);
		} else if (meta % 4 == 3) {
			r = (float)(3 * Math.PI / 2);
		}
		 
		
		// Edges
		String texture;
		if (meta > 3)
			texture = "/resources/darkx/darkxsinput/fpsensoron.png";
		else
			texture = "/resources/darkx/darkxsinput/fpsensoroff.png";
		
		ForgeHooksClient.bindTexture(texture, 0);
		setRotation(edgeBottom, 0F, r, 0F);
		setRotation(edgeTop, 0F, r, 0F);
		setRotation(panel, 0F, r, 0F);
		edgeBottom.render(scale);
		edgeTop.render(scale);
		panel.render(scale);
		
		GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
		return;
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
}
