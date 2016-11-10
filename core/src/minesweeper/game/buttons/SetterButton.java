package minesweeper.game.buttons;

import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.screensystem.PlayGameScreen;

public class SetterButton extends Button {

	private int value = 0;
	private SetType type;
	
	public enum SetType {
		DIFFICULTY, SIZE;
	}
	
	public SetterButton(Texture texture, float xPosition, float yPosition, int value, SetType type) {
		super(texture, xPosition, yPosition);
		this.type = type;
		this.value = value;
	}

	@Override
	public void onClick() {
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
