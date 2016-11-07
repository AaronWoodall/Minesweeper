package minesweeper.controllers;

import java.util.ArrayList;
import java.util.Random;

import minesweeper.models.Board;
import minesweeper.models.Square;

public class BoardGenerationController {
	
	private Board board;
	private int boardSize;
	private double bombPercentage;
	private long rows;
	private long columns;
	
	/**
	 * Performs all necessary functions for the class to operate
	 * @param boardSize - size of the board
	 * @param bombPercnetage - percentage representing how many bombs we want 
	 */
	public BoardGenerationController(int boardSize, double bombPercnetage){
		this.boardSize = boardSize;
		this.bombPercentage = bombPercnetage;		
		createBoard();
	}
	
	/**
	 * Initializes the board to the closest value to board size (ie. 130 will make it 11 x 12)
	 */
	private void createBoard(){
		rows = Math.round(Math.sqrt(boardSize));
		columns = Math.round(boardSize / rows);
		board = new Board((int) rows, (int) columns);
		initializeSquares();
		placeBombs();
	}

	/**
	 * Initializes all squares in the 2d Array to replace the nulls
	 */
	private void initializeSquares(){
		for(int i = 0; i < board.getSquares().length; i++){
			for(int j = 0; j < board.getSquares()[i].length; j++){
				board.setSquare(i, j, new Square());
			}
		}
	}
	
	/**
	 * Evaluate how many bombs need to be placed
	 * Places 'x' amount of bombs on the board
	 */
	private void placeBombs(){
		Random random = new Random();
		int bombCount = (int) (boardSize * bombPercentage);
		Square[][] tempSquares =  board.getSquares();
		ArrayList<Square> squares = new ArrayList<Square>();
		for(int row = 0; row < tempSquares.length; row++){
			for(int column = 0; column < tempSquares[row].length; column++){
				squares.add(board.getSquare(row, column));
			}
		}	
		
		for(int i = 0; i < bombCount; i++){
			int randomSquareBomb = random.nextInt(squares.size());	
			squares.get(randomSquareBomb).setHasBomb(true);
			squares.remove(randomSquareBomb);		    
		}	
		
		for(int row = 0; row < tempSquares.length; row++){
			for(int column = 0; column < tempSquares[row].length; column++){
				incrementNumbersAroundBomb(row, column);
			}
		}

	}
	
	/**
	 * This will increment all values around where a bomb has been placed
	 * @param row - row of the 2d array
	 * @param column - column of the 2d array
	 */
	private void incrementNumbersAroundBomb(int row, int column){
		incrementSquareValue(row, column, row - 1, column - 1);   // NW
		incrementSquareValue(row, column, row, column - 1);       // N
		incrementSquareValue(row, column, row + 1, column - 1);   // NE
		incrementSquareValue(row, column, row - 1, column);       // W
		incrementSquareValue(row, column, row + 1, column);       // E
		incrementSquareValue(row, column, row - 1, column + 1);   // SW
		incrementSquareValue(row, column, row, column + 1);       // S
		incrementSquareValue(row, column, row + 1, column + 1);   // SE
	}
	
	/**
	 * This will increment a single squares value when a bomb is placed around it
	 * @param row - row of the 2d array
	 * @param column - column of the 2d array
	 */
	private void incrementSquareValue(int originalRow, int originalColumn, int row, int column){
		if(row > -1 && row < rows && column > -1 && column < columns){
			Square originalSquare = board.getSquare(originalRow, originalColumn);
			Square square = board.getSquare(row, column);
			if(!square.getHasBomb()){
				if(originalSquare.getHasBomb()){
					int squareValue = square.getSquareValue();
					squareValue += 1;
					square.setSquareValue(squareValue);	
				}				
			}		
		}			
	}
	
	/**
	 * This is strictly to test the output of our numbers in
	 * comparison to where the bombs are placed
	 */
	public void printingBoardTest(){
		Square[][] tempSquares =  board.getSquares();
		for(int i = 0; i < tempSquares.length; i++){
			for(int j = 0; j < tempSquares[i].length; j++){
				System.out.print(board.getSquare(i, j).getSquareValue() + "\t");
			}
			System.out.println();
		}
	}

}
