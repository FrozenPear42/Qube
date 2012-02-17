package com.bugfullabs.qube;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;



public class CubeEntity {

	public static final int DIRECTION_FORWARD = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_BACKWARD = 2;
	public static final int DIRECTION_LEFT = 3;
	
	
	private int direction = DIRECTION_FORWARD;
	
	private float x;
	private float y;
	
	private Sprite sprite;
	
	public CubeEntity(int pX, int pY) {
	x = pX;
	y = pY;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setTextureRegion(TextureRegion tx){
		
		sprite = new Sprite(0,0,tx);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setDirection(final int dir){
		this.direction = dir;
	}
	
	
	public void setPosition(final float x, final float y){
		sprite.setPosition(x, y);
		
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
	
	public Scene attachToScene(Scene scene){
		
		scene.attachChild(sprite);
		
		return scene;
	}
	
}
