package com.kilobolt.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
//import com.kilobolt.ZBHelpers.AssetLoader;
import com.kilobolt.ZBHelpers.B2DSprite;
//import com.badlogic.gdx.scenes.scene2d.Actor;


//extends from b2dSprite to do all the work of animating sprites
//main player
public class GravBot extends B2DSprite {
	
	public GravBot( Body body ){
		super( body );
		
		//adds sprites for gravBot
		Texture tex =  new Texture(Gdx.files.internal("data/crate.png"));
		TextureRegion gravBot = new TextureRegion(tex , 0 ,0 , 32, 32);
		TextureRegion[] GravBotAnimations = { gravBot};
		setAnimation( GravBotAnimations , 1 / 12f );
	}
	
	//gets current velocity for gravBot
	public Vector2 GetVelocity(){	return body.getLinearVelocity();}
	
	public void MoveUp(){
		
	}
	
	public void MoveDown(){
		
	}
	
	public void MoveRight(){
		
	}
	
	public void MoveLeft(){
		
	}
	
	
	
}