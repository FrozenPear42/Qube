package com.bugfullabs.qube.level;

import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;

import com.bugfullabs.qube.game.CubeEntity;
import com.bugfullabs.qube.game.GameValues;

public class Level{

	private int level_pattern[][];
	private int level_items[][];
	private int width;
	private int height;		
	private int numberOfCubes;		
	private ArrayList<CubeEntity> cubes;
	
	private int id;
	private int levelpackId;
	
	private String levelTexture;
	
	public Level(int columns, int rows, int id, int levelpackId, String texture){
		
		width = columns;
		height = rows;
		
		this.levelTexture = texture;
		
		level_pattern = new int[columns][rows];
		level_items = new int[columns][rows];
		
		for (int i = 0; i < columns; i++){
			for (int j = 0; j < rows; j++){				
				level_pattern[i][j] = 0;
			}	
		
		}
		
		this.id = id;
		this.levelpackId = levelpackId;
	}
	
	public void setItem(final int column, final int row, final int id){
		
		if(!isCorrect(column, row)){
			return;
		}
		
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
		
		if(!isCorrect(column, row)){
			return -1;
		}
		return level_pattern[column][row];
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
	
	public CubeEntity setCube(int x, int y,int color, int direction, int index ){
		
		final CubeEntity cube = new CubeEntity(x*32, y*32, color, direction);
		cubes.add(index, cube);
		return cubes.get(index);
	}

	public int getLevelId() {
		
		return id;
	}
	
	public int getLevelpackId(){
		return levelpackId;
	}
	
	public String getLevelTexture(){
		return this.levelTexture;
	}	
	
	
	public void removeCube(int id, Scene scene){
		
		cubes.get(id).removeCube(scene);
		cubes.get(id).setFinished(true);
		
	}
	
	
	public void addItemEntityItem(int column, int row){
		
		if(!isCorrect(column, row)){
			return;
		}
		
		this.level_items[column][row] = GameValues.ITEM_SOLID;
	}
	
	public int getItemEntityItem(int column, int row){
		
		if(!isCorrect(column, row)){
			return -1;
		}
		
		return level_items[column][row];
	}
	
	public int getCollision(int column, int row){
		
		if(!isCorrect(column, row)){
			return -1;
		}
		
		if(level_pattern[column][row] != 0){
			return level_pattern[column][row];
		}
		if(level_items[column][row] != 0){
			return level_items[column][row];
		}	
		return 0;
	}

	public void deleteItemEntityItem(int column, int row) {
		
		if(!isCorrect(column, row)){
			return;
		}
		
		level_items[column][row] = GameValues.ITEM_BLANK;
	}
	
	
	private boolean isCorrect(int x, int y){
		
		return (x <= width && y <= height);
	}
}

