package minesweeper.game.buttons;

import java.util.function.Function;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.Texture;

public class CallbackButton extends Button {

	private Supplier<Void> callbackAction;

	public Supplier<Void> getCallbackAction() {
		return callbackAction;
	}

	public void setCallbackAction(Supplier<Void> callbackAction) {
		this.callbackAction = callbackAction;
	}

	public CallbackButton(Texture texture, float xPosition, float yPosition) {
		super(texture, xPosition, yPosition);
	}

	@Override
	public void onClick() {
		callbackAction.get();
	}

	@Override
	public void onRelease() {
		
	}

}
