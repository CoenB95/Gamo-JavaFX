package com.cbapps.gamo.test;

import com.cbapps.gamo.components.FollowComponent;
import com.cbapps.gamo.components.SmoothRotationComponent;
import com.cbapps.gamo.javafx.GroupObject;
import com.cbapps.gamo.math.*;
import com.cbapps.gamo.javafx.CubeObject;
import com.cbapps.gamo.objects.GameObject;
import com.cbapps.gamo.javafx.TextObject;
import javafx.scene.paint.Color;

import java.util.Random;

public class SpinningCubes extends GroupObject {
	private final Random random;

	private double randomUpdateSecondsLeft = 0;

	private GameObject group;
	private CubeObject smallCube;
	private TextObject debugText;

	public SpinningCubes() {
		random = new Random();
	}

	@Override
	protected void onAttach(GameObject newParent) {
		super.onAttach(newParent);

		smallCube = CubeObject.of(5, 5, 5, Color.RED.deriveColor(0, 1, 1, 0.8));
		smallCube.setPosition(new Vector3(0, 0, -50));
		smallCube.setOrientation(Quaternion.of(Vector3.unitX(), -10));

		var bigCube = CubeObject.of(11, 11, 11, Color.GREEN.deriveColor(0, 1, 1, 0.8));

		var outlineCube = CubeObject.of(11, 11, 11, Color.BLUE);
		outlineCube.wireframe(true);
		outlineCube.addComponent(FollowComponent.rotatingAndTranslating(bigCube));

		debugText = new TextObject();
		debugText.setScale(0.05);
		debugText.setPosition(new Vector3(5.5, 5.5, 5.5));

		group = new GroupObject();
		group.addObjects(bigCube, outlineCube, debugText);
		group.setOrientation(smallCube.getOrientation());
		group.addComponent(FollowComponent.rotatingAndTranslating(smallCube));
		group.addComponent(new SmoothRotationComponent(0.5));

		addObject(smallCube);
		addObject(group);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);

		debugText.textProperty().set(String.format("Goal:  %s%nAngle: %s", smallCube.getOrientation(), group.getOrientation()));

		randomUpdateSecondsLeft -= elapsedSeconds;
		if (randomUpdateSecondsLeft <= 0) {
			smallCube.setOrientation(smallCube.getOrientation().multiply(Quaternion.of(Vector3.unitY(), random.nextDouble() * 360)));
			randomUpdateSecondsLeft = random.nextDouble() * 9 + 1; // 1 to 10 seconds
		}
	}

	public void spinCube() {
		randomUpdateSecondsLeft = 0;
	}
}
