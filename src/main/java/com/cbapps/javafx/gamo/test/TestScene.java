package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.objects.CubeObject;
import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.objects.Camera;
import com.cbapps.javafx.gamo.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class TestScene extends GameScene {

	public TestScene(Scene scene, Pane root) {
		super(scene, root);

		Camera camera = new Camera(10, 900);

		CubeObject cube = new CubeObject(10, 10, 10);
		cube.setTargetVector(cube.getTargetVector()
				.withPosition(Position.ORIGIN.withZ(50))
				.withRotation(new Rotation(20, 20, 0)));

		add3DObject(cube);
		add3DObject(camera);
		setCamera(camera.camera);
	}
}
