package minesweeper.game.interaction;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import minesweeper.game.Minesweeper;

/**
 * Created by kyle on 11/3/16.
 */
public class TouchInteraction {
    public static Rectangle getTouchRectangle() {
        Vector3 location = Minesweeper.getInstance().getScreenManager().getCurrentScreen() // This is way too long. :|
                             .getOrthographicCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Rectangle(location.x, location.y, 1, 1);
    }
    
    public static boolean isLeftMouseDown() {
    	return Gdx.input.isTouched();
    }
}
