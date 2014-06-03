package com.me.BoxSwipe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.BoxSwipe.BoxSwipeGDXGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BoxSwipe";
		new LwjglApplication(new BoxSwipeGDXGame(), config);
	}
}
