package com.kilobolt.ZBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//we can keep this to load all assest or manuel have them added to each class as we create them within there class
public class AssetLoader {

	public static Texture texture, gravTextTex, botTextTex, touchTextTex;
	public static TextureRegion zbLogo, gravText ,botText, touchText;
	public static Animation birdAnimation;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, shadow, whiteFont;
	private static Preferences prefs;

	public static void load() {	

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		//-------------------------Load MainMenu Assets---------------------------------//
		
		gravTextTex = new Texture(Gdx.files.internal("data/gravTitleText.png"));
		gravTextTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gravText = new TextureRegion( gravTextTex, 0 , 0, 512, 128 );
		
		botTextTex = new Texture(Gdx.files.internal("data/botTitleText.png"));
		botTextTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		botText = new TextureRegion( botTextTex, 0 , 0, 512, 128 );
		
		touchTextTex = new Texture(Gdx.files.internal("data/touchToPlayText.png"));
		touchTextTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		touchText = new TextureRegion( touchTextTex, 0 , 0, 256, 128 );

		zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
		zbLogo.flip(false, true);

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();

		font.dispose();
		shadow.dispose();
	}

}