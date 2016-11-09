package minesweeper.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minesweeper.models.Square;

public class GameButton extends Button {
	
	private Square square;
	
	private static int NUMBER_OF_REVEALED_BUTTONS = 7;
	
	private static Texture hiddenTexture = new Texture(Gdx.files.internal("Unrevealed.png"));
	private static Texture revealedTexture = new Texture(Gdx.files.internal("Revealed.png"));
	private static Texture[] numberedTextures = new Texture[NUMBER_OF_REVEALED_BUTTONS];
	
	static {
		for (int iter = 0; iter < numberedTextures.length; iter++) {
			numberedTextures[iter] = new Texture(Gdx.files.internal("Revealed" + (iter+1) + ".png"));
		}
	}

	public GameButton(float xPosition, float yPosition) {
		super(hiddenTexture, xPosition, yPosition);
	}

	@Override
	public void onClick() {
		setTexture(revealedTexture);
	}

	@Override
	public void onRelease() {
		
	}

}
