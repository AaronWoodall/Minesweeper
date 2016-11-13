package minesweeper.game.buttons;

import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.screensystem.PlayGameScreen;

public class SetterButton extends Button {

	private double value = 0;
	private SetType type;
	private String buttonName;
	
	public enum SetType {
		DIFFICULTY, SIZE;
	}
	
	public SetterButton(Texture texture, float xPosition, float yPosition, double boardSizes, SetType type, String buttonName) {
		super(texture, xPosition, yPosition);
		this.type = type;
		this.value = boardSizes;
		this.buttonName = buttonName;
	}

	@Override
	public void onClick() {
		switch (type) {
			case DIFFICULTY:
				PlayGameScreen.setDifficulty(value);
				PlayGameScreen.clickDifficulty(buttonName);
				break;
			case SIZE:
				PlayGameScreen.setMinePercentage(value);
				PlayGameScreen.clickSize(buttonName);
				break;
		}
	}

	@Override
	public void onRelease() {
		
	}
}
