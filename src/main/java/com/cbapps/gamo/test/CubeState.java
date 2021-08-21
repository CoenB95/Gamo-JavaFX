package com.cbapps.gamo.test;

import com.cbapps.gamo.javafx.TextObject;
import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.objects.AmbientLight;
import com.cbapps.gamo.state.GameState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.Duration;
import java.time.LocalDateTime;

public class CubeState extends GameState {
	private final LocalDateTime startTime;

	private final SpinningCubes cubeScene;
	private final TextObject debugText;
	private final AmbientLight light;

	public CubeState() {
		this.startTime = LocalDateTime.now();

		this.cubeScene = new SpinningCubes();
		this.debugText = new TextObject();
		this.light = new AmbientLight();
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		super.onKeyPressed(event);

		if (event.getCode() == KeyCode.SPACE) {
			cubeScene.spinCube();
		}

		if (event.getCode() == KeyCode.ESCAPE) {
			setState(new StartState());
		}
	}

	@Override
	public void onStart() {
		var camera = getCamera();
		camera.setClip(10, 900);
		camera.setPosition(Vector3.identity());

		getScene2D().addObject(debugText);
		getScene3D().addObjects(cubeScene, light);
	}

	@Override
	public void onStop() {
		getScene2D().removeAllObjects(true);
		getScene3D().removeAllObjects(true);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		var elapsedTotal = Duration.between(startTime, LocalDateTime.now());
		debugText.textProperty().set(String.format("Elapsed: %d:%02d:%02d",
				elapsedTotal.toHoursPart(),
				elapsedTotal.toMinutesPart(),
				elapsedTotal.toSecondsPart()));
	}
}
