package minesweeper.game.screensystem;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import minesweeper.game.buttons.SceneLaunchButton;
import minesweeper.game.rendering.RenderObject;

public abstract class Screen {

    private OrthographicCamera orthographicCamera = new OrthographicCamera(ScreenManager.DEFAULT_ORTHO_WIDTH, ScreenManager.DEFAULT_ORTHO_HEIGHT);
    protected Array<RenderObject> renderObjects = new Array<RenderObject>();

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        orthographicCamera.setToOrtho(false, ScreenManager.DEFAULT_ORTHO_WIDTH, ScreenManager.DEFAULT_ORTHO_HEIGHT);
        orthographicCamera.update();
        
        for (RenderObject renderObject : renderObjects) {
            Rectangle renderRectangle = renderObject.getRectangle();
            
            if (renderObject instanceof SceneLaunchButton) {
            	SceneLaunchButton btn = (SceneLaunchButton) renderObject;
            	spriteBatch.setColor(btn.getRenderTint());
            }
            
            spriteBatch.draw(renderObject.getTexture(), renderRectangle.getX(), renderRectangle.getY());
            
            spriteBatch.setColor(Color.WHITE);
        }
        
        spriteBatch.end();
    }

    public void update(float deltaTime) {
//        for (RenderObject renderObject : renderObjects) {
//            renderObject.update(deltaTime);
//        }
//        
        for (int i = 0; i < renderObjects.size; i++) {
        	renderObjects.get(i).update(deltaTime);
        }
    }
    
    public static Class<Screen> getScreen(String screenName) {
    	Class<Screen> screen = null;
    	try {
			screen = (Class<Screen>) Class.forName("minesweeper.game.screensystem." + screenName + "Screen");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find screen action for button [" + screenName + "]");
			e.printStackTrace();
		}
    	return screen;
    }
}
