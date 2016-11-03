package minesweeper.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import minesweeper.game.Minesweeper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new DesktopLauncher();
	}

	public DesktopLauncher() {
		launchGui();
	}

	private void launchGui() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Minesweeper(), config);
	}
}
