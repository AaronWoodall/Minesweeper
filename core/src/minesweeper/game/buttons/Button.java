package minesweeper.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import minesweeper.game.interaction.TouchInteraction;
import minesweeper.game.rendering.RenderObject;

/**
 * Created by kyle on 11/3/16.
 */
public abstract class Button extends RenderObject {

    public boolean isMouseOver = false;

    public Button(Texture texture, float xPosition, float yPosition) {
        super.setTexture(texture);
        super.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        super.setRectangle(new Rectangle(xPosition, yPosition, texture.getWidth(), texture.getHeight()));
    }

    @Override
    public void update(float deltaTime) {
        Rectangle touchLocation = TouchInteraction.getTouchRectangle();
        if (super.getRectangle().contains(touchLocation)) {
            isMouseOver = true;
            if (TouchInteraction.isLeftMouseDown()) {
            	onClick();
            }
        } else {
            isMouseOver = false;
        }
    }
    
    public abstract void onClick();
}
