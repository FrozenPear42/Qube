package com.bugfullabs.qube.hud;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;

import com.bugfullabs.qube.util.SpriteButton;



public class ItemsHUD extends HUD{
	
	public static final int BUTTON_NUMBER = 5;
	public static final int BUTTON_PADDING = 32;

	private SpriteButton mButtons[];
	
	public ItemsHUD(Camera pCamera, TexturePack HUDTexturePack){
		
		super();
		super.setCamera(pCamera);
	
		mButtons = new SpriteButton[ItemsHUD.BUTTON_NUMBER];

		this.setBackgroundEnabled(false);
		
		
		for(int i = 0; i < ItemsHUD.BUTTON_NUMBER; i++){
		
			final int id = i;
			
			mButtons[i] = new SpriteButton(this, 32+(i*ItemsHUD.BUTTON_PADDING+64), 448, HUDTexturePack.getTexturePackTextureRegionLibrary().get(i+1), HUDTexturePack.getTexturePackTextureRegionLibrary().get(0)){
				@Override
				public void onButtonPressed(){
				
					ItemsHUD.this.onItemSelected(id);
					
				}
			};
			
		}
		
	}

	protected void onItemSelected(int i) {
		
		
		
	}
	
}