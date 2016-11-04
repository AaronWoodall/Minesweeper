package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import minesweeper.game.Minesweeper;
import minesweeper.game.buttons.Button;
import minesweeper.game.rendering.RenderObject;

public class MainMenuScreen extends Screen {
    private static final String[] buttonNames = {
            "Quit.png", "HowToPlay.png", "QuickStart.png", "PlayGame.png"
    };

    private static final int BUTTON_MARGIN = 10;

    public MainMenuScreen() {
        placeButtons();
    }

    private void placeButtons() {
        for (int buttonIndex = 0; buttonIndex < buttonNames.length; buttonIndex++) {
            String textureName = buttonNames[buttonIndex];
            Texture buttonTexture = new Texture(Gdx.files.internal(textureName));
            int xPosition = (ScreenManager.DEFAULT_ORTHO_WIDTH / 2) - (buttonTexture.getWidth() / 2);
            int yPosition = (BUTTON_MARGIN * 8) + (buttonTexture.getHeight() + BUTTON_MARGIN) * buttonIndex;
            renderObjects.add(new Button(buttonTexture, xPosition, yPosition));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();

        super.getOrthographicCamera().setToOrtho(false, ScreenManager.DEFAULT_ORTHO_WIDTH, ScreenManager.DEFAULT_ORTHO_HEIGHT);
        super.getOrthographicCamera().update();

        for (RenderObject renderObject : renderObjects) {
            Color renderTint = Color.WHITE;
            if (renderObject instanceof Button) {
                Button button = (Button)renderObject;
                if (button.isMouseOver) {
                    renderTint = Color.LIGHT_GRAY;
                }
            }
            spriteBatch.setColor(renderTint);

            Rectangle renderRectangle = renderObject.getRectangle();

            spriteBatch.draw(renderObject.getTexture(), renderRectangle.getX(), renderRectangle.getY());
        }
        spriteBatch.end();
    }
}
