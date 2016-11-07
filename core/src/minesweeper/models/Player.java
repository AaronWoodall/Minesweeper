package minesweeper.models;

public class Player {
	
	private String name;
	private int playerWins = 0;
	
	public Player(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(int playerWins) {
		this.playerWins = playerWins;
	}

}
