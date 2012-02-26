package com.bugfullabs.qube;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ClickDetector;
import org.anddev.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import com.bugfullabs.qube.util.Button;
import com.bugfullabs.qube.level.Level;
import com.bugfullabs.qube.level.LevelFileReader;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;


/**
 * 
 * @author Bugful Labs
 * @author Grushenko
 * @email  wojciech@bugfullabs.pl
 *
 */

public class LevelSelectActivity extends BaseGameActivity implements IScrollDetectorListener, IOnSceneTouchListener, IClickDetectorListener {
       
	    protected static int CAMERA_WIDTH = 800;
        protected static int CAMERA_HEIGHT = 480;
 
        protected static int FONT_SIZE = 36;
        protected static int PADDING = 100;
        
        protected static int MENUITEMS = 3;

    	public static final int NUMBER_OF_ITEMS = 15;
    	public static final int NUMBER_OF_ITEMS_IN_ROW = 5;
    	
    	public static final float X_OFFSET = 92;
    	public static final float Y_OFFSET = 92;
        
    	public static final float MARGIN_X = CAMERA_WIDTH/2 - ((X_OFFSET)*(NUMBER_OF_ITEMS_IN_ROW/2));
    	public static final float MARGIN_Y = CAMERA_HEIGHT/2 - (((Y_OFFSET)*((NUMBER_OF_ITEMS/NUMBER_OF_ITEMS_IN_ROW)/2)));
    	
        
        private Camera mCamera;
        private Scene mScene;
        private Scene gridScene;
        
        private StrokeFont mFont; 
        private BitmapTextureAtlas mFontTexture;     
 
        private SurfaceScrollDetector mScrollDetector;
        private ClickDetector mClickDetector;
 
        private float mMinX = 0;
        private float mMaxX = 0;
        private float mCurrentX = 0;
        private int iItemClicked = -1;
        
        private BitmapTextureAtlas mAtlas; 
        private TextureRegion Item;
		private TextureRegion mBackground;
		
        //private TexturePack mBackgrounds;
        private TexturePack mLevels; 
           
        private LevelFileReader LevelReader;
        
		public static final int NUMBER_OF_LEVELPACKS = 3;
		
