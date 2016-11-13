package minesweeper.game.buttons;

import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.screensystem.PlayGameScreen;

public class SetterButton extends Button {

	private double value = 0;
	private SetType type;
	private String buttonName;
	private Texture upTexture, downTexture;
	
	public enum SetType {
		DIFFICULTY, SIZE;
	}
	
	public SetterButton(Texture texture, Texture clickedTexture, float xPosition, float yPosition, double boardSizes, SetType type, String buttonName) {
		super(texture, xPosition, yPosition);
		this.type = type;
		this.value = boardSizes;
		this.buttonName = buttonName;
		upTexture = texture;
		downTexture = clickedTexture;
	}

	@Override
	public void onClick() {
		this.setTexture(downTexture);
		switch (type) {
			case DIFFICULTY:
				PlayGameScreen.setDifficulty(value);
				break;
			case SIZE:
				PlayGameScreen.setMinePercentage(value);
				break;
		}
	}

	@Override
	public void onRelease() {
		
	}
}
