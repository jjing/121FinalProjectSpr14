package com.kilobolt.ZombieBird;

import com.badlogic.gdx.Game;
import com.kilobolt.Screens.SplashScreen;
import com.kilobolt.ZBHelpers.AssetLoader;

public class ZBGame extends Game {

	private boolean inMainMenu;
	@Override
	public void create() {
		AssetLoader.load();
		inMainMenu = true;
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}