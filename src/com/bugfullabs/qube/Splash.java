package com.bugfullabs.qube;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.modifier.FadeInModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseSplashActivity;
import org.anddev.andengine.util.modifier.IModifier;

import android.app.Activity;
import android.content.Intent;

public class Splash extends BaseSplashActivity{

    private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;
    
    private Camera mCamera;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mSpriteTextureRegion;
    private TextureRegion mBugfullabsTextureRegion;
    	
	private Sprite picture;
	
	@Override
	public void onLoadComplete() {
	}

	@Override
	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new FillResolutionPolicy(), this.mCamera).setNeedsMusic(true).setNeedsSound(true));
	}

	@Override
	public void onLoadResources() {
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(256, 512, TextureOptions.BILINEAR);
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.mSpriteTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "andengine_logo.png", 0, 256);
        this.mBugfullabsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "bugfullabs.png", 0, 0);
        
        this.mEngine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);		
	}

	@Override
	public Scene onLoadScene() {	 
		 
        final Scene splashScene = new Scene();

      	final float Xpos = (CAMERA_WIDTH/2)-(mBugfullabsTextureRegion.getWidth()/2);
      	final float Ypos = (CAMERA_HEIGHT/2)-(mBugfullabsTextureRegion.getHeight()/2);

      	
      	
 		picture = new Sprite(Xpos, Ypos, this.mBugfullabsTextureRegion);
 		picture.setAlpha(0);
 		splashScene.attachChild(picture);
 		
 	
 		picture.registerEntityModifier(new FadeInModifier(0.5f));
 		
 		splashScene.registerUpdateHandler(new TimerHandler(2.5f, false, new ITimerCallback(){

			@Override
			public void onTimePassed(TimerHandler arg0) {
	
				picture.registerEntityModifier(new FadeOutModifier(0.5f, new IEntityModifier.IEntityModifierListener() {
					
					@Override
					public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
	
					}
					
					@Override
					public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {	
					
				 		picture = new Sprite(Xpos, Ypos, mSpriteTextureRegion);
				 		splashScene.attachChild(picture);
				 		picture.setAlpha(0);
				 		picture.registerEntityModifier(new FadeInModifier(0.5f));
				 		splashScene.clearUpdateHandlers();
				 		splashScene.registerUpdateHandler(new TimerHandler(2.5f, false, new ITimerCallback(){

							@Override
							public void onTimePassed(TimerHandler arg0) {
					
								picture.registerEntityModifier(new FadeOutModifier(0.5f, new IEntityModifier.IEntityModifierListener() {
									
									@Override
									public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
					
									}
									
									@Override
									public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {	
										Intent intent = new Intent(Splash.this, MainMenuActivity.class);
										Splash.this.startActivity(intent);
										Splash.this.finish();
										
									}
								}));		
							}
				 		}
				 		));	
					}
				}));		
			}
 		}
 		));		
		return splashScene;
	}

	@Override
	protected Class<? extends Activity> getFollowUpActivity() {
		return null;
	}

	@Override
	protected ScreenOrientation getScreenOrientation() {
		return null;
	}

	@Override
	protected float getSplashDuration() {
		return 0;
	}

	@Override
	protected IBitmapTextureAtlasSource onGetSplashTextureAtlasSource() {

		return null;
	}
}
	