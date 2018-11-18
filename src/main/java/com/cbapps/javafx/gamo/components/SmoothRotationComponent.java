package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.math.RotationalDelta;

public class SmoothRotationComponent extends GameObjectEditingComponent {

	private double snappyness;

	public SmoothRotationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothRotationComponent direct() {
		return new SmoothRotationComponent(0);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		Rotation r1 = getParentObject().getRotation();
		Rotation r2 = getParentObject().getTargetRotation();
		RotationalDelta delta = r1.smallestDeltaTo(r2).multiply(1.0 - snappyness);
		getEditableParentObject().setRotation(getParentObject().getRotation().add(delta));
	}
}
