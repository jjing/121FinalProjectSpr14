package com.gravBot.GravBot;

import com.badlogic.gdx.Game;
import com.gravBot.GBHelpers.AssetLoader;
import com.gravBot.Screens.SplashScreen;

public class GravBotGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}