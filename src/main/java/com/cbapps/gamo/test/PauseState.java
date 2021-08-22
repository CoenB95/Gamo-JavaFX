package com.cbapps.gamo.test;

import com.cbapps.gamo.javafx.TextObject;
import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.state.GameStateBase;
import com.cbapps.gamo.state.IGameScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PauseState extends GameStateBase {
	private final TextObject pauseText;

	public PauseState() {
		this.pauseText = new TextObject();
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		super.onKeyPressed(event);

		if (event.getCode() == KeyCode.SPACE) {
			getScene().popState();
		}
	}

	@Override
	public void onStart(IGameScene scene) {
		super.onStart(scene);

		pauseText.setFontSize(64);
		pauseText.setCentered(true);
		pauseText.textProperty().set("""
				===== WELCOME =====
				Hit space to start""");

		scene.get2D().addObject(pauseText);
	}


	@Override
	public void onStop() {
		getScene().get2D().removeObject(pauseText);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		pauseText.setPosition(new Vector3(getScene().getWidth() / 2, -getScene().getHeight() / 2, 0));
	}
}
