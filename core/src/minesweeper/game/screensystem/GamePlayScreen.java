package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import minesweeper.controllers.BoardGenerationController;
import minesweeper.game.buttons.GameButton;
import minesweeper.game.rendering.RenderObject;
import minesweeper.models.Square;

public class GamePlayScreen extends Screen {
	private double difficulty, boardSize;
	private int counter = 0;
	private BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
	private BoardGenerationController boardGenerationController;
	
	public BoardGenerationController getBoardGenerationController() {
		return boardGenerationController;
	}

	public GamePlayScreen() {
		difficulty = PlayGameScreen.getDifficulty();
		boardSize = PlayGameScreen.getMinePercentage();
		
		boardGenerationController = new BoardGenerationController((int)(boardSize * boardSize), difficulty);
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				counter += 1;
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

	public void placeGameButtons() {
		Texture referenceTexture = new Texture(Gdx.files.internal("Unrevealed.png"));
		int textureWidth = referenceTexture.getWidth();
		int textureHeight = referenceTexture.getHeight();
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				int xPos = (int) (textureWidth * column + ((textureWidth * boardSize - Gdx.graphics.getBackBufferWidth()) * -1));
				GameButton button = new GameButton(xPos, textureHeight * row, row, column);
				renderObjects.add(button);
				button.updateSquare();
			}
		}
	}
	
	public void refresh() {
		for (RenderObject ro : renderObjects) {
			if (ro instanceof GameButton) {
				Square square = ((GameButton)ro).getThisSquare();
				if (square.getIsRevealed()) {
					((GameButton)ro).correctTexture();
				}
			}
		}
	}
}
