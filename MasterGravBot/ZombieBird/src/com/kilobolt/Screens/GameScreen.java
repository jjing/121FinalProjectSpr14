package com.kilobolt.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kilobolt.GameWorld.GameRenderer;
import com.kilobolt.GameWorld.GameWorld;
import com.kilobolt.ZBHelpers.InputHandler;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen, GestureListener {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	// This is the constructor, not the class declaration
	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(midPointY);
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		world.setRenderer(renderer);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override public boolean fling(float velocityX, float velocityY, int button) {
		if (velocityY < -100) GameWorld.gameWorldPhysics.setGravity( new Vector2(0,1) );
		else if (velocityY > 100)  GameWorld.gameWorldPhysics.setGravity( new Vector2(0,-1) );
		else if (velocityX < -100) GameWorld.gameWorldPhysics.setGravity( new Vector2(-1,0) );
		else if (velocityX > 100)  GameWorld.gameWorldPhysics.setGravity( new Vector2(1,0) );
		return false;
	}

	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}
	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false;}
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean panStop(float x, float y, int pointer, int button) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}
}
