package gamo.objects;

import javafx.scene.PerspectiveCamera;

public class Camera extends GameObjectBase {
	public javafx.scene.Camera camera;

	public Camera(double nearClip, double farClip) {
		camera = new PerspectiveCamera(true);
		camera.setNearClip(nearClip);
		camera.setFarClip(farClip);

		setNode(camera);
	}
}
