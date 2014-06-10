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
import com.kilobolt.GameObjects.Goal;
import com.kilobolt.GameObjects.GravBot;
import com.kilobolt.GameObjects.Obstacle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.kilobolt.Screens.SplashScreen;
import com.kilobolt.ZBHelpers.AssetLoader;
import com.kilobolt.ZombieBird.ZBGame;


public class GameWorld {

	private GravBot gravBot;
	public Goal goal;
	public List<Obstacle> obs;
	public List<Level> levels;
	private Rectangle ground;
	private int currentLevel;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;
	public static World gameWorldPhysics; 
	public float Gravity = 0;
	private ZBGame game;
	

	public GameWorld(int midPointY) {
		gameWorldPhysics = new World( new Vector2( 0 , 0 ) , false );
		this.midPointY = midPointY;
		levels = new ArrayList<Level>();
		obs = new ArrayList<Obstacle>();
		createGravBot();
		ground = new Rectangle(0, midPointY + 66, 137, 11);
		currentLevel = 1;
		createLevels();
		startLevel(currentLevel);
	}
	
	public void setGame(ZBGame currGame) {
		game = currGame;
	}

	//Clamps gravBot to screen bounds
	public void clampToScreen() {
		Vector2 gBVec = gravBot.getPosition();
		float width = gravBot.getWidth();
		float height = gravBot.getHeight();


		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 132;
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
		if (levels.get(currentLevel - 1).isWon == true) {
			if(currentLevel >= levels.size()){
				Gdx.app.exit();
			}
			gravBot.getBody().setTransform(.16f, 0, 0);
			gravBot.getBody().setLinearVelocity(new Vector2(0,0));
			gravBot.getBody().setAngularVelocity(0);
			destroyLevel();
			currentLevel++;
			startLevel(currentLevel);
			
		}

		gameWorldPhysics.step(delta, 3, 3);
		gravBot.update(delta);
		for (Obstacle obstacles : obs) {
			obstacles.update(delta);
		}
		clampToScreen();	
		runTime += delta;

		

	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

	}

	public GravBot getGravBot() {
		return gravBot;

	}

	private void createGravBot(){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		//create gravBot
		bdef.position.set(0 / PPM , 0 / PPM );
		bdef.type = BodyType.DynamicBody;
		Body body = gameWorldPhysics.createBody(bdef);


		shape.setAsBox( 16 / PPM ,  16 / PPM );
		fdef.shape = shape;
		body.createFixture(fdef);

		body.setUserData("Gravbot");

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

		body.setUserData("Obstacle");

		Obstacle obstacle = new Obstacle(body);
		obs.add(obstacle);
	}

	private void createGoal(float x, float y){
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

		body.setUserData("Goal");

		goal = new Goal(body);
	}

	public void createLevels(){
		Level level1 = new Level();
		Level level2 = new Level();
		Level level3 = new Level();
		Level level4 = new Level();
		level1.positions.add(new Vector2(16, 120));
		level1.positions.add(new Vector2(48, 120));
		level1.goalPos = new Vector2(16, 188);
		levels.add(level1);
		level2.positions.add(new Vector2(16, 120));
		level2.positions.add(new Vector2(48, 120));
		level2.positions.add(new Vector2(116, 120));
		level2.positions.add(new Vector2(116, 16));
		level2.goalPos = new Vector2(16, 188);
		levels.add(level2);
		level3.positions.add(new Vector2(16, 112));
		level3.positions.add(new Vector2(116, 112));
		level3.positions.add(new Vector2(116, 80));
		level3.positions.add(new Vector2(80, 208));
		level3.goalPos = new Vector2(16, 144);
		levels.add(level3);
		level4.positions.add(new Vector2(16, 80));
		level4.positions.add(new Vector2(116, 112));
		level4.positions.add(new Vector2(80, 208));
		level4.goalPos = new Vector2(116, 176);
		levels.add(level4);
	}

	public void startLevel(int index) {
		Level l = levels.get(index - 1);
		for (Vector2 pos : l.positions) {
			createObstacle(pos.x, pos.y);
		}
		createGoal(l.goalPos.x, l.goalPos.y);
	}

	public void destroyLevel() {
		for (Obstacle obstacle : obs) {
			gameWorldPhysics.destroyBody(obstacle.getBody());
		}
		obs.clear();
		gameWorldPhysics.destroyBody(goal.getBody());
		goal = null;
	}


	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

	public void setGravity(int i, int j) {
		Vector2 gravF = new Vector2(i,j);
		gameWorldPhysics.setGravity(gravF);
	}

	public void setContactListener(ContactListener listener) {
		gameWorldPhysics.setContactListener(listener);
	}

	public int getCurrentLevel() {return currentLevel;}
	
	public int getMidPointY(){
		return midPointY;
	}
}