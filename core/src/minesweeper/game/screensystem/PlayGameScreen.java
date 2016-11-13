package minesweeper.game.screensystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.buttons.SetterButton;

public class PlayGameScreen extends Screen {
	private static final double[] BOARD_SIZES = { 24, 16, 8 };
	private static final double[] MINE_PERCENTAGES = { 0.25, 0.17, 0.10 };
	
	private static double difficulty, minePercentage;
	
	private BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
	
	public static void setMinePercentage(double minePercentage) {
		PlayGameScreen.minePercentage = minePercentage;
		System.out.println("Mine Percentage set to " + getMinePercentage());
	}

	public static void setDifficulty(double value) {
		PlayGameScreen.difficulty = value;
		System.out.println("Difficulty set to " + getDifficulty());
	}

	public static double getMinePercentage() {
		return minePercentage;
	}

	public static double getDifficulty() {
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
			Texture downTexture = new Texture(Gdx.files.internal(difficultyTextureNames[diff] + "Clicked.png"));
			
			SetterButton button = new SetterButton(upTexture, downTexture, xPosLeft, yPos, MINE_PERCENTAGES[diff], SetterButton.SetType.DIFFICULTY, difficultyTextureNames[diff]);
			renderObjects.add(button);
		}
		
		xPosRight = (float) ((float)width - (width / 2.4));
		for (int size = 0; size < sizeTextureNames.length; size++) {
			int yPos = 200 + (75 * size);
			
			Texture upTexture = new Texture(Gdx.files.internal(sizeTextureNames[size] + ".png"));
			Texture downTexture = new Texture(Gdx.files.internal(sizeTextureNames[size] + "Clicked.png"));
			
			SetterButton button = new SetterButton(upTexture, downTexture, xPosRight, yPos, BOARD_SIZES[size], SetterButton.SetType.SIZE, sizeTextureNames[size]);
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
		
		// SET DIFFICULTY / SIZE DEFAULTS FOR PLAY GAME
		setDifficulty(MINE_PERCENTAGES[2]);
		setMinePercentage(BOARD_SIZES[2]);
	}
	
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		spriteBatch.begin();
		font.draw(spriteBatch, "Difficulty", 160, 450);
		font.draw(spriteBatch, "Size", 550, 450);
		spriteBatch.end();
	}

	public static void unclickButtons() {
		
		
	}
}