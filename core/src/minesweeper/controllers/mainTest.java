package minesweeper.controllers;

import minesweeper.views.ViewBoard;

public class mainTest {
	
	public static void main(String[] args) {
		BoardGenerationController boardGeneration = new BoardGenerationController(100, .01);
		GameLogic logic = new GameLogic(boardGeneration.getBoard());
		logic.runMinesweeper();
	}

}
