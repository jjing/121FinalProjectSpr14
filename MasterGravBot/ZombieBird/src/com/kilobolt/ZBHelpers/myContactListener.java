package com.kilobolt.ZBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.kilobolt.GameObjects.Goal;
import com.kilobolt.GameObjects.GravBot;
import com.kilobolt.GameWorld.GameWorld;
import com.kilobolt.GameWorld.Level;

public class myContactListener implements ContactListener{
	private GameWorld myWorld;
	
	public myContactListener(GameWorld world) {
		myWorld = world;
	}

	@Override
	public void beginContact(Contact contact) {
	    Body a=contact.getFixtureA().getBody();
	    Body b=contact.getFixtureB().getBody();
	    a.getUserData();
	    if(a.getUserData().equals("Gravbot") && b.getUserData().equals("Goal"))
	    {
	    	//TODO: make code to signal level end.
	    	myWorld.levels.get(myWorld.getCurrentLevel() - 1).isWon = true;
	    }
	    if(a.getUserData().equals("Goal") && b.getUserData().equals("Gravbot"))
	    {
	    	//TODO: make code to signal level end.
	    	myWorld.levels.get(myWorld.getCurrentLevel() - 1).isWon = true;
	    }
	}

	@Override
	public void endContact(Contact contact) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	    // TODO Auto-generated method stub

	}
}
