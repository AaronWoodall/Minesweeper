package minesweeper.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class RenderObject {
    private Texture texture;
    private Rectangle rectangle;

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void autoSetRectangle() {
        rectangle = new Rectangle();
        rectangle.set(0, 0, texture.getWidth(), texture.getHeight());
    }

    public abstract void update(float deltaTime);
}
