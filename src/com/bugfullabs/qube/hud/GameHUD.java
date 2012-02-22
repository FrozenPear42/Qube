package com.bugfullabs.qube.hud;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;

import com.bugfullabs.qube.util.SpriteButton;



public class GameHUD extends HUD{
	
	public static final int BUTTON_NUMBER = 5;
	public static final int BUTTON_PADDING = 8;

	public static final int ID_STAR = 1;
	
	private SpriteButton mButtons[];
	private int mStars = 0;
	private TexturePack HUDTexturePack;
	
	public GameHUD(Camera pCamera, TexturePack pTexturePack){
		
		super();
		super.setCamera(pCamera);
	
		HUDTexturePack = pTexturePack;
		
		mButtons = new SpriteButton[GameHUD.BUTTON_NUMBER];

		this.setBackgroundEnabled(false);
		
		for(int i = 0; i < GameHUD.BUTTON_NUMBER; i++){
		
			final int id = i;
			
			mButtons[i] = new SpriteButton(this, 730, 32+i*(GameHUD.BUTTON_PADDING+64), HUDTexturePack.getTexturePackTextureRegionLibrary().get(i+1), HUDTexturePack.getTexturePackTextureRegionLibrary().get(0)){
				@Override
				public void onButtonPressed(){
				
					GameHUD.this.onItemSelected(id);
					
				}
			};
			
			
		}
		
	}

	public void show(){
		this.setVisible(true);
		this.setTouchAreaBindingEnabled(true);
	}
	
	public void hide(){
		this.setVisible(false);
		this.setTouchAreaBindingEnabled(false);
	}
	
	public void setStars(int number){
		
		if(number > mStars){
		
		for(int i = mStars; i < number; i++){
			this.attachChild(new Sprite(i*32,0, HUDTexturePack.getTexturePackTextureRegionLibrary().get(ID_STAR)));
			
		}
		}
		
	}
	
	
	protected void onItemSelected(int i) {	
		
	}
	
}