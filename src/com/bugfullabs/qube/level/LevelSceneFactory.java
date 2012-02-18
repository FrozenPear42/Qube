package com.bugfullabs.qube.level;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import android.util.Log;




public class LevelSceneFactory{
	
	private Level level;
	private Scene levelScene;
	private TexturePack txPack;
	
	
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
	
	public LevelSceneFactory(Level mLevel, TexturePack txPack){
	
		this.level = mLevel;
		this.txPack = txPack;
	}
	
	
	public Scene createScene(){	
			this.levelScene	= new Scene();	
			  
			levelScene.setBackground(new SpriteBackground(new Sprite(0, 0, txPack.getTexturePackTextureRegionLibrary().get(BG_ID))));

			for(int i = 0; i < level.getWidth(); i++){
		    	  for(int j = 0; j < level.getHeight(); j++){
		    	  
		    	  switch(level.getItemNumber(i, j))
		    	  {
		    	  
		    	  case 0:	//BLANK
		    		  
		    		  break;
		    	  
		    	  case 1:	//SOLID 
		    		  
		    		  final Sprite solid = new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(SOLID_ID));
		    		  levelScene.attachChild(solid);
		    		  Log.i("SOLID",Integer.toString(i)+" . "+Integer.toString(j));
		    		  
		    		  break;

		    	  case 2:	//END
		    		  
		    		  final Sprite end = new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(END0_ID));
		    		  levelScene.attachChild(end);
		    		  Log.i("END",Integer.toString(i)+" . "+Integer.toString(j));
		    		  
		    		  break;
		    	  case 3:	//STAR
			  
			  final Sprite star = new Sprite(i*32, j*32, txPack.getTexturePackTextureRegionLibrary().get(STAR_ID));
			  levelScene.attachChild(star);
			  Log.i("STAR",Integer.toString(i)+" . "+Integer.toString(j));
			  
			  break;

		    	  }
		    		  
		    	 }
			  }	
			
			
			for (int i = 0; i < level.getNumberOfCubes(); i++){
				level.getCube(i).setTextureRegion(txPack.getTexturePackTextureRegionLibrary().get(level.getCube(i).getColor()));
				level.getCube(i).attachToScene(levelScene);
			}
			
			
			return this.levelScene;
	}
	
}