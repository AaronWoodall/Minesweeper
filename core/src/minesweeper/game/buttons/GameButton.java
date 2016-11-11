package minesweeper.game.buttons;

import java.util.function.Supplier;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.controllers.BoardGenerationController;
import minesweeper.controllers.GameLogic;
import minesweeper.game.Minesweeper;
import minesweeper.game.screensystem.GamePlayScreen;
import minesweeper.game.screensystem.Screen;
import minesweeper.models.Board;
import minesweeper.models.Square;

public class GameButton extends CallbackButton {
	
	private int boardRow, boardCol;
	private static int NUMBER_OF_REVEALED_BUTTONS = 7;
	
	private static Texture hiddenTexture = new Texture(Gdx.files.internal("Unrevealed.png"));
	private static Texture revealedTexture = new Texture(Gdx.files.internal("Revealed.png"));
	private static Texture[] numberedTextures = new Texture[NUMBER_OF_REVEALED_BUTTONS];
	private static Texture mineTexture = new Texture(Gdx.files.internal("mine.png"));
	private Square thisSquare;
	
	public Square getThisSquare() {
		return thisSquare;
	}

	static {
		for (int iter = 0; iter < numberedTextures.length; iter++) {
			numberedTextures[iter] = new Texture(Gdx.files.internal("Revealed" + (iter+1) + ".png"));
		}
	}

	public GameButton(float xPosition, float yPosition, int boardRow, int boardCol) {
		super(hiddenTexture, xPosition, yPosition);
		Supplier<Void> callback = this::revealThis;
		super.setCallbackAction(callback);
		this.boardRow = boardRow;
		this.boardCol = boardCol;
	}
	
	public Void revealThis() {
		JOptionPane.showMessageDialog(null, "Reveal click event.");
		return null;
	}
	
	public void updateSquare() {
		Screen screenReference = Minesweeper.getInstance().getScreenManager().getCurrentScreen();
		Board board = ((GamePlayScreen)screenReference).getBoardGenerationController().getBoard();
		thisSquare = board.getSquare(boardRow, boardCol);
	}

	@Override
	public void onClick() {
		Screen screenReference = Minesweeper.getInstance().getScreenManager().getCurrentScreen();
		Board board = ((GamePlayScreen)screenReference).getBoardGenerationController().getBoard();
		thisSquare = board.getSquare(boardRow, boardCol);
		thisSquare.setIsRevealed(true);
		correctTexture();
		GameLogic logic = ((GamePlayScreen)screenReference).getBoardGenerationController()
										 .getLogicController();
		logic.checkAdjacentSquares(boardRow, boardCol);
		((GamePlayScreen)screenReference).refresh();
		logic.gameOver();
	}
	
	public void correctTexture() {
		if (thisSquare.getHasBomb()) {
			setTexture(mineTexture);
		} else if (thisSquare.getSquareValue() == '0') {
			setTexture(revealedTexture);
		} else {
			setTexture(numberedTextures[thisSquare.getSquareValue() - '1']);
		}
	}

	@Override
	public void onRelease() {
		
	}

}
