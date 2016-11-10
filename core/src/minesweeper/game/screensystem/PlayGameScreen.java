package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.buttons.SetterButton;

public class PlayGameScreen extends Screen {
	private static final int[] BOARD_SIZES = { 24, 16, 8 };
	private static final int[] MINE_PERCENTAGES = { 25, 17, 10 };
	
	private static int difficulty, minePercentage;
	
	public static void setMinePercentage(int minePercentage) {
		PlayGameScreen.minePercentage = minePercentage;
		System.out.println("Mine Percentage set to " + getMinePercentage());
	}

	public static void setDifficulty(int difficulty) {
		PlayGameScreen.difficulty = difficulty;
		System.out.println("Difficulty set to " + getDifficulty());
	}

	public static int getMinePercentage() {
		return minePercentage;
	}

	public static int getDifficulty() {
		return difficulty;
	}

	private boolean hasChosenDifficulty, hasChosenMinePercentage;
	
	private String[] difficultyTextureNames = {
		"Hard", "Medium", "Easy"
	};
	
	private String[] sizeTextureNames = {
		"Large", "Medium", "Small"
	};
	
	// TODO: Extract method(s)
	public PlayGameScreen() {
		int width = Gdx.graphics.getBackBufferWidth();
		
		int xPosLeft = width / 8;
		float xPosRight = width - (width / 3);
		
		for (int diff = 0; diff < difficultyTextureNames.length; diff++) {
			int yPos = 200 + (75 * diff);
			
			Texture upTexture = new Texture(Gdx.files.internal(difficultyTextureNames[diff] + ".png"));
			
			SetterButton button = new SetterButton(upTexture, xPosLeft, yPos, MINE_PERCENTAGES[diff], SetterButton.SetType.DIFFICULTY);
			renderObjects.add(button);
		}
		
		xPosRight = (float) ((float)width - (width / 2.4));
		for (int size = 0; size < sizeTextureNames.length; size++) {
			int yPos = 200 + (75 * size);
			
			Texture upTexture = new Texture(Gdx.files.internal(sizeTextureNames[size] + ".png"));
			
			SetterButton button = new SetterButton(upTexture, xPosRight, yPos, BOARD_SIZES[size], SetterButton.SetType.SIZE);
			renderObjects.add(button);
		}
		
		Texture backUpText = new Texture(Gdx.files.internal("Back.png"));
		Texture backDownText = new Texture(Gdx.files.internal("BackClicked.png"));
		Texture playUpText = new Texture(Gdx.files.internal("PlayGame.png"));
		Texture playDownText = new Texture(Gdx.files.internal("PlayGameClicked.png"));
		
		SceneLaunchButton playButton = new SceneLaunchButton(playUpText, playDownText, xPosRight, 50, getScreen("GamePlay"));
		renderObjects.add(playButton);
		
		SceneLaunchButton backButton = new SceneLaunchButton(backUpText, backDownText, xPosLeft, 50, getScreen("MainMenu"));
		renderObjects.add(backButton);
	}
	
	
}
