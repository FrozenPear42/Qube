package com.bugfullabs.qube.hud;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;

import com.bugfullabs.qube.util.SpriteButton;



public class ItemsHUD extends HUD{
	
	public static final int BUTTON_NUMBER = 5;
	public static final int BUTTON_PADDING = 8;


	public static final int PLAY_ID = 7;
	
	
	private SpriteButton mButtons[];
	private SpriteButton playButton;
	private TexturePack HUDTexturePack;
	
	public ItemsHUD(Camera pCamera, TexturePack pTexturePack){
		
		super();
		super.setCamera(pCamera);
	
		HUDTexturePack = pTexturePack;
		
		mButtons = new SpriteButton[ItemsHUD.BUTTON_NUMBER];

		this.setBackgroundEnabled(false);
		
		for(int i = 0; i < ItemsHUD.BUTTON_NUMBER; i++){
		
			final int id = i;
			
			mButtons[i] = new SpriteButton(this, 730, 32+i*(ItemsHUD.BUTTON_PADDING+64), HUDTexturePack.getTexturePackTextureRegionLibrary().get(i+1), HUDTexturePack.getTexturePackTextureRegionLibrary().get(0)){
				@Override
				public void onButtonPressed(){
				
					ItemsHUD.this.onItemSelected(id);
					
				}
			};
			
		}
		
		playButton = new SpriteButton(this, 730, (BUTTON_NUMBER)*(ItemsHUD.BUTTON_PADDING+64), HUDTexturePack.getTexturePackTextureRegionLibrary().get(PLAY_ID), HUDTexturePack.getTexturePackTextureRegionLibrary().get(0)){
			
			@Override
			public void onButtonPressed(){
				
				ItemsHUD.this.onPlay();
			}
		};
			
	
		
	}

	public void show(){
		this.setVisible(true);
		this.setTouchAreaBindingEnabled(true);
	}
	
	public void hide(){
		this.setVisible(false);
		this.setTouchAreaBindingEnabled(false);
	}
	
	protected void onItemSelected(int i) {	
		
	}
	
	protected void onPlay(){
		
	}
	
}