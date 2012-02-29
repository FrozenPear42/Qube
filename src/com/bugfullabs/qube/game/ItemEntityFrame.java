package com.bugfullabs.qube.game;

import java.util.ArrayList;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.scene.Scene;




public class ItemEntityFrame{
	
	private int mTemplate;
	
	private int mX;
	private int mY;
	
	private Scene mScene;
	
	private ArrayList<Line> mLines;
	
	public ItemEntityFrame(int x, int y, Scene scene, int pTemplate){
		
		this.mTemplate = pTemplate;
		this.mX = x;
		this.mY = y;
		this.mScene = scene;
		
		this.mLines = new ArrayList<Line>();
		
		draw();
		
	}
	
	private void draw(){
		
		switch(mTemplate){
		
		
		case ItemEntity.TEMPLATE_1:
			mLines.add(0, new Line(mX, mY, mX+32, mY, 3));
			mLines.add(1, new Line(mX+32, mY, mX+32, mY+32, 3));
			mLines.add(2, new Line(mX+32, mY+32, mX, mY+32, 3));
			mLines.add(3, new Line(mX, mY+32, mX, mY, 3));
			
			break;
			
		case ItemEntity.TEMPLATE_2_1:
				
			break;
				
		case ItemEntity.TEMPLATE_2_2:
				
			break;
			
		case ItemEntity.TEMPLATE_3:
			
			break;
		
		case ItemEntity.TEMPLATE_4:
			
			break;
				
		}
		
		for(int i = 0; i < mLines.size(); i++){
			mScene.attachChild(mLines.get(i));
		}
		
	}
	
	
	public void setPosition(int x, int y){
		
		mLines.get(0).setPosition(x, y, x+32, y);
		mLines.get(1).setPosition(x+32, y, x+32, y+32);
		mLines.get(2).setPosition(x+32, y+32, x, y+32);
		mLines.get(3).setPosition(x, y+32, x, y);
		
		
		
	}
	
	
}