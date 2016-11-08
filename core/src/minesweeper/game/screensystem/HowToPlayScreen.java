package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.rendering.RenderImage;

public class HowToPlayScreen extends Screen {
	public HowToPlayScreen() {
		Texture helpText = new Texture(Gdx.files.internal("HowToPlayPage.png"));
		RenderImage helpImage = new RenderImage(helpText, Gdx.graphics.getBackBufferWidth() / 2, Gdx.graphics.getBackBufferHeight() / 2);
		renderObjects.add(helpImage);
	}
}
