package com.kilobolt.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kilobolt.TweenAccessors.SpriteAccessor;
import com.kilobolt.ZBHelpers.AssetLoader;
import com.kilobolt.ZombieBird.ZBGame;

public class SplashScreen implements Screen, InputProcessor{

	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite touchTextSprite;
	private Sprite gravTitleSprite;
	private Sprite botTitleSprite;
	private Sprite sprite;
	private ZBGame game;
	//private GestureDetector gestureDetector;
	

	public SplashScreen(ZBGame game) {
		this.game = game;
		//setup input for the splash screen
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void show() {
		
		//setup TouchTitle text on MainMenu
		touchTextSprite = new Sprite(AssetLoader.touchText);
		touchTextSprite.setColor(1, 1, 1, 0);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float desiredWidth = width * .7f;
		float scale = desiredWidth / touchTextSprite.getWidth();

		touchTextSprite.setSize(touchTextSprite.getWidth() * scale, touchTextSprite.getHeight() * scale);
		touchTextSprite.setPosition((width / 2) - (touchTextSprite.getWidth() / 2), (height / 2)
				- (touchTextSprite.getHeight() * 2));
		
		//setup GravTitle Text on MainMenu
		gravTitleSprite = new Sprite(AssetLoader.gravText);
		gravTitleSprite.setColor(1, 1, 1, 1);

		gravTitleSprite.setSize(gravTitleSprite.getWidth() / 2, gravTitleSprite.getHeight() / 2);
		gravTitleSprite.setPosition((width / 2) - (gravTitleSprite.getWidth() / 2), (height )
				- (gravTitleSprite.getHeight() * 3f));
		
		
		//setup BotTitle Text on MainMenu
		botTitleSprite = new Sprite(AssetLoader.botText);
		botTitleSprite.setColor(1, 1, 1, 1);

		botTitleSprite.setSize(botTitleSprite.getWidth() * scale, botTitleSprite.getHeight() * scale);
		botTitleSprite.setPosition((width / 2) - (botTitleSprite.getWidth() / 2), (height )
				- (botTitleSprite.getHeight() * 3f));
		
		
		setupTween();
		batcher = new SpriteBatch();
	}

	//Tween SplashScreen
	//Then Load GameScreen
	private void setupTween() {
		
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
			
				//game.setScreen(new GameScreen());
		
			}
		};

		
		Tween.to(touchTextSprite, SpriteAccessor.ALPHA, .8f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(Tween.INFINITY, .4f)
				.setCallback(cb).start(manager);
		
		/*Tween.to(gravTitleSprite, SpriteAccessor.ALPHA, .8f).target(1)
		.ease(TweenEquations.easeInOutQuad).repeatYoyo(Tween.INFINITY, .4f)
		.setCallback(cb).start(manager);

		Tween.to(botTitleSprite, SpriteAccessor.ALPHA, .8f).target(1)
		.ease(TweenEquations.easeInOutQuad).repeatYoyo(Tween.INFINITY, .4f)
		.setCallback(cb).start(manager);*/

		
	}

	@Override
	public void render(float delta) {
		manager.update(delta);
		//make background black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		touchTextSprite.draw(batcher);
		gravTitleSprite.draw(batcher);
		botTitleSprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		game.setScreen(new GameScreen());
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
