package com.bugfullabs.qube;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.bugfullabs.qube.util.AsyncTaskLoader;
import com.bugfullabs.qube.util.IAsyncCallback;
 
import com.bugfullabs.qube.util.ProgressBar; 

/**
 * Written by Tyler Reid of Studio Reveries.
 * 
 * All work provided can be modified and used freely so long as this statement stays in the source code.
 * 
 * 
 * @author Tyler Reid @ www.studioreveries.com
 *
 */
public abstract class LoadingActivity extends BaseGameActivity {
 
	// ===========================================================
	// Fields
	// ===========================================================
 
	private ProgressBar Progress;

	private TextureRegion background;

	private BitmapTextureAtlas mAtlas;

	private Scene scene;
	
	protected Camera mCamera;
	
	// ===========================================================
	// Constants
	// ===========================================================
 
	protected static final float CAMERA_WIDTH = 800, CAMERA_HEIGHT = 480;
 
	// ===========================================================
	// Inherited Methods
	// ===========================================================
 
	@Override
	public Engine onLoadEngine() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	
		final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new FillResolutionPolicy(), mCamera);
        engineOptions.getTouchOptions().setRunOnUpdateThread(true);
        engineOptions.setNeedsMusic(true).setNeedsSound(true);
		return mEngine = new Engine(engineOptions);
	
	}
 
	@Override
	public void onLoadResources() {
		this.mAtlas = new BitmapTextureAtlas(1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		this.background = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAtlas, this, "bg.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(mAtlas);
	}
 
	@Override
	public Scene onLoadScene() {
		scene = new Scene();
	
		Sprite bg = new Sprite(0, 0, background);
		scene.setBackground(new SpriteBackground(bg));
		
		Progress = new ProgressBar(scene, 200, 360, 400, 30, 5);
		Progress.setFrameColor(1, 1, 1, 1);
		Progress.setBarColor(0.345f, 0.764f, 0.876f, 1.0f);
		Progress.setBackgroundColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		/*
		 * Here's where the assets are loaded in the background behind the loading scene.
		 */
		IAsyncCallback callback = new IAsyncCallback() {
 
			@Override
			public void workToDo() {
				assetsToLoad();
			}
 
			@Override
			public void onComplete() {
				unloadLoadingScene();
				mEngine.setScene(onAssetsLoaded());
			}
		};
 
		new AsyncTaskLoader().execute(callback);
 
		return scene;
	}
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
	// ===========================================================
	// Methods
	// ===========================================================
 
	public void setLoadingProgress(float progress){
		Progress.setProgress(progress);
	}
	
	private void unloadLoadingScene(){
	}
 
	protected abstract Scene onAssetsLoaded();
 
 
	protected abstract void assetsToLoad();
 
}