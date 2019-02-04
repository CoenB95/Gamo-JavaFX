package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.components.*;
import com.cbapps.javafx.gamo.math.*;
import com.cbapps.javafx.gamo.objects.*;
import com.cbapps.javafx.gamo.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TestScene extends GameScene {
	private double randomUpdateSecondsLeft = 0;

	private GameObject noop;

	public TestScene(Scene scene, Pane root) {
		super(scene, root);

		Camera camera = new Camera(10, 900);

		CubeObject cube = new CubeObject(11, 11, 11, Color.GREEN.deriveColor(0, 1, 1, 0.1));

		noop = new CubeObject(9, 9, 9, Color.RED);
		noop.setPosition(Position.ORIGIN.withZ(50));
		noop.setRotation(new Rotation(0, 30, 0));

		cube.setPosition(noop.getPosition());
		cube.setRotation(noop.getRotation());
		cube.addComponent(FollowComponent.rotatingAndTranslating(noop));
		cube.addComponent(SmoothTranslationComponent.direct());
		//cube.addEditor(new SmoothRotationComponent(0.95));
		cube.addComponent(AccelerationComponent.horizontalRotation(75.0, 100.0));

		add3DObject(noop);
		add3DObject(cube);
		add3DObject(camera);
		setCamera(camera.camera);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);

		randomUpdateSecondsLeft -= elapsedSeconds;

		if (randomUpdateSecondsLeft <= 0) {
			noop.setRotation(noop.getRotation().withHorizontal(NumberGenerator.randomDouble(360)));
			randomUpdateSecondsLeft = NumberGenerator.randomDouble(1, 10);
		}
	}
}
