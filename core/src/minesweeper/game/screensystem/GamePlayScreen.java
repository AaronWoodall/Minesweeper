package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import minesweeper.game.buttons.GameButton;

public class GamePlayScreen extends Screen {
	private int difficulty, boardSize;
	private int counter = 0;
	private BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
	
	public GamePlayScreen() {
		difficulty = PlayGameScreen.getDifficulty();
		boardSize = PlayGameScreen.getMinePercentage();
		
		placeGameButtons();
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				counter += 1;
				System.out.println(counter);
			}
		}, 1, 1);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		spriteBatch.begin();
		font.draw(spriteBatch, String.valueOf(counter), 5, 30);
		spriteBatch.end();
	}

	private void placeGameButtons() {
		Texture referenceTexture = new Texture(Gdx.files.internal("Unrevealed.png"));
		int textureWidth = referenceTexture.getWidth();
		int textureHeight = referenceTexture.getHeight();
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				int xPos = textureWidth * column + ((textureWidth * boardSize - Gdx.graphics.getBackBufferWidth()) * -1);
				GameButton button = new GameButton(xPos, textureHeight * row);
				renderObjects.add(button);
			}
		}
	}
}
