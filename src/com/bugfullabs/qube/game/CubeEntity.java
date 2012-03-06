package com.bugfullabs.qube.game;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.MoveModifier;
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
	
	private float initX;
	private float initY;
		
	private int initDirection;
	
	private int color;
	
	private Sprite sprite;
	
	private boolean finished = false;
	
	private MoveModifier mM; 
	
	private Scene mScene;
	
	
	public CubeEntity(int pX, int pY, int pColor, int pDirection) {
	x = pX;
	y = pY;
	initX = pX;
	initY = pY;
	color = pColor;
	
	initDirection = pDirection;
	direction = pDirection;
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
	
	public void moveToInitPosition(){
		sprite.unregisterEntityModifier(mM);
		sprite.setPosition(initX, initY);
		this.x = initX;
		this.y = initY;
		this.setDirection(initDirection);
	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
	
	public int getColor(){
		return this.color;
	}
	
	public Scene attachToScene(Scene scene){
		
		scene.attachChild(sprite);
		this.mScene = scene;
		
		return scene;
	}
	
	
	public void removeCube(Scene scene){	
		
		scene.detachChild(sprite);
		
	}

	public void setFinished(boolean isFinished) { 
		finished = isFinished;
	}
	
	public boolean isFinished() { 
		return finished;
	}
	
		
	public void move(float endX, float endY){
	
		mM = new MoveModifier(0.199f, this.x, endX, this.y, endY);
	
		this.mM.setRemoveWhenFinished(true);
		
		this.sprite.registerEntityModifier(mM);
		this.x = endX;
		this.y = endY;

		
	}
	
	public void reset(){
		//TODO: WHY CUBE STAY IN ITS POSITION?
		this.removeCube(mScene);
		this.attachToScene(mScene);
		this.moveToInitPosition();
		this.setFinished(false);
	}
	
	
	public void attachChild(IEntity pEntity){
		sprite.attachChild(pEntity);
	}
	
	public void detachChildren(){
		sprite.detachChildren();
	}
}
