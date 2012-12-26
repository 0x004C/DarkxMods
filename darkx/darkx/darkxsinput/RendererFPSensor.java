package darkx.darkxsinput;

import org.lwjgl.opengl.GL11;

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
		render(sensor.getBlockMetadata(), sensor.isPowered, x, y, z);
	}

	private void render(int side, boolean isPowered, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		
		float r = 0;
		if (side == 2) {
			r = 0;
		} else if (side == 3) {
			r = (float)(Math.PI);
		} else if (side == 4) {
			r = (float)(Math.PI / 2);
		} else if (side == 5) {
			r = (float)(3 * Math.PI / 2);
		}
		 
		
		// Edges
		ForgeHooksClient.bindTexture("/resources/darkx/darkxsinput/fpsensor.png", 0);
		setRotation(edgeBottom, 0F, r, 0F);
		setRotation(edgeTop, 0F, r, 0F);
		edgeBottom.render(scale);
		edgeTop.render(scale);
		
		// Panel
		ForgeHooksClient.bindTexture("/resources/darkx/darkxsinput/fpsensor.png", 0);
		setRotation(panel, 0F, r, 0F);
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
