package minesweeper.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import minesweeper.game.Minesweeper;

public class DesktopLauncher {

	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 600;

	public static void main (String[] arg) {
		new DesktopLauncher();
	}

	public DesktopLauncher() {
		launchGui();
	}

	private void launchGui() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;

		new LwjglApplication(new Minesweeper(), config);
	}
}
