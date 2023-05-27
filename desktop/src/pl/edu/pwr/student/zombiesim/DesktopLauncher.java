package pl.edu.pwr.student.zombiesim;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setWindowedMode(1280, 720);
		config.setResizable(false);
		config.setTitle("Left4Dziekan");

		new Lwjgl3Application(new ZombieSimulation(), config);
	}

}
