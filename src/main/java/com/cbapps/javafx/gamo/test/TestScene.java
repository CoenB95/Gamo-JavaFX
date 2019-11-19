package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.components.*;
import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.math.*;
import com.cbapps.javafx.gamo.objects.*;
import com.cbapps.javafx.gamo.scenes.GameScene;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TestScene extends GameObjectGroup {
	private double randomUpdateSecondsLeft = 0;

	private GameObject noop;
	private GameObjectGroup cubeWithText;
	private GameObject cube;
	private TextObject text;

	public TestScene() {

	}

	@Override
	protected void onAttach(GameObjectGroup newParent) {
		super.onAttach(newParent);
		Camera camera = new Camera(10, 900);
		camera.setPosition(new Position(0, 0, 0));

		noop = new CubeObject(5, 5, 5, Color.RED.deriveColor(0, 1, 1, 0.1));
		noop.setPosition(Position.ORIGIN.withZ(-50));
		noop.setRotation(new Rotation(0, -10, 0));

		cube = new CubeObject(11, 11, 11, Color.GREEN.deriveColor(0, 1, 1, 0.8));

		text = new TextObject();
		text.setScale(0.05);
		text.setPosition(Position.ORIGIN.withZ(5.5).withX(5.5).withY(5.5));

		cubeWithText = new GameObjectGroup();
		cubeWithText.addObject(cube);
		cubeWithText.addObject(text);

		cubeWithText.setRotation(noop.getRotation());
		cubeWithText.addComponent(FollowComponent.rotatingAndTranslating(noop));
		//cubeWithText.addComponent(AccelerationComponent.horizontalRotation(25.0, 100.0));
		cubeWithText.addComponent(new SmoothRotationComponent(0.5));

		addObject(noop);
		addObject(cubeWithText);
		setCamera(camera);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);

		text.textProperty().set(String.format("z: %.1f*", text.getPosition().z));

		randomUpdateSecondsLeft -= elapsedSeconds;
		if (randomUpdateSecondsLeft <= 0) {
			noop.setRotation(noop.getRotation().withHorizontal(NumberGenerator.randomDouble(360)));
			randomUpdateSecondsLeft = NumberGenerator.randomDouble(1, 10);
		}
	}
}
