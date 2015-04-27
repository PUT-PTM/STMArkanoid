package com.arkanoid.stm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arkanoid.stm.game.ArkanoidGdx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "ARKANOID # STM32f4 # PTM 2015 - Krasowski Mikolaj, Przadka Grzegorz";
		config.width = 600;
		config.height = 800;

		new LwjglApplication(new ArkanoidGdx(), config);
	}
}
