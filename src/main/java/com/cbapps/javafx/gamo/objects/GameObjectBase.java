package com.cbapps.javafx.gamo.objects;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public abstract class GameObjectBase extends GameObject {
	private Node node;
	private Translate translateTransform;
	private Rotate horizontalRotateTransform;
	private Rotate verticalRotateTransform;
	private Scale scaleTransform;

	public GameObjectBase() {
		translateTransform = new Translate(0, 0, 0);
		horizontalRotateTransform = new Rotate(0, 0, 0, 0, new Point3D(0, 1, 0));
		verticalRotateTransform = new Rotate(0, 0, 0, 0, new Point3D(1, 0, 0));
		scaleTransform = new Scale(1, 1, 1);
	}

	protected void setNode(Node node) {
		this.node = node;
		node.getTransforms().addAll(translateTransform, horizontalRotateTransform, verticalRotateTransform,
				scaleTransform);
	}

	@Override
	public void setParentGroup(GameObjectGroup parent) {
		super.setParentGroup(parent);
		if (node == null)
			return;

		parent.getGroup().getChildren().add(node);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
		translateTransform.setX(getPosition().getX());
		translateTransform.setY(-getPosition().getY());
		translateTransform.setZ(getPosition().getZ());
		horizontalRotateTransform.setAngle(getRotation().getHorizontal());
		verticalRotateTransform.setAngle(getRotation().getVertical());
		scaleTransform.setX(getScale());
		scaleTransform.setY(getScale());
		scaleTransform.setZ(getScale());
	}
}
