package com.me.BoxSwipe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BoxSwipeGDXGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	Texture box;
	
	Vector2 position;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		box = new Texture(Gdx.files.internal("Box.jpg"));
		
		position = new Vector2(50, 250);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.input.setInputProcessor(new DirectionGestureDetector(new DirectionGestureDetector.DirectionListener() {
			
			@Override
			public void onUp() {
				position.y = Gdx.graphics.getHeight() + position.y - 1;
			}

			@Override
			public void onRight() {
				position.x = Gdx.graphics.getWidth() - position.x - 5;
			}

			@Override
			public void onLeft() {
				position.x = Gdx.graphics.getWidth() - position.x;
			}

			@Override
			public void onDown() {
				position.y -= 20f;
			}
		}));
		batch.begin();
		batch.draw(box,position.x,position.y);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//super.resize(width, height);
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		//super.pause();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		//super.resume();
	}
	
	
}

