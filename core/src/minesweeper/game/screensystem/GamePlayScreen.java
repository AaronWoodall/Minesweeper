package minesweeper.game.screensystem;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import minesweeper.controllers.BoardGenerationController;
import minesweeper.game.Minesweeper;
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
		font.draw(spriteBatch, "Flag Counter: " + boardGenerationController.getBoard().getFlagCount(), 535, 575);
		font.draw(spriteBatch, "Time: " + String.valueOf(counter), 25, 575);
		spriteBatch.end();
	}

	public void placeGameButtons() {
		Texture referenceTexture = new Texture(Gdx.files.internal("Unrevealed.png"));
		int textureWidth = referenceTexture.getWidth();
		int textureHeight = referenceTexture.getHeight();
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				int xPos = (int) ((textureWidth * column) + (Gdx.graphics.getBackBufferWidth() / 2) - ((referenceTexture.getWidth() / 2) * (boardSize)));
				int yPos = (int) ((textureHeight * row) + (Gdx.graphics.getBackBufferHeight() / 2) - ((referenceTexture.getHeight() / 2) * (boardSize))) - textureHeight;
				GameButton button = new GameButton(xPos, yPos, row, column);
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
	
	private boolean delayWinPopup = true; 
	public void gameOver(boolean won) {
		revealAll();
		if (!delayWinPopup) {
			delayWinPopup = true;
			String message = (won ? "You won!" : "You revealed a bomb, you lost!");
			String subMessage = "Would you like to return to the Main Menu?";
			int reply = JOptionPane.showConfirmDialog(null, message + "\n" + subMessage, message, JOptionPane.YES_NO_OPTION);
			
	        if (reply == JOptionPane.YES_OPTION) {
	        	Minesweeper.getInstance().getScreenManager().setCurrentScreen(new MainMenuScreen());
	        } else {
	        	JOptionPane.showMessageDialog(null, "Thanks for playing!");
	        	System.exit(0);
	        }
		} else {
			Timer.schedule(new Task() {
				@Override
				public void run() {
					gameOver(won);
				}
			}, 0.1f);
		}
		delayWinPopup = false;
	}
	
	private void revealAll() {
		Square[][] squares = boardGenerationController.getBoard().getSquares();
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				squares[row][col].setIsRevealed(true);
				refresh();
			}
		}
	}
}
