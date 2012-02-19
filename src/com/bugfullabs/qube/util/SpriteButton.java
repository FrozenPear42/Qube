package com.bugfullabs.qube.util;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;


public class SpriteButton{
	
	private Sprite mBgButton;
	private Sprite mSpriteButton;
	
	
	public SpriteButton(Scene scene, final float x, final float y, final TextureRegion pSprite, final TextureRegion tx){	
		
			this.mBgButton = new Sprite(x, y, tx){
			@Override
	        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	        if(pSceneTouchEvent.isActionDown() == true){
			onButtonPressed();
	        }
			return true;
	    }
		};
		
			this.mSpriteButton = new Sprite(x, y, pSprite);
			
		scene.attachChild(mBgButton);
		scene.attachChild(mSpriteButton);
		}
	
	
	
	
	
	public void setSprite(Sprite pSprite){
		this.mSpriteButton = pSprite;
	}
	
	public void setPosition(final float x, final float y){
		
		mBgButton.setPosition(x, y);
		mSpriteButton.setPosition(x, y);
	}
	
	public void onButtonPressed(){
		
	}
	
}