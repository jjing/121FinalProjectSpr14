package com.kilobolt.GameWorld;

import static com.kilobolt.ZBHelpers.B2DVars.PPM;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.kilobolt.GameObjects.GravBot;
import com.kilobolt.GameObjects.Obstacle;
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
	public static World gameWorldPhysics; 
	public float Gravity = 0;
	public List<Obstacle> obs;

	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		gameWorldPhysics = new World( new Vector2( 0 , 0 ) , false );
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		createGravBot();
		createObstacle(16, 120);
		createObstacle(48, 120);
		ground = new Rectangle(0, midPointY + 66, 137, 11);
	}

	//Clamps gravBot to screen bounds
		public void clampToScreen() {
			Vector2 gBVec = gravBot.getPosition();
			float width = gravBot.getWidth();
			float height = gravBot.getHeight();
			
			
			float screenWidth = Gdx.graphics.getWidth();
			float screenHeight = Gdx.graphics.getHeight();
			float gameWidth = 136;
			float gameHeight = screenHeight / (screenWidth / gameWidth);
			

			if (gBVec.x < (width / (PPM * 2))) {
				gravBot.getBody().setTransform(width / (PPM * 2), gBVec.y, 0);
				gravBot.getBody().setLinearVelocity(new Vector2(0,0));
				gravBot.getBody().setAngularVelocity(0);
			}
			if (gBVec.x > (gameWidth - (width / 2)) / PPM) {
				gravBot.getBody().setTransform((gameWidth - (width / 2)) / PPM , gBVec.y, 0);
				gravBot.getBody().setLinearVelocity(new Vector2(0,0));
				gravBot.getBody().setAngularVelocity(0);
			}
			if (gBVec.y < (height / (PPM * 2))) {
				gravBot.getBody().setTransform(gBVec.x, height / (PPM * 2), 0);
				gravBot.getBody().setLinearVelocity(new Vector2(0,0));
				gravBot.getBody().setAngularVelocity(0);
			}
			if (gBVec.y > (gameHeight - (height / 2)) / PPM) {
				gravBot.getBody().setTransform(gBVec.x, (gameHeight - (height / 2)) / PPM, 0);
				gravBot.getBody().setLinearVelocity(new Vector2(0,0));
				gravBot.getBody().setAngularVelocity(0);
			}
			
		}
		
	public void update(float delta) {
		gameWorldPhysics.step(delta, 3, 3);
		gravBot.update(delta);
		
		for (Obstacle obstacles : obs) {
			obstacles.update(delta);
		}
		
		clampToScreen();	
		
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

	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

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
		
		
		shape.setAsBox( 16 / PPM ,  16 / PPM );
		fdef.shape = shape;
		body.createFixture(fdef);
		
		
		//create GravBot
		gravBot = new GravBot( body );
			
	}

	private void createObstacle(float x, float y){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
			
		//create gravBot
		bdef.position.set(x / PPM , y / PPM);
		bdef.type = BodyType.StaticBody;
		Body body = gameWorldPhysics.createBody(bdef);
		
		
		shape.setAsBox( 16 / PPM ,  16 / PPM );
		fdef.shape = shape;
		body.createFixture(fdef);
		
		Obstacle obstacle = new Obstacle(body);
		obs.add(obstacle);
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

	public void setGravity(int i, int j) {
		Vector2 gravF = new Vector2(i,j);
		gameWorldPhysics.setGravity(gravF);
	}

}
