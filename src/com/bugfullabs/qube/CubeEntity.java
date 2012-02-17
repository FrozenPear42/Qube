package com.bugfullabs.qube;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;



public class CubeEntity extends Sprite{

	public static final int DIRECTION_FORWARD = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_BACKWARD = 2;
	public static final int DIRECTION_LEFT = 3;
	
	
	private int direction = DIRECTION_FORWARD;
	
	public CubeEntity(float pX, float pY, float pWidth, float pHeight, TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
	}

	public CubeEntity(int pX, int pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(final int dir){
		this.direction = dir;
	}
	
}
