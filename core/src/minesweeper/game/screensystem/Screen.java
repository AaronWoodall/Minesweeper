package minesweeper.game.screensystem;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import minesweeper.game.Minesweeper;
import minesweeper.game.rendering.RenderObject;

public abstract class Screen {

    private OrthographicCamera orthographicCamera = new OrthographicCamera(Minesweeper.getWindowWidth(), Minesweeper.getWindowHeight());
    protected Array<RenderObject> renderObjects = new Array<RenderObject>();

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (RenderObject renderObject : renderObjects) {
//            Rectangle renderRectangle = renderObject.getRectangle();
//            spriteBatch.draw(renderObject.getTexture(), renderRectangle.getX(), renderRectangle.getY());
        }
        spriteBatch.end();
    }

    public void update(float deltaTime) {
        for (RenderObject renderObject : renderObjects) {
//            renderObject.update(deltaTime);
        }
    }

}
