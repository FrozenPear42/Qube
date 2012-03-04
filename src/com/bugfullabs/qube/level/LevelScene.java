package com.bugfullabs.qube.level;

import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;



public class LevelScene extends Scene{
	
	private ArrayList<Sprite> mArrows;
	private ArrayList<Sprite> mStars;
	
	
	LevelScene(int numberOfCubes){
		super();
	
		mStars = new ArrayList<Sprite>(3);
		mArrows = new ArrayList<Sprite>(numberOfCubes);
		
	}
	
	public void addArrow(Sprite arrow){
		mArrows.add(arrow);
		this.attachChild(arrow);
	}
	
	public void addStar(Sprite star){
		mStars.add(star);
		this.attachChild(star);
	}

	public void removeStar(float x, float y){
		
		int index1 = 0;
		int index2 = 1;
		
		for(int i = 0; i < 3; i++){

			if(mStars.get(i).getX() == x){
				index1 = i;
				break;
			}
			if(mStars.get(i).getY() == y){
				index2 = i;
				break;
			}
		
		}
		
		if(index1 == index2){
		mStars.get(index1).detachSelf();
		}
		
	}
	
	public void setArrowsVisibility(boolean b){
			for(int i = 0; i < mArrows.size(); i++)
			{
			mArrows.get(i).setVisible(b);	
			}
	}

}

