package com.arkanoid.stm.desktop;

import com.arkanoid.stm.ScreenProperties;
import com.arkanoid.stm.gameStates.ArkanoidGdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Toolkit;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		java.awt.Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		float proportion= (float) (600.0/800.0);
		ScreenProperties screenProperties= new ScreenProperties();
		screenProperties.heightFit= (int) screenSize.getHeight()-100;
		screenProperties.widthFit= (int) (ScreenProperties.heightFit * proportion);

		config.title = "ARKANOID # STM32f4 # PTM 2015 - Krasowski Mikolaj, Przadka Grzegorz";
		config.width = screenProperties.widthFit;
		config.height =  screenProperties.heightFit;
		config.resizable= false;
		config.backgroundFPS=-1;

		new LwjglApplication(new ArkanoidGdx(), config);
	}
}
