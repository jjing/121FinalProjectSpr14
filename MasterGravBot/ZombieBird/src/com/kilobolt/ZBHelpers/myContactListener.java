package com.kilobolt.ZBHelpers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.kilobolt.GameObjects.Goal;
import com.kilobolt.GameObjects.GravBot;
import com.kilobolt.GameObjects.Obstacle;

public class myContactListener implements ContactListener{
	public myContactListener(){}

	@Override
	public void beginContact(Contact contact) {
	    Body a=contact.getFixtureA().getBody();
	    Body b=contact.getFixtureB().getBody();
	    a.getUserData();
	    if(a.getUserData() instanceof GravBot && b.getUserData() instanceof Goal)
	    {
	    	//TODO: make code to signal level end.
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
