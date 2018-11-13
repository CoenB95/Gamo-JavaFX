package gamo.test;

import gamo.math.Position;
import gamo.math.Rotation;
import gamo.objects.Camera;
import gamo.objects.CubeObject;
import gamo.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class TestScene extends GameScene {

	public TestScene(Scene scene, Pane root) {
		super(scene, root);

		Camera camera = new Camera(10, 900);

		CubeObject cube = new CubeObject(10, 10, 10);
		cube.setTargetPosition(Position.ORIGIN.withZ(50));
		cube.setTargetRotation(new Rotation(20, 20, 0));

		add3DObject(cube);
		add3DObject(camera);
		setCamera(camera.camera);
	}
}
