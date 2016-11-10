package minesweeper.views;

import minesweeper.models.Board;
import minesweeper.models.Square;

public class ViewBoard {
	
	/**
	 * This is strictly to test the output of our numbers in
	 * comparison to where the bombs are placed
	 */
	public void printBoard(Board board){
		int rowNumberDisplay = 0;
		int columnNumberDisplay = 0;
		Square[][] tempSquares =  board.getSquares();
		
		for(int k = 0; k < tempSquares[0].length; k++){
			System.out.print(columnNumberDisplay + "\t");
			columnNumberDisplay++;
		}
		
		System.out.println();
		System.out.println();
		
		for(int i = 0; i < tempSquares.length; i++){			
			for(int j = 0; j < tempSquares[i].length; j++){
				if(board.getSquare(i, j).getIsFlagged()){
					System.out.print('F' + "\t");
				}
				else if(!board.getSquare(i, j).getIsRevealed()){
					System.out.print('-' + "\t");
				}
				else{
					System.out.print(board.getSquare(i, j).getSquareValue() + "\t");
				}

			}			
			System.out.print(rowNumberDisplay);
			rowNumberDisplay++;
			System.out.println();
		}
	}

}
