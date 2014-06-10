package com.kilobolt.GameWorld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.kilobolt.GameObjects.Goal;
import com.kilobolt.GameObjects.GravBot;
import com.kilobolt.GameObjects.Obstacle;
import com.kilobolt.TweenAccessors.Value;
import com.kilobolt.TweenAccessors.ValueAccessor;
import com.kilobolt.ZBHelpers.AssetLoader;
import com.kilobolt.ZBHelpers.InputHandler;
import com.kilobolt.ui.SimpleButton;

import static com.kilobolt.ZBHelpers.B2DVars.PPM;


public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private OrthographicCamera b2dCam;
	private ShapeRenderer shapeRenderer;
	//private Box2DDebugRenderer b2dr;
	
	private SpriteBatch batcher;

	private int midPointY;

	// Game Objects
	private GravBot gravBot;
	private List<Obstacle> obs;
	private Goal goal;

	// Game Assets
	private TextureRegion bg, grass, birdMid, skullUp, skullDown, bar, ready,
			zbLogo, gameOver, highScore, scoreboard, star, noStar, retry;
	private Animation birdAnimation;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons;
	private Color transitionColor;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		//b2dr = new Box2DDebugRenderer();
		
		this.midPointY = midPointY;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 132  , gameHeight );

		//needed to render box2D Objects
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(true, 132 / PPM , gameHeight / PPM);
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

	}

	private void initGameObjects() {
		gravBot = myWorld.getGravBot();
		obs = myWorld.obs;
		goal = myWorld.goal;
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
		ready = AssetLoader.ready;
		zbLogo = AssetLoader.zbLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
		star = AssetLoader.star;
		noStar = AssetLoader.noStar;
	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22, midPointY - 30, 97, 37);

		batcher.draw(noStar, 25, midPointY - 15, 10, 10);
		batcher.draw(noStar, 37, midPointY - 15, 10, 10);
		batcher.draw(noStar, 49, midPointY - 15, 10, 10);
		batcher.draw(noStar, 61, midPointY - 15, 10, 10);
		batcher.draw(noStar, 73, midPointY - 15, 10, 10);

		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25, midPointY - 15, 10, 10);
		}

		int length = ("" + myWorld.getScore()).length();

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
				104 - (2 * length), midPointY - 20);

		int length2 = ("" + AssetLoader.getHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				104 - (2.5f * length2), midPointY - 3);

	}

	private void drawRetry() {
		batcher.draw(retry, 36, midPointY + 10, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 50, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 82);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 83);
	}

	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 50, 96, 14);
	}
	
	private void drawHud(){

		batcher.begin();
		//batcher.disableBlending();
		
		//batcher.enableBlending();
		String speedY = formatSpeedString( gravBot.GetVelocity().y );
		String speedX = formatSpeedString( gravBot.GetVelocity().x );
		
		//Y velocity UI
		AssetLoader.shadow.draw(batcher, "Y:" + speedY, (10 ) - ( 3 * speedY.length() - 1), 12);
		AssetLoader.font.draw(batcher, "Y:" + speedY, (10 ) - ( 3 * speedY.length() - 1), 11);

		//X velocity UI
		AssetLoader.shadow.draw(batcher, "X:" + speedX, (136 - 40 ) - ( 3 * speedY.length() - 1), 12);
		AssetLoader.font.draw(batcher, "X:" + speedX, (136 - 40  ) - ( 3 * speedY.length() - 1), 11);
		
		
		//Number of Swipes UI-- need to find a way to keep count of swipes
		//AssetLoader.shadow.draw(batcher, "Swipes:" + speedX, (136 - 40 ) - ( 3 * speedY.length() - 1), 100);
		//AssetLoader.font.draw(batcher, "Swipes" + speedX, (136 - 40  ) - ( 3 * speedY.length() - 1), 99);
		
		batcher.end();
		
	}
	
	//format speed font strings correctly
	private String formatSpeedString( float speed ){
		String number = speed + "";
	
		if( number.length() <= 3 ){
			return number;
		}
			
		if( speed > 0f ){	
			if( speed < 10f )
				return number.substring(0 , 3);
			else
				return number.substring(0 , 4);
		}else{
			if( speed > -10f )
				return number.substring(0 , 4);
			else
				return number.substring(0 , 5);
		}
	
		
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		///renderering our b2d colliders and gravbot 
		batcher.setProjectionMatrix( cam.combined );
		gravBot.render(batcher);
		
		for (Obstacle obstacles : obs) {
			obstacles.render(batcher);
		}
		goal.render(batcher);
		
		//draw UI hud
		drawHud();
		//b2dr.render(myWorld.gameWorldPhysics, b2dCam.combined);

	}


}
