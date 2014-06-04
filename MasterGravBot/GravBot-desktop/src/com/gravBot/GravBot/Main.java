package com.gravBot.GravBot;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gravBot.GravBot.GravBotGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ZombieBird";
		cfg.useGL20 = false;
		cfg.width = 1080 / 3;
		cfg.height = 1920 / 3;
		
		new LwjglApplication(new GravBotGame(), cfg);
	}
}
