package com.bugfullabs.qube.game;

import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.anddev.andengine.input.touch.TouchEvent;

import com.bugfullabs.qube.level.Level;

/**
 * 
 * @author Bugful Labs
 * @author Grushenko
 * @email  wojciech@bugfullabs.pl
 *
 */

public class ItemEntity implements ITouchArea{
	
	public static final int TEMPLATE_1 = 0;
	public static final int TEMPLATE_2_1 = 1;
	public static final int TEMPLATE_2_2 = 2;
	public static final int TEMPLATE_3 = 3;
	public static final int TEMPLATE_4 = 4;
	
	public static final int ROTATION_0 = 0;
	public static final int ROTATION_90 = 1;
	public static final int ROTATION_180 = 2;
	public static final int ROTATION_270 = 3;
	
	
	private int mX = 0;
	private int mY = 0;
	
	private int rX;
	private int rY;
	
	private int rX16;
	private int rY16;
	
	
	private int mTemplate;
	
	private Level mLevel;
	
	private TexturePack mPack;
	
	private Scene mScene;
	
	private ArrayList<Sprite> mSprites;
	
	public ItemEntity(final int pX, final int pY, final int pTemplate, Scene pScene, final Level pLevel, final TexturePack pPack){
		
		mSprites = new ArrayList<Sprite>(5);
		
		this.mTemplate = pTemplate;
		this.mX = pX;
		this.mY = pY;
		this.mPack = pPack;
		this.mScene = pScene;
		this.mLevel = pLevel;
		
		this.rX = round(mX);
		this.rY = round(mY);
		
		this.rX16 = round(mX-16);
		this.rY16 = round(mY-16);
		
		
		this.mScene.registerTouchArea(this);
		
		this.draw(pTemplate);
	}
	
	private void draw(int pTemplate){
		
		switch(pTemplate){
		
		case TEMPLATE_1:
		
			this.mSprites.add(0, new Sprite((mX/32)*32, (mY/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			mScene.attachChild(this.mSprites.get(0));
			
			
			break;
		
		case TEMPLATE_2_1:
			
			this.mSprites.add(0, new Sprite((mX/32)*32, (mY/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(1, new Sprite((mX/32)*32+32, (mY/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			
			mScene.attachChild(this.mSprites.get(0));
			mScene.attachChild(this.mSprites.get(1));
			
			break;
		
		case TEMPLATE_2_2:
		
			this.mSprites.add(0, new Sprite(((mX-16)/32)*32+32, ((mY-16)/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(1, new Sprite(((mX-16)/32)*32, ((mY-16)/32)*32+32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			
			mScene.attachChild(this.mSprites.get(0));
			mScene.attachChild(this.mSprites.get(1));
			
			break;
		
		case TEMPLATE_3:
			
			this.mSprites.add(0, new Sprite(((mX-16)/32)*32+32, ((mY-16)/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(1, new Sprite(((mX-16)/32)*32, ((mY-16)/32)*32+32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(2, new Sprite(((mX-16)/32)*32+32, ((mY-16)/32)*32+32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
				
			mScene.attachChild(this.mSprites.get(0));
			mScene.attachChild(this.mSprites.get(1));
			mScene.attachChild(this.mSprites.get(2));
			
			break;
			
		case TEMPLATE_4:
			
			this.mSprites.add(0, new Sprite(((mX-16)/32)*32, ((mY-16)/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(1, new Sprite(((mX-16)/32)*32+32, ((mY-16)/32)*32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(2, new Sprite(((mX-16)/32)*32, ((mY-16)/32)*32+32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			this.mSprites.add(3, new Sprite(((mX-16)/32)*32+32, ((mY-16)/32)*32+32, this.mPack.getTexturePackTextureRegionLibrary().get(GameValues.SOLID_ID)));
			
			mScene.attachChild(this.mSprites.get(0));
			mScene.attachChild(this.mSprites.get(1));
			mScene.attachChild(this.mSprites.get(2));
			mScene.attachChild(this.mSprites.get(3));
			
			break;
		
		}
		
		for(int i = 0; i<mSprites.size(); i++){
			
			mLevel.addItemEntityItem((int)this.mSprites.get(i).getX()/32, (int)this.mSprites.get(i).getY()/32);
			
		}
			
	}
	
	
	public void rotate(int rotation){
		
		switch(mTemplate){
		
		case TEMPLATE_1:

			break;
		
		case TEMPLATE_2_1:
			if(rotation == ROTATION_0 || rotation == ROTATION_180){
				this.mSprites.get(1).setPosition(mX+32, mY);
			}else{
				this.mSprites.get(1).setPosition(mX, mY+32);		
			}
	
			break;
		
		case TEMPLATE_2_2:
		
		
			break;
		
		case TEMPLATE_3:
			
			break;
			
		case TEMPLATE_4:
			
			
			break;
		
		}
	}
	
	public void remove(){
		for(int i = 0;  i < mSprites.size(); i++){
			
			this.mSprites.get(i).detachSelf();
			this.mLevel.deleteItemEntityItem((int)this.mSprites.get(i).getX()/32, (int)this.mSprites.get(i).getY()/32);
		}
		this.mSprites.clear();
	}

	@Override
	public boolean contains(float pX, float pY) {
		
		switch(mTemplate){
		
		case TEMPLATE_1:
		return (pX >= rX && pX <= rX+32 && pY >= rY && pY <= rY+32);
		
		case TEMPLATE_2_1:
		return (pX >= rX && pX <= rX+64 && pY >= rY && pY <= rY+32);
		
		case TEMPLATE_2_2:
		//TODO
			
		return (pX >= mX && pX <= mX+64 && pY >= mY && pY <= mY+64);
		
		case TEMPLATE_3:
		//TODO
			
		return (pX >= mX && pX <= mX+64 && pY >= mY && pY <= mY+64);
		
		case TEMPLATE_4:
		return (pX >= rX16 && pX <= rX16+64 && pY >= rY16 && pY <= rY16+64);
		
		default:
		return false;
		
		}
	
	}

	@Override
	public float[] convertLocalToSceneCoordinates(float pX, float pY) {
		float tmp[] = new float[2];
		tmp[0] = pX;
		tmp[1] = pY;
		return tmp;
	}

	@Override
	public float[] convertSceneToLocalCoordinates(float pX, float pY) {
		float tmp[] = new float[2];
		tmp[0] = 0;
		tmp[1] = 0;
		return tmp;
		}

	@Override
	public boolean onAreaTouched(TouchEvent pTouchEvent, float pLocalX, float pLocalY) {
		ItemEntity.this.onTouched(pTouchEvent, pLocalX, pLocalY);
		return true;
	}
	
	public void onTouched(TouchEvent pTouchEvent, float pLocalX, float pLocalY){
		
	}
	
	private int round(int n){
		return (int)((n)/32)*32;
	}
	
	
}