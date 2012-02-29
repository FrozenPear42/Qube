package com.bugfullabs.qube.game;

import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;

import com.bugfullabs.qube.level.Level;


public class ItemEntity{
	
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
			this.mSprites.clear();
			this.mLevel.deleteItemEntityItem((int)this.mSprites.get(i).getX()/32, (int)this.mSprites.get(i).getY()/32);
			
		}
	}
	
	
}