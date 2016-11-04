package minesweeper.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import minesweeper.game.screensystem.MainMenuScreen;
import minesweeper.game.screensystem.ScreenManager;

public class Minesweeper extends ApplicationAdapter {

    private static int windowWidth, windowHeight;
    private ScreenManager screenManager = new ScreenManager();
    private SpriteBatch spriteBatch;
    private static volatile Minesweeper instance = null;

    public static synchronized Minesweeper getInstance() {
        if (instance == null) {
            instance = new Minesweeper();
        }
        return instance;
    }

	@Override
	public void create () {
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();
        spriteBatch = new SpriteBatch();
        screenManager.setCurrentScreen(new MainMenuScreen());
        instance = this;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.74f, 0.74f, 0.74f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screenManager.drawCurrentScreen(spriteBatch);
        screenManager.updateCurrentScreen(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
        spriteBatch.dispose();
	}

	public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }
}
