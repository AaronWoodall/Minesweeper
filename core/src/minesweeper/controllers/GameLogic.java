package minesweeper.controllers;

import java.util.ArrayList;

import minesweeper.game.Minesweeper;
import minesweeper.game.screensystem.*;
import minesweeper.models.Board;
import minesweeper.models.Square;
import minesweeper.views.ViewBoard;

public class GameLogic {

	private Board board;
	private UserInteraction ui;
	private ArrayList<Square> squaresChecked;

	/**
	 * This generates all necessary things for the logic of the game
	 * @param board - the board that is being used in this game
	 */
	public GameLogic(Board board){
		this.board = board;
//		ui = new UserInteraction();
		squaresChecked = new ArrayList<Square>();
	}

	/**
	 * This runs the game of minesweeper by displaying the board and taking a turn to reveal a square,
	 * it ends when the gameOver() method returns true
	 */
	public void runMinesweeper(){
		ViewBoard displayBoard = new ViewBoard();
		do{
			displayBoard.printBoard(board);
			selectSquare();
		}while(!gameOver());
		displayBoard.printBoard(board);
	}

	/**
	 * This returns true when the game is over by either hitting a bomb or
	 * all flags have been correctly placed
	 * @return - returns whether the game is over or not
	 */
	public boolean gameOver(){
		boolean gameOver = false;
		boolean win = false;
		ArrayList<Square> squares = fillSquareArrayList();
		for(int i = 0; i < squares.size() && !gameOver; i++){
			if(squares.get(i).getHasBomb() && squares.get(i).getIsRevealed()){
				gameOver = true;
			}
		}
		if(board.getFlagCount() == 0 && !gameOver){
			boolean bombWithoutFlag = false;
			for(int i = 0; i < squares.size() && !bombWithoutFlag; i++){
				if(squares.get(i).getHasBomb() && !squares.get(i).getIsFlagged()){
					bombWithoutFlag = true;
				}
			}
			if(!bombWithoutFlag){
				gameOver = true;
				win = true;
			}
		}	
		
		if (gameOver) {
			((GamePlayScreen)Minesweeper.getInstance()
										.getScreenManager()
										.getCurrentScreen())
										.gameOver(win);
		}
		return gameOver;
	}

	/**
	 * This is a helper method to clean up some for loops in a method
	 * @return - returns the list of squares used in gameOver()
	 */
	private ArrayList<Square> fillSquareArrayList(){
		ArrayList<Square> squares = new ArrayList<Square>();
		for(int i = 0; i < board.getSquares().length; i++){
			for(int j = 0; j < board.getSquares()[i].length; j++){
				squares.add(board.getSquare(i, j));
			}
		}
		return squares;
	}

	/**
	 * This allows a user to select a square
	 * @return - returns the square selected by the user
	 */
	private Square selectSquare(){
		int row = ui.promptForInt("Which row would you like to select", -1, board.getSquares().length);
		int column = ui.promptForInt("Which column would you like to select", -1, board.getSquares()[0].length);
		Square square = board.getSquare(row, column);
		squareAction(square, row, column);
		return square;
	}

	/**
	 * This allows the user to select the action they want to take on a square
	 * @param square - square user has selected
	 * @param row - row where the square is
	 * @param column - column where the square is
	 */
	private void squareAction(Square square, int row, int column){
		String[] flaggedOptions = {"Yes", "No"};
		String[] unFlaggedOptions = {"Reveal Square", "Flag Square"};

		int selection;
		if(square.getIsRevealed()){
			ui.respondToUser("That square has already been revealed");
		}
		else{
			if(square.getIsFlagged()){
				ui.respondToUser("Would you like to unflag this square?");
				ui.promptOptions(flaggedOptions);
				selection = ui.promptForInt("Selection: ", -1, flaggedOptions.length);

				if(selection == 0){
					unFlagSquare(square);
				}
			}

			else{
				ui.respondToUser("What would you like to do with this square?");
				ui.promptOptions(unFlaggedOptions);
				selection = ui.promptForInt("Selection: ", -1, unFlaggedOptions.length);
				if(selection == 0){
					revealSquare(square);
					checkAdjacentSquares(row, column);
					squaresChecked.clear();
				}
				else if(selection == 1){
					flagSquare(square);
				}
			}
		}		
	}

	/**
	 * This flags a square
	 * @param square - square user is flagging
	 */
	private void flagSquare(Square square){
		square.setIsFlagged(true);
		board.setFlagCount(board.getFlagCount() - 1);
	}

	/**
	 * This unflags a square
	 * @param square - square user is unflagging
	 */
	private void unFlagSquare(Square square){
		square.setIsFlagged(false);
		board.setFlagCount(board.getFlagCount() + 1);
	}

	/**
	 * This reveals a square
	 * @param square - square user is revealing
	 */
	private void revealSquare(Square square){
		square.setIsRevealed(true);
	}
	
	private void revealSquare(int row, int column){
		if(row > -1 && row < board.getRows() && column > -1 && column < board.getColumns()){
			if(!board.getSquare(row, column).getHasBomb() && !board.getSquare(row, column).getIsFlagged()){
				board.getSquare(row, column).setIsRevealed(true);
			}			
		}		
	}

	/**
	 * This recursively calls itself, grabbing new squares around the original square,
	 * it keep revealing squares until it runs out of places to reveal
	 * @param row - row where the square is
	 * @param column - column where the square is
	 */
	public void checkAdjacentSquares(int row, int column){
		if(row > -1 && row < board.getRows() && column > -1 && column < board.getColumns()){
			if(!squaresChecked.contains(board.getSquare(row, column))){
				squaresChecked.add(board.getSquare(row, column));
				if(board.getSquare(row, column).getSquareValue() == '0'){
					checkAdjacentSquares(row - 1, column - 1);   // NW
					checkAdjacentSquares(row, column - 1);       // N
					checkAdjacentSquares(row + 1, column - 1);   // NE
					checkAdjacentSquares(row - 1, column);       // W
					checkAdjacentSquares(row + 1, column);       // E
					checkAdjacentSquares(row - 1, column + 1);   // SW
					checkAdjacentSquares(row, column + 1);       // S
					checkAdjacentSquares(row + 1, column + 1);   // SE
				}				
			}		
			else if(board.getSquare(row, column).getSquareValue() == '0'){
				if(!board.getSquare(row, column).getIsFlagged()){
					revealSquare(row - 1, column - 1);   // NW
					revealSquare(row, column - 1);       // N
					revealSquare(row + 1, column - 1);   // NE
					revealSquare(row - 1, column);       // W
					revealSquare(row + 1, column);       // E
					revealSquare(row - 1, column + 1);   // SW
					revealSquare(row, column + 1);       // S
					revealSquare(row + 1, column + 1);   // SE
				}				
			}
		}
	}
}