package com.kilobolt.GameWorld;

import static com.kilobolt.ZBHelpers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kilobolt.GameObjects.GravBot;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.kilobolt.ZBHelpers.AssetLoader;


public class GameWorld {

	private GravBot gravBot;
	private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;
	public World gameWorldPhysics; 
	
	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		gameWorldPhysics = new World( new Vector2( 0 , 9 ) , true );
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		createGravBot();
		ground = new Rectangle(0, midPointY + 66, 137, 11);
	}

	public void update(float delta) {
		gameWorldPhysics.step(delta, 3, 3);
		gravBot.update(delta);
		runTime += delta;
		
		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}
	

	private void updateReady(float delta) {
		//bird.updateReady(runTime);
		//scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		//bird.update(delta);
		//scroller.update(delta);

	}

	public GravBot getGravBot() {
		return gravBot;

	}

	public int getMidPointY() {
		return midPointY;
	}
	
	private void createGravBot(){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
			
		//create gravBot
		bdef.position.set( 100 / PPM , 100 / PPM );
		bdef.type = BodyType.DynamicBody;
		Body body = gameWorldPhysics.createBody(bdef);
		
		shape.setAsBox( 50 / PPM ,  5 / PPM );
		fdef.shape = shape;
		body.createFixture(fdef);
		
		//create GravBot
		gravBot = new GravBot( body );
			
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		score = 0;
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

}
