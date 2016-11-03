package minesweeper.game.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import minesweeper.game.rendering.RenderObject;

/**
 * Created by kyle on 11/3/16.
 */
public class Mine extends RenderObject {

    public Mine() {
        setTexture(new Texture(Gdx.files.internal("assets/mine.png")));
        autoSetRectangle();
    }

    @Override
    public void update(float deltaTime) {

    }
}
