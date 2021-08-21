package com.cbapps.gamo.javafx;

import com.cbapps.gamo.objects.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.*;

public abstract class GameObjectBase extends GameObject {
	private final Group group;
	private final Affine rotateTransform;
	private final Scale scaleTransform;
	private final Translate translateTransform;

	public GameObjectBase(Node node) {
		this.group = new Group();
		this.rotateTransform = new Affine();
		this.scaleTransform = new Scale(1, 1, 1);
		this.translateTransform = new Translate(0, 0, 0);

		if (node != null) {
			this.group.getChildren().add(node);
		}

		this.group.getTransforms().addAll(translateTransform, scaleTransform, rotateTransform);
	}

	@Override
	public void addObject(GameObject object) {
		super.addObject(object);

		if (object instanceof GameObjectBase obj) {
			this.group.getChildren().add(obj.group);
		}
	}

	public Group getGroup() {
		return group;
	}

	@Override
	public boolean removeObject(GameObject object) {
		if (object instanceof GameObjectBase obj) {
			this.group.getChildren().remove(obj.group);
		}

		return super.removeObject(object);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
		//Convert from JavaFX/2D to OpenGL/3D conventions:
		//2D Y-down			-> 3D Y-up
		//2D Z-elevation	-> 3D Z-depth
		translateTransform.setX(getPosition().x());
		translateTransform.setY(-getPosition().y());
		translateTransform.setZ(-getPosition().z());
		rotateTransform.setToTransform(getOrientation().toMatrix(), MatrixType.MT_3D_4x4, 0);
		scaleTransform.setX(getScale());
		scaleTransform.setY(getScale());
		scaleTransform.setZ(getScale());
	}
}
