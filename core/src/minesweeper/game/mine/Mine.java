package minesweeper.game.mine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import minesweeper.game.Minesweeper;
import minesweeper.game.rendering.RenderObject;

/**
 * Created by kyle on 11/3/16.
 */
public class Mine extends RenderObject {

    private int velocityModifier = 1;

    public Mine() {
        setTexture(new Texture(Gdx.files.internal("mine.png")));
        autoSetRectangle();
    }

    @Override
    public void update(float deltaTime) {
        getRectangle().x += 80 * deltaTime * velocityModifier;
        float xPosition = getRectangle().getX();
        OrthographicCamera camera = Minesweeper.getInstance().getScreenManager().getCurrentScreen().getOrthographicCamera();
        float orthoMaxX = camera.viewportWidth;
//        float orthoMaxY = camera.viewportHeight;
        System.out.println(xPosition);
        if (xPosition > orthoMaxX - getRectangle().getWidth()|| xPosition < 0) {
            velocityModifier *= -1;
        }
//        System.out.println(velocityModifier);
    }
}
