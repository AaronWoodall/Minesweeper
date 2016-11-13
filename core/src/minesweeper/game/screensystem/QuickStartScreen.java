package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minesweeper.game.Minesweeper;
import minesweeper.game.buttons.SceneLaunchButton;

public class QuickStartScreen extends Screen {
	private static final double quickDifficulty = 0.1;
	private static final double quickSize = 8;
	
	public QuickStartScreen() {
		
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);	
		PlayGameScreen.setDifficulty(quickDifficulty);
		PlayGameScreen.setMinePercentage(quickSize);
		Texture texture = new Texture(Gdx.files.internal("PlayGame.png"));
		SceneLaunchButton button = new SceneLaunchButton(texture, texture, 0.0f, 0.0f, getScreen("GamePlay"));
		button.onClick();
	}
	
	
}
