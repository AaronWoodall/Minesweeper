package minesweeper.models;

public class Board {
	
	private Square[][] squares;
	private int flagCount;
	private long rows;
	private long columns;
	
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

	public int getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getColumns() {
		return columns;
	}

	public void setColumns(long columns) {
		this.columns = columns;
	}

}
