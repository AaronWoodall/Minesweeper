package minesweeper.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class RenderImage extends RenderObject {
	
	public RenderImage(Texture texture, int xPosition, int yPosition) {
		super.setTexture(texture);
		
		Rectangle rect = new Rectangle(xPosition - (texture.getWidth() / 2), yPosition, texture.getWidth(), texture.getHeight());
		super.setRectangle(rect);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

}
