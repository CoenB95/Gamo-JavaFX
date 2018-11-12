package gamo.objects;

import javafx.scene.PerspectiveCamera;

public class Camera extends GameObjectBase {
	public javafx.scene.Camera camera;

	public Camera() {
		camera = new PerspectiveCamera(true);
		camera.setNearClip(10);
		camera.setFarClip(900);

		//setTargetPosition(getPosition().withY(400));
		//setNode(camera);
	}
}
