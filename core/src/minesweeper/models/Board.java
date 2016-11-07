package minesweeper.models;

public class Board {
	
	private Square[][] squares;
	
	public Board(int rows, int columns){
		squares = new Square[rows][columns];
	}
	
	public void setSquare(int row, int column, Square square){
		squares[row][column] = square;
	}
	
	public Square getSquare(int row, int column){
		return squares[row][column];
	}
	
	public Square[][] getSquares(){
		return squares;
	}

}
