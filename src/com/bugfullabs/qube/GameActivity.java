package com.bugfullabs.qube;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.VerticalAlign;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import com.bugfullabs.qube.level.Level;
import com.bugfullabs.qube.level.LevelFileReader;
import com.bugfullabs.qube.level.LevelSceneFactory;
import com.bugfullabs.qube.util.AlignedText;
import com.bugfullabs.qube.util.Button;

import com.bugfullabs.qube.hud.ItemsHUD;

import com.bugfullabs.qube.game.CubeEntity;
import com.bugfullabs.qube.game.GameValues;




public class GameActivity extends LoadingActivity{
	
	public static Level level;
	
	private Scene gameScene;
	private Scene scoreScene;
	
	
	private TimerHandler updateTimer;
	
	private BitmapTextureAtlas mAtlas;
	
	private BitmapTextureAtlas mFontTexture;
	
	private StrokeFont Stroke;
	
	private StrokeFont bigFont;
	
	private TextureRegion buttonTexture;
	
	private BitmapTextureAtlas starAtlas;
	
	private TextureRegion starFull;
	
	private TextureRegion starBlank;
	
	private int stars = 0;
	
	private BitmapTextureAtlas mBigFontTexture;
	
	private TexturePack levelPack;
	
	private int cubesFinished = 0;
	
	private Music gameMusic; 
	
	private TexturePack levelItemsPack;
	
	private ItemsHUD mItems;
	
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	private static final String SETTINGS_FILE = "Settings";
	
	@Override
	protected void assetsToLoad() {
			
		super.setLoadingProgress(10);
		this.mAtlas = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		super.setLoadingProgress(20);
		this.buttonTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAtlas, this, "button.png", 0, 0);
		
		this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mBigFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		Typeface typeface =  Typeface.createFromAsset(getAssets(), "font/FOO.ttf");
	    this.Stroke = new StrokeFont(mFontTexture, typeface, 26, true, Color.WHITE, 2, Color.BLACK);
	    this.bigFont = new StrokeFont(mBigFontTexture, typeface, 42, true, Color.WHITE, 2, Color.BLACK);
	    super.setLoadingProgress(90);
		