        @Override
        public void onLoadResources() {
                // Paths
        	
                BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
 
                this.mAtlas = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                this.mBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAtlas, this, "bg.png", 0, 0);
        		this.Item = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAtlas, this, "levelitem.png", 800, 0);
        		
        		try {
        			
        			//this.mBackgrounds = new TexturePackLoader(this, "gfx/menu/").loadFromAsset(this, "backgrounds.xml");
    				this.mLevels = new TexturePackLoader(this, "gfx/menu/").loadFromAsset(this, "levels.xml");
        		
        		  } catch (TexturePackParseException e) {
        				e.printStackTrace();
        		}        		  
        		
                this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                Typeface typeface = Typeface.createFromAsset(getAssets(), "font/FOO.ttf");
                this.mFont = new StrokeFont(mFontTexture, typeface, FONT_SIZE, true, Color.WHITE, 2, Color.BLACK);
                
                this.mEngine.getTextureManager().loadTextures(this.mFontTexture, this.mAtlas, /*this.mBackgrounds.getTexture(),*/ this.mLevels.getTexture());
                this.mEngine.getFontManager().loadFont(this.mFont);            
                
        }
 
        @Override
        public Engine onLoadEngine() {
                this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
 
                final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
                engineOptions.getTouchOptions().setRunOnUpdateThread(true);
                return new Engine(engineOptions);
        }
 
        @Override
        public Scene onLoadScene() {
                this.mEngine.registerUpdateHandler(new FPSLogger());
 
                this.mScene = new Scene();
                this.mScene.setBackground(new SpriteBackground(new Sprite(0, 0, this.mBackground)));
               
                this.mScrollDetector = new SurfaceScrollDetector(this);
                this.mClickDetector = new ClickDetector(this);
 
       
                
                this.mScene.setOnSceneTouchListener(this);
                this.mScene.setTouchAreaBindingEnabled(true);
                this.mScene.setOnSceneTouchListenerBindingEnabled(true);
 
                CreateMenuBoxes();
 
                return this.mScene;
 
        }
 
        @Override
        public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
                this.mClickDetector.onTouchEvent(pSceneTouchEvent);
                this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
                return true;
        }
 
        @Override
        public void onScroll(final ScrollDetector pScollDetector, final TouchEvent pTouchEvent, final float pDistanceX, final float pDistanceY) {

        	//Return if ends are reached
                if ( ((mCurrentX - pDistanceX) < mMinX)  ){                	
                    return;
                }else if((mCurrentX - pDistanceX) > mMaxX){
                	
                	return;
                }
                
                //Center camera to the current point
                this.mCamera.offsetCenter(-pDistanceX,0 );
                mCurrentX -= pDistanceX;
                	
               
               //Because Camera can have negativ X values, so set to 0
            	if(this.mCamera.getMinX()<0){
            		this.mCamera.offsetCenter(0, 0);
            		mCurrentX=0;
            	}
            	
 
        }
 
        @Override
        public void onClick(ClickDetector pClickDetector, TouchEvent pTouchEvent) {
                loadLevel(iItemClicked);
        };

        
        private void CreateMenuBoxes() {
        	
             int spriteX = PADDING;
        	 int spriteY = ((CAMERA_HEIGHT/2 - mLevels.getTexturePackTextureRegionLibrary().get(0).getHeight()/2)+PADDING);
        	 
        	 int strings[] = new int[3];
        	 
        	 strings[0] = R.string.levelpack1;
        	 strings[1] = R.string.levelpack2;
        	 strings[2] = R.string.levelpack3;
        	 
        	 
        	 //current item counter
             int iItem = 1;

        	 for (int x = 0; x < LevelSelectActivity.NUMBER_OF_LEVELPACKS; x++) {
        		 
        		 //On Touch, save the clicked item in case it's a click and not a scroll.
                 final int itemToLoad = iItem;

        		 Button button = new Button(mScene, spriteX, spriteY, 256, 256, iItem+"."+getString(strings[x]), mLevels.getTexturePackTextureRegionLibrary().get(x), mFont){
        			 
        			 @Override 
        			 public boolean onButtonPressed(){
        				 iItemClicked = itemToLoad;
        				 return false;
        			 } 
        		 };

        		 spriteX += 20 + PADDING+button.getWidth();
        		 iItem++;
        		 
			}
        	
        	 mMaxX = spriteX - CAMERA_WIDTH;
        }
        
        
 
        @Override
        public void onLoadComplete() {
 
        }
 
        
        //Here is where you call the item load.
        private void loadLevel(final int iLevel) {
                if (iLevel != -1) {
                        runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                		
                                		setLevelSelectGrid(null, Item, iLevel);  
                                		
                                        iItemClicked = -1;
                                }
                        });
                }
        }
        
        
        
        
      private void setLevelSelectGrid(TextureRegion bg, TextureRegion Item, final int iLevel){
    	
    	  gridScene = new Scene();
    	  
    	  //gridScene.setBackground(new SpriteBackground(new Sprite(0, 0, bg)));
  
    	  
    	  int i = 0;
    	  
    	  for(int j = 0; j < (NUMBER_OF_ITEMS/NUMBER_OF_ITEMS_IN_ROW); j++){
    		  
  			for(int k = 0; k < NUMBER_OF_ITEMS_IN_ROW; k++){
  				
  			final int id = i;
  					  
      		  new Button(gridScene, MARGIN_X + (k * X_OFFSET) - (Item.getWidth()/2), MARGIN_Y + (j * Y_OFFSET)- (Item.getHeight()/2),Item.getWidth(), Item.getWidth(), Integer.toString(i+1), Item, mFont ){
      			  
      			  @Override
      			  public boolean onButtonPressed(){
      				LevelSelectActivity.this.onLevelSelected(id, iLevel);
      				return true;
      			  }
      		  };  
      		  i++;
  			}
  		}
    	  
    	  gridScene.setTouchAreaBindingEnabled(true);
    	  
    	  this.mCamera.offsetCenter(-this.mCurrentX, 0);
    	  
    	  this.mEngine.setScene(gridScene);
    	  
      }
    		  
       
      
      private void onLevelSelected(int id, int iLevel)
      {
    	  
    	  this.LevelReader = new LevelFileReader(this, "level_"+Integer.toString(iLevel)+"_"+Integer.toString(id+1));
    	  
    	  final Level level = this.LevelReader.getLevel();
    	  
    	  GameActivity.setLevel(level); 
    	  
    	  //this.setIntent(new Intent(this, GameActivity.class));
    	  
    	  this.startActivity(new Intent(this, GameActivity.class));
    	  this.finish();
    	  
    	  overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
      }
      

       
        
        
}
 