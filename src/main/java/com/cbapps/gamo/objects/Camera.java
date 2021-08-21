package com.cbapps.gamo.objects;

import com.cbapps.gamo.javafx.GameObjectBase;
import javafx.scene.PerspectiveCamera;

public class Camera extends GameObjectBase {
	public javafx.scene.PerspectiveCamera camera;

	public Camera() {
		this(new PerspectiveCamera(true));
	}

	private Camera(javafx.scene.PerspectiveCamera camera) {
		super(camera);
		this.camera = camera;
	}

	public void setClip(double near, double far) {
		camera.setNearClip(near);
		camera.setFarClip(far);
	}

	public void setFieldOfView(double fieldOfView) {
		camera.setFieldOfView(fieldOfView);
	}
}
