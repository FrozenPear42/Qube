package com.bugfullabs.qube.level;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;

import com.bugfullabs.qube.game.GameValues;




public class LevelSceneFactory{
	
	
	public static final int CUBE0_ID = 0;
	public static final int CUBE1_ID = 1;
	public static final int CUBE2_ID = 2;
	public static final int CUBE3_ID = 3;
	public static final int CUBE4_ID = 4;
	public static final int CUBE5_ID = 5;
	public static final int CUBE6_ID = 6;
	public static final int END0_ID = 7;
	public static final int END1_ID = 8;
	public static final int END2_ID = 9;
	public static final int END3_ID = 10;
	public static final int END4_ID = 11;
	public static final int END5_ID = 12;
	public static final int END6_ID = 13;
	public static final int SOLID_ID = 14;
	public static final int STAR_ID = 15;	
	public static final int BG_ID = 16;
	

	
	public static Scene createScene(Level level, TexturePack txPack){	
			
			Scene levelScene = new Scene();	
			  
			levelScene.setBackground(new SpriteBackground(new Sprite(0, 0, txPack.getTexturePackTextureRegionLibrary().get(BG_ID))));

			for(int i = 0; i < level.getWidth(); i++){
		    	  for(int j = 0; j < level.getHeight(); j++){
		    	  
		    	  switch(level.getItemNumber(i, j))
		    	  {
		    	  
		    	  case GameValues.ITEM_SOLID:	//SOLID 	  
		    		  levelScene.attachChild(new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(SOLID_ID)));
		    		  break;

		    	  case GameValues.ITEM_STAR:	//STAR
		    		  levelScene.attachChild(new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(STAR_ID)));
		    		  break;
		    		  
			  	  default:
			  		
			  		 break;
			  	
		    	  }
		    	  
		    	  //ENDS
		    	  if(level.getItemNumber(i, j) >= GameValues.ITEM_END_0 &&  level.getItemNumber(i, j) <= GameValues.ITEM_END_6){
		    		  levelScene.attachChild(new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(level.getItemNumber(i, j))));  
		    	  }
		    	  //END ENDS
		    		  
		    	 }
			  }	
			
			
			for (int i = 0; i < level.getNumberOfCubes(); i++){
				level.getCube(i).setTextureRegion(txPack.getTexturePackTextureRegionLibrary().get(level.getCube(i).getColor()));
				level.getCube(i).attachToScene(levelScene);
				level.getCube(i).setPosition(level.getCube(i).getX(), level.getCube(i).getY());
			}
			
			
			return levelScene;
	}
	
}