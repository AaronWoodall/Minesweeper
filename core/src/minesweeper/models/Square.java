package minesweeper.models;

public class Square {
	
	private boolean hasBomb = false;
	private boolean isFlagged = false;
	private boolean isRevealed = false;
	private int squareValue = 0;

	public boolean getHasBomb() {
		return hasBomb;
	}
	
	public void setHasBomb(boolean hasBomb){
		this.hasBomb = hasBomb;
		setSquareValue(-1);
	}

	public boolean getIsFlagged() {
		return isFlagged;
	}

	public void setIsFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public boolean getIsRevealed() {
		return isRevealed;
	}

	public void setIsRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}

	public int getSquareValue() {
		return squareValue;
	}

	public void setSquareValue(int squareValue) {
		this.squareValue = squareValue;
	}

}