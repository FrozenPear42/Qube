package com.bugfullabs.qube.util;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.VerticalAlign;


public class Button{
	
	private Sprite bgButton;
	private AlignedText textButton;
	
	private float mX;
	private float mY;
	
	public Button(final Scene scene, final float x, final float y, final float width, final float height, final String text, final TextureRegion tx, final Font font){	
		
			mX = x;
			mY = y;
		
			textButton = new AlignedText(x, y, font, text, HorizontalAlign.CENTER, VerticalAlign.CENTER, width, height);	
			
			bgButton = new	Sprite(x, y, tx){
			@Override
	        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	        if(pSceneTouchEvent.isActionDown() == true){
			return onButtonPressed();
	        }
			return false;
	    }
		};
		bgButton.setWidth(width);
		bgButton.setHeight(height);
		
	    scene.registerTouchArea(bgButton);
		scene.attachChild(bgButton);
		scene.attachChild(textButton);
		}
	
	
	
	
	
	public void setText(String text){
		textButton.setText(text);
	}
	
	public void setPosition(final float x, final float y){
		
		textButton.setPosition(x, y);
		bgButton.setPosition(x, y);
	}
	
	public boolean onButtonPressed(){
		return false;
	}

	
	public int getHeight() {
		
		return (int) this.bgButton.getHeight();
	}

	public float getX(){
		return this.mX;
	}

	public float getY(){
		return this.mY;
	}
	
	public int getWidth() {
		
		return (int) this.bgButton.getWidth();
	}
	
}