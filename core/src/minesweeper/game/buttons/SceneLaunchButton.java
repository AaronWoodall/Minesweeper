package minesweeper.game.buttons;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.Minesweeper;
import minesweeper.game.screensystem.GamePlayScreen;
import minesweeper.game.screensystem.Screen;

public class SceneLaunchButton extends Button {
	
	private Class<Screen> action = null;
	private Screen actionCreated;
	private Texture upTexture, downTexture;

	public SceneLaunchButton(Texture texture, Texture clickedTexture, float xPosition, float yPosition, Class<Screen> action) {
		super(texture, xPosition, yPosition);
		this.action = action;
		upTexture = texture;
		downTexture = clickedTexture;
	}
	
	@Override
	public void onClick() {
		try {
			this.setTexture(downTexture);
			actionCreated = action.newInstance();
			Minesweeper.getInstance().getScreenManager().setCurrentScreen(actionCreated);
			if (actionCreated instanceof GamePlayScreen) {
				((GamePlayScreen)actionCreated).placeGameButtons();
			}
		} catch (IllegalAccessException | InstantiationException e) {
			JOptionPane.showMessageDialog(null, "Could not launch new screen via ScreenLaunchButton");
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	public void onRelease() {
		this.setTexture(upTexture);
	}
	
	public Color getRenderTint() {
		Color tint = Color.WHITE;
		if (isMouseOver) {
			tint = Color.LIGHT_GRAY;
		}
		return tint;
	}

}
