package com.kilobolt.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kilobolt.ZBHelpers.B2DSprite;

public class Goal extends B2DSprite {
	public Goal( Body body ){
		super( body );
		Texture tex =  new Texture(Gdx.files.internal("data/Goal.png"));
		TextureRegion[] GravBotAnimations = TextureRegion.split(tex, 32, 32)[0];
		setAnimation( GravBotAnimations , 1 / 12f );
	}
}
