package com.cbapps.gamo.test;

import com.cbapps.gamo.javafx.TextObject;
import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.objects.AmbientLight;
import com.cbapps.gamo.state.GameStateBase;
import com.cbapps.gamo.state.IGameScene;
import javafx.scene.input.KeyEvent;

import java.time.Duration;
import java.time.LocalDateTime;

public class CubeState extends GameStateBase {
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

		switch (event.getCode()) {
			case S -> cubeScene.spinCube();
			case SPACE -> getScene().pushState(new PauseState());
			case ESCAPE -> getScene().setState(new StartState());
		}
	}

	@Override
	public void onStart(IGameScene scene) {
		super.onStart(scene);

		var camera = scene.getCamera();
		camera.setClip(10, 900);
		camera.setPosition(Vector3.identity());

		scene.get2D().addObject(debugText);
		scene.get3D().addObjects(cubeScene, light);
	}

	@Override
	public void onStop() {
		getScene().get2D().removeObject(debugText);
		getScene().get3D().removeObjects(cubeScene, light);
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
