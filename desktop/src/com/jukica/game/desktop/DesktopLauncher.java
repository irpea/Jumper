package com.jukica.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jukica.game.Jumper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Jumper.WIDTH;
		config.height = Jumper.HEIGHT;
		config.title = Jumper.TITLE;
		new LwjglApplication(new Jumper(), config);
	}
}
