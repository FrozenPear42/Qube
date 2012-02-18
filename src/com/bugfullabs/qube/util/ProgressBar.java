package com.bugfullabs.qube.util;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;

public class ProgressBar{
	
	private Rectangle Bar;
	private Line[] mFrameLines = new Line[4];
	private Rectangle background;
	private final float mPixels;

	public ProgressBar(final Scene mScene, final float pX, final float pY, final float width, final float height, final float line_width){
	
	this.Bar = new Rectangle(pX, pY, 0, height);	
	this.mFrameLines[0] = new Line(pX, pY, pX+width, pY, line_width);
	this.mFrameLines[1] = new Line(pX, pY, pX, pY+height, line_width);
	this.mFrameLines[2] = new Line(pX, pY+height, pX+width, pY+height, line_width);
	this.mFrameLines[3] = new Line(pX+width, pY, pX+width, pY+height, line_width);
	this.background = new Rectangle(pX, pY, width, height);
	
	mScene.attachChild(background);
	mScene.attachChild(Bar);
	mScene.attachChild(mFrameLines[0]);
	mScene.attachChild(mFrameLines[1]);
	mScene.attachChild(mFrameLines[2]);
	mScene.attachChild(mFrameLines[3]);
	this.mPixels = width/100;	
	}
	
	public void setFrameColor(float r, float g, float b, float alpha){
		for(int i = 0; i <= 3; i++){
			mFrameLines[i].setColor(r, g, b, alpha);
		}
	}
	
	public void setBarColor(float r, float g, float b, float alpha){
		
		Bar.setColor(r, g, b, alpha);
}
	public void setBackgroundColor(float r, float g, float b, float alpha){
	
		background.setColor(r, g, b, alpha);
}
	
	public void setProgress(float progress){
		
		Bar.setWidth(progress * mPixels);
		
	}
	
}


