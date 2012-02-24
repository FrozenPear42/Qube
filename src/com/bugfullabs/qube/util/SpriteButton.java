package com.bugfullabs.qube.util;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;


public class SpriteButton{
	
	private Sprite mBgButton;
	private Sprite mSpriteButton;
	private boolean canBeTouched = true;
	private Scene mScene;
	
	public SpriteButton(Scene scene, final float x, final float y, final TextureRegion pSprite, final TextureRegion tx){	
		
			this.mScene = scene;
		
			this.mBgButton = new Sprite(x, y, tx){
				@Override
		        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		        if(pSceneTouchEvent.isActionDown() == true){
		        	if(canBeTouched == true)
		        	{
		        	onButtonPressed();
		        	}
		        	}
				return true;
	    }
		};
		
			this.mSpriteButton = new Sprite(x, y, pSprite);
			
		scene.attachChild(mBgButton);
		scene.attachChild(mSpriteButton);
		scene.registerTouchArea(mBgButton);
		}
	
	
	public SpriteButton(Scene scene, final float x, final float y, final float width, final float height, final TextureRegion pSprite, final TextureRegion tx){	
		
		this.mBgButton = new Sprite(x, y, tx){
		@Override
        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        if(pSceneTouchEvent.isActionDown() == true){
        	if(canBeTouched == true)
        	{
        	onButtonPressed();
        	}
        	}
		return true;
    }
	};
	
	this.mSpriteButton = new Sprite(x, y, pSprite);
		
	this.mBgButton.setSize(width, height);
	
	scene.attachChild(mBgButton);
	scene.attachChild(mSpriteButton);
	scene.registerTouchArea(mBgButton);
	}
	
	
	public SpriteButton(Scene scene, final float x, final float y, final TextureRegion pSprite){	
		
		this.mBgButton = new Sprite(x, y, pSprite){
			@Override
	        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	        if(pSceneTouchEvent.isActionDown() == true){
	        	if(canBeTouched == true)
	        	{
	        	onButtonPressed();
	        	}
	        	}
			return true;
    }
	};
	scene.attachChild(mBgButton);
	scene.registerTouchArea(mBgButton);
	}
	
	
	public void setSprite(Sprite pSprite){
		this.mSpriteButton = pSprite;
	}
	
	public void setSprite(TextureRegion pSprite){
		this.mSpriteButton.detachSelf();
		this.mSpriteButton = new Sprite(mSpriteButton.getX(), mSpriteButton.getY(),pSprite);
		this.mScene.attachChild(mSpriteButton);
		
	}
	
	
	public void setPosition(final float x, final float y){
		
		mBgButton.setPosition(x, y);
		mSpriteButton.setPosition(x, y);
	}
	
	public void setCanBeTouched(boolean value){
		this.canBeTouched = value;
	}
	
	public boolean canBeTouched(){
		return this.canBeTouched;
	}
	
	public void onButtonPressed(){
		
	}
	
	public void detach(){
		this.mBgButton.detachSelf();
		this.mSpriteButton.detachSelf();
	}
	
	public void attachTo(Scene s){
		s.attachChild(mBgButton);
		s.attachChild(mSpriteButton);
	}


	public void setVisible(boolean isVisible) {
		this.mBgButton.setVisible(isVisible);
		this.mSpriteButton.setVisible(isVisible);
	}
	
}