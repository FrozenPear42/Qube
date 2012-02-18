package com.bugfullabs.qube.level;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import android.util.Log;




public class LevelSceneFactory{
	
	private Level level;
	private Scene levelScene;
	private TexturePack txPack;
	
	public LevelSceneFactory(Level mLevel, TexturePack txPack){
	
		this.level = mLevel;
		this.txPack = txPack;
	}
	
	
	public Scene createScene(){	
			this.levelScene	= new Scene();	
			  

			for(int i = 0; i < level.getWidth(); i++){
		    	  for(int j = 0; j < level.getHeight(); j++){
		    	  
		    	  switch(level.getItemNumber(i, j))
		    	  {
		    	  
		    	  case 0:	//BLANK
		    		  
		    		  break;
		    	  
		    	  case 1:	//SOLID 
		    		  
		    		  final Rectangle rect = new Rectangle(i*32, j*32, 32, 32);
		    		  rect.setColor(1.0f, 0.456f, 0.89f);
		    		  levelScene.attachChild(rect);
		    		  Log.i("SOLID",Integer.toString(i)+" . "+Integer.toString(j));
		    		  
		    		  break;

		    	  case 2:	//END
		    		  
		    		  final Rectangle end = new Rectangle(i*32, j*32, 32, 32);
		    		  end.setColor(0.0f, 1.0f, 0.0f);
		    		  levelScene.attachChild(end);
		    		  Log.i("END",Integer.toString(i)+" . "+Integer.toString(j));
		    		  
		    		  break;
		    	  case 3:	//END
			  
			  final Rectangle star = new Rectangle(i*32, j*32, 32, 32);
			  star.setColor(0.4f, 0.0f, 1.0f);
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