package minesweeper.game.screensystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {

    public static final int DEFAULT_ORTHO_WIDTH = 800;
    public static final int DEFAULT_ORTHO_HEIGHT = 600;

    private Screen currentScreen = null;

    public void setCurrentScreen(Screen newScreen) {
        currentScreen = newScreen;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void drawCurrentScreen(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(currentScreen.getOrthographicCamera().combined);
        currentScreen.draw(spriteBatch);
    }

    public void updateCurrentScreen(float deltaTime) {
        currentScreen.update(deltaTime);
    }

}
