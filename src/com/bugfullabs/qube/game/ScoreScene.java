package com.bugfullabs.qube.game;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.bugfullabs.qube.R;
import com.bugfullabs.qube.util.Button;


public class ScoreScene extends Scene{
	
	BaseGameActivity activity;
	
	final Button nextLevel;
	final Button replay;
	final Button mainMenu;
	
	public ScoreScene(BaseGameActivity e, TextureRegion buttonTexture, Font buttonFont){
		super();
		activity = e;
		
		nextLevel = new Button(this, 150, 300, 250, 75, activity.getString(R.string.nextlevel), buttonTexture, buttonFont);
		replay = new Button(this, 400, 300, 250, 75, activity.getString(R.string.reset), buttonTexture, buttonFont);
		mainMenu = new Button(this, 275, 375, 250, 75,activity.getString(R.string.mainmenu), buttonTexture, buttonFont);
	
	
	}
	
	public void setScore(){
		
	}
	
	public void setSpriteBackground(Sprite bg){
		this.setBackground(new SpriteBackground(bg));	
	}
	
	public void setStarSprite(Sprite star, Sprite noStar){
		//TODO: this
	}
	
	
}