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
		this.setButtonName(buttonName);
		upTexture = texture;
		downTexture = clickedTexture;
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
		PlayGameScreen.unclickButtons(type);
		this.setTexture(downTexture);
	}

	@Override
	public void onRelease() {
		
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	public SetType getType() {
		return type;
	}
}