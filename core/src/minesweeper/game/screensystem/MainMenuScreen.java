package minesweeper.game.screensystem;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import minesweeper.game.buttons.Button;
import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.rendering.RenderImage;
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
        
        int height = Gdx.graphics.getBackBufferHeight();
        Texture logoTexture = new Texture(Gdx.files.internal("Title.png"));
        RenderImage logoImage = new RenderImage(logoTexture, Gdx.graphics.getBackBufferWidth() / 2, height - (height / 3));
        renderObjects.add(logoImage);
    }
}
