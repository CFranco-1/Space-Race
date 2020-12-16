package edu.lewisu.cs.cfranco.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.lewisu.cs.cfranco.VgFinalCure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = 480;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new VgFinalCure(), config);
	}
}
