package com.kilobolt.GameWorld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.kilobolt.GameObjects.Obstacle;

public class Level {
	//public List<Obstacle> obstacles;
	public List<Vector2> positions;
	public Vector2 goalPos;
	public boolean isWon;
	
	public Level(){
		//obstacles = new ArrayList<Obstacle>();
		positions = new ArrayList<Vector2>();
		isWon = false;
	}
}
