package com.bugfullabs.qube.level;

import java.util.ArrayList;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.bugfullabs.qube.CubeEntity;


import android.util.Log;

public class Level{

	private int level_pattern[][];
	
	
	private int width;
	private int height;
		
	private int numberOfCubes = 0;
	
	private static final int ITEM_BLANK = 0;
	
	private Scene gameScene;
	
	private ArrayList<CubeEntity> cubes;


	private int id;


	private int levelpackId;

	public static ArrayList<TextureRegion> cubeTextures; 
	
	public Level(int columns, int rows, int id, int levelpackId){
		
		width = columns;
		height = rows;
		
		level_pattern = new int[columns][rows];
	
		for (int i = 0; i < columns; i++){
			for (int j = 0; j < rows; j++){				
				level_pattern[i][j] = ITEM_BLANK;		
			}	
		
		}
		
		this.id = id;
		this.levelpackId = levelpackId;
	}
	
	public void setItem(final int column, final int row, final int id){
		
		level_pattern[column][row] = id;
		
	}
	
	
	public int getWidth(){
		
	return this.width;
	}
	
	public int getHeight(){
	
		return this.height;
}

	
	public int getItemNumber(int column, int row)
	{
		
		return level_pattern[column][row];
	}
	
	
	
	public Scene getScene(){
	
	this.gameScene	= new Scene();	
	  

	for(int i = 0; i < width; i++){
    	  for(int j = 0; j < height; j++){
    	  
    	  switch(level_pattern[i][j])
    	  {
    	  
    	  case 0:	//BLANK
    		  
    		  break;
    	  
    	  case 1:	//SOLID 
    		  
    		  final Rectangle rect = new Rectangle(i*32, j*32, 32, 32);
    		  rect.setColor(1.0f, 0.456f, 0.89f);
    		  gameScene.attachChild(rect);
    		  Log.i("SOLID",Integer.toString(i)+" . "+Integer.toString(j));
    		  
    		  break;

    	  case 2:	//END
    		  
    		  final Rectangle end = new Rectangle(i*32, j*32, 32, 32);
    		  end.setColor(0.0f, 1.0f, 0.0f);
    		  gameScene.attachChild(end);
    		  Log.i("END",Integer.toString(i)+" . "+Integer.toString(j));
    		  
    		  break;
    	  case 3:	//END
	  
	  final Rectangle star = new Rectangle(i*32, j*32, 32, 32);
	  star.setColor(0.4f, 0.0f, 1.0f);
	  gameScene.attachChild(star);
	  Log.i("END",Integer.toString(i)+" . "+Integer.toString(j));
	  
	  break;

    	  }
    		  
    	 }
	  }	
	
	return this.gameScene;
	}
	
	
	public CubeEntity getCube(int number){
			
		return cubes.get(number);
	}
	
	public int getNumberOfCubes(){
	return this.numberOfCubes;	
	}
	
	public ArrayList<CubeEntity> getCubes(){
		return cubes;
	}
	
	public void setNumberOfCubes(int number){
		
		cubes = new ArrayList<CubeEntity>(number);
		this.numberOfCubes = number;
	}
	
	public void setCube(int x, int y,int color,int index){
		
		Log.w("x", Integer.toString(x));
		Log.w("y", Integer.toString(y));
		Log.w("color", Integer.toString(color));
		Log.w("index", Integer.toString(index));
		
		
		final CubeEntity cube;
		
		cube = new CubeEntity(x*32, y*32, cubeTextures.get(color));

		cubes.add(index, cube);
	
	}

	public int getLevelId() {
		
		return id;
	}
	
	public int getLevelpackId(){
		return levelpackId;
	}
	
	
}