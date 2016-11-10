package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.buttons.Button;
import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.rendering.RenderImage;

public class HowToPlayScreen extends Screen {
	private static final int MARGIN = 5, HELP_TEXT_MARGIN = 25;
	public HowToPlayScreen() {
		Texture helpText = new Texture(Gdx.files.internal("HowToPlayPage.png"));
		Texture backButtonTexture = new Texture(Gdx.files.internal("Back.png"));
		Texture backButtonTextureClicked = new Texture(Gdx.files.internal("BackClicked.png"));
		RenderImage helpImage = new RenderImage(helpText, Gdx.graphics.getBackBufferWidth() / 2, 
				Gdx.graphics.getBackBufferHeight() - helpText.getHeight() - HELP_TEXT_MARGIN);
		Button backButton = new SceneLaunchButton(backButtonTexture, backButtonTextureClicked,
							MARGIN, MARGIN, getScreen("MainMenu"));
		renderObjects.add(helpImage);
		renderObjects.add(backButton);
	}
}
