package minesweeper.game.screensystem;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import minesweeper.game.buttons.Button;
import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.rendering.RenderObject;

public class MainMenuScreen extends Screen {
    private static final String[] buttonNames = {
            "Quit", "HowToPlay", "QuickStart", "PlayGame"
    };

    private static final int BUTTON_MARGIN = 10;

    public MainMenuScreen() {
        placeButtons();
    }

    private void placeButtons() {
        for (int buttonIndex = 0; buttonIndex < buttonNames.length; buttonIndex++) {
            String textureName = buttonNames[buttonIndex];
            Texture upTexture = new Texture(Gdx.files.internal(textureName + ".png"));
            Texture downTexture = new Texture(Gdx.files.internal(textureName + "Clicked.png"));
            int xPosition = (ScreenManager.DEFAULT_ORTHO_WIDTH / 2) - (upTexture.getWidth() / 2);
            int yPosition = (BUTTON_MARGIN * 8) + (upTexture.getHeight() + BUTTON_MARGIN) * buttonIndex;
            
            String className = textureName;
            
            renderObjects.add(new SceneLaunchButton(upTexture, downTexture, xPosition, yPosition, getScreen(className)));
        }
    }
    
    private Class<Screen> getScreen(String screenName) {
    	Class<Screen> screen = null;
    	try {
			screen = (Class<Screen>) Class.forName("minesweeper.game.screensystem." + screenName + "Screen");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find screen action for button [" + screenName + "]");
			e.printStackTrace();
		}
    	return screen;
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