	    this.starAtlas = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    this.starFull = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.starAtlas, this, "star_full.png", 0, 0);
	    this.starBlank = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.starAtlas, this, "star_blank.png", 128, 0);
	    
	    
	    try {
			levelPack = new TexturePackLoader(this, "gfx/game/").loadFromAsset(this, level.getLevelTexture()+".xml");
		} catch (TexturePackParseException e) {
			e.printStackTrace();
		}
	    
	    try {
			levelItemsPack = new TexturePackLoader(this, "gfx/game/").loadFromAsset(this, level.getLevelTexture()+"_items.xml");
		} catch (TexturePackParseException e) {
			e.printStackTrace();
		}	    
	    
	    MusicFactory.setAssetBasePath("music/");
        try {
                this.gameMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "game.ogg");
                this.gameMusic.setLooping(true);
                this.gameMusic.setVolume(10.0f);
        } catch (final IOException e) {
                Log.e("Error", e.toString());
        }
	    
	    this.mEngine.getTextureManager().loadTextures(this.mAtlas, this.mFontTexture, this.mBigFontTexture,this.starAtlas, this.levelPack.getTexture(), this.levelItemsPack.getTexture());
	    this.mEngine.getFontManager().loadFonts(Stroke, bigFont);      
	    super.setLoadingProgress(100);
		
	    createScoreScene();
	    
		settings = getSharedPreferences(SETTINGS_FILE, 0);
		editor = settings.edit();		
	
	    
	}
	
	
	
	@Override
	protected Scene onAssetsLoaded() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		if(settings.getBoolean("music", true)){
		this.gameMusic.play();
		}
		gameScene = LevelSceneFactory.createScene(level, levelPack);
		
		mItems = new ItemsHUD(this.mCamera, levelItemsPack);
		this.mCamera.setHUD(mItems);
		
		this.mEngine.registerUpdateHandler(updateTimer = new TimerHandler(0.3f, true, new ITimerCallback(){
		@Override
		public void onTimePassed(TimerHandler arg0) {	
		GameActivity.this.onTimerUpdate();	
		}		
		}));
		
		return gameScene;
	}
	
	
	public static void setLevel(Level pLevel){
		level = pLevel;
	}
	
	private void onTimerUpdate(){
		
		checkCollisions();
		
		if(cubesFinished == level.getNumberOfCubes()){
			loadScoreScene();
		}
		
		for(int i = 0; i < level.getNumberOfCubes(); i++){
		
		switch(level.getCube(i).getDirection()){
	
		case CubeEntity.DIRECTION_FORWARD:
		
			level.getCube(i).setPosition(level.getCube(i).getX(), level.getCube(i).getY()-32);
			
		break;
	
		case CubeEntity.DIRECTION_RIGHT:
			
			level.getCube(i).setPosition(level.getCube(i).getX()+32, level.getCube(i).getY());
			
		break;
		
		case CubeEntity.DIRECTION_LEFT:
			
			level.getCube(i).setPosition(level.getCube(i).getX()-32, level.getCube(i).getY());
			
		break;
		
		case CubeEntity.DIRECTION_BACKWARD:
			
			level.getCube(i).setPosition(level.getCube(i).getX(), level.getCube(i).getY()+32);
			
		break;
		
		}
		
		}
	}
	
	
	private void checkCollisions(){
	
		for(int i = 0; i < level.getNumberOfCubes(); i++){
		
		if(!level.getCube(i).isFinished()){	
			
		switch(level.getCube(i).getDirection()){
		
		case CubeEntity.DIRECTION_FORWARD:	
			proceedCollision(level.getItemNumber((int)level.getCube(i).getX()/32, (int)(level.getCube(i).getY()/32)-1), i, CubeEntity.DIRECTION_RIGHT);
			break;
			
		case CubeEntity.DIRECTION_BACKWARD:
			proceedCollision(level.getItemNumber((int)level.getCube(i).getX()/32, (int)(level.getCube(i).getY()/32)+1), i, CubeEntity.DIRECTION_LEFT);
			break;
			
		case CubeEntity.DIRECTION_LEFT:
			proceedCollision(level.getItemNumber((int)(level.getCube(i).getX()/32)-1, (int)level.getCube(i).getY()/32), i, CubeEntity.DIRECTION_FORWARD);
			break;
			
		case CubeEntity.DIRECTION_RIGHT:
			proceedCollision(level.getItemNumber((int)(level.getCube(i).getX()/32)+1, (int)level.getCube(i).getY()/32), i, CubeEntity.DIRECTION_BACKWARD);
			break;
		}
		}
		}
		
	}
	
	
	private void proceedCollision(int id, int cubeId, int nextDirection){
		
		switch(id){
		
		case GameValues.ITEM_SOLID:
			level.getCube(cubeId).setDirection(nextDirection);
			break;

		case GameValues.ITEM_STAR:

			//TODO: FIX - STAR HAS TO DISAPEAR
			stars++;
			
			mItems.setStars(stars);
			
			break;			
		
		
		default:
			break;
		
		}	
		
		
		 if(id >= GameValues.ITEM_END_0 &&  id <= GameValues.ITEM_END_6){
			 if(id-GameValues.ITEM_END_0 == level.getCube(cubeId).getColor()){
				 
				 level.removeCube(cubeId, gameScene);
				 
				 this.cubesFinished++;
				 
				 Log.i("WIN", Integer.toString(id));
			 }
			 
			 
   	  	 }
		
		
	}
	
	
	private void nextLevel(){
		
		this.stars = 0;
		this.mItems.setStars(0);
		this.cubesFinished = 0;
		
		this.mItems.show();
		
		LevelFileReader lvReader = new LevelFileReader(this, "level_"+Integer.toString(level.getLevelpackId())+"_"+Integer.toString(level.getLevelId()+1));
		level = lvReader.getLevel();
		
		this.mEngine.unregisterUpdateHandler(updateTimer);
		
		this.mEngine.setScene(onAssetsLoaded());
		
	}
	
	
	private void resetLevel(){
		
		this.stars = 0;
		this.mItems.setStars(0);
		this.cubesFinished = 0;
		
		this.mItems.show();
		
		LevelFileReader lvReader = new LevelFileReader(this, "level_"+Integer.toString(level.getLevelpackId())+"_"+Integer.toString(level.getLevelId()));
		level = lvReader.getLevel();
		
		this.mEngine.unregisterUpdateHandler(updateTimer);
		
		this.mEngine.setScene(onAssetsLoaded());
		
	}
	
	private void createScoreScene(){
		
		scoreScene = new Scene() ;
		
		scoreScene.setBackground(new SpriteBackground(new Sprite(0,0,this.levelPack.getTexturePackTextureRegionLibrary().get(LevelSceneFactory.BG_ID))));
		
		final AlignedText text = new AlignedText(0, 50, bigFont, "CONGRATULATIONS!!!", HorizontalAlign.CENTER, VerticalAlign.CENTER, 800, 60);
		
		scoreScene.attachChild(text);
		
		new Button(scoreScene, 150, 300, 250, 75, getString(R.string.nextlevel), buttonTexture, Stroke){
			@Override
			public boolean onButtonPressed(){
				nextLevel();
				return true;
			}
		};
		new Button(scoreScene, 400, 300, 250, 75, getString(R.string.reset), buttonTexture, Stroke){
			@Override
			public boolean onButtonPressed(){
				resetLevel();
				return true;
			}
		};
		new Button(scoreScene, 275, 375, 250, 75, getString(R.string.mainmenu), buttonTexture, Stroke){
			@Override
			public boolean onButtonPressed(){
				GameActivity.this.finish();
				return true;
			}
		};
	
	}
	
	private void loadScoreScene(){
		this.mEngine.unregisterUpdateHandler(updateTimer);
		
		this.mItems.hide();
		
		for(int i = 1; i <= stars; i++){
		final Sprite star = new Sprite(80+(i*128), 175, starFull);	
		scoreScene.attachChild(star);
		}
		
		for(int i = stars+1; i <= 3; i++){
		final Sprite star = new Sprite(80+(i*128), 175, starBlank);	
		scoreScene.attachChild(star);	
		}
		
		this.mEngine.setScene(scoreScene);
	}


}











