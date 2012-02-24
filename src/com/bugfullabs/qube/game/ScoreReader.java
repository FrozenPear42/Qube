package com.bugfullabs.qube.game;

import android.app.Activity;
import android.content.SharedPreferences;

import com.bugfullabs.qube.level.Level;




public class ScoreReader{

	private static String mFilename;
	
	private static SharedPreferences score;
	
	private static SharedPreferences.Editor scoreEditor;
	
	public static void setFilename(Activity a, String pName){
		mFilename = pName;
		
		score = a.getSharedPreferences(pName, 0);
		scoreEditor = score.edit();
	}
	
	public static void setScore(Level level, int score){
		
		scoreEditor.putInt(Integer.toString(level.getLevelpackId()) + Integer.toString(level.getLevelId()) + "score", score);
		scoreEditor.commit();
	}
	
	public static void setStars(Level level, int stars){
		
		scoreEditor.putInt(Integer.toString(level.getLevelpackId()) + Integer.toString(level.getLevelId()) + "stars", stars);
		scoreEditor.commit();
	}
	
	public static int getScore(Level level){
		
		return score.getInt(Integer.toString(level.getLevelpackId()) + Integer.toString(level.getLevelId()) + "score", 0);
		
	}
	
	public static String getFile(){
		return mFilename;
	}
	
	public static int getStars(Level level){
		
		return score.getInt(Integer.toString(level.getLevelpackId()) + Integer.toString(level.getLevelId()) + "stars", 0);
		
	}
	
	public static void addTotalCubes(int value){
		
		scoreEditor.putInt("TotalCubes", (score.getInt("TotalCubes", 0) + value));
		
	}

	public static int getTotalCubes() {

		return score.getInt("TotalCubes", 0);
	}
	
	
}