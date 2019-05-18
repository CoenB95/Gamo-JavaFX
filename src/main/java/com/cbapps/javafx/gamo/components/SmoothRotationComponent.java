package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.math.RotationalDelta;
import com.cbapps.javafx.gamo.objects.GameObject;
import com.cbapps.javafx.gamo.objects.GameVector;

public class SmoothRotationComponent extends GameObjectComponentBase {
	private Rotation lastRotation;
	private double snappyness;

	public SmoothRotationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothRotationComponent direct() {
		return new SmoothRotationComponent(0);
	}

	@Override
	public void onAttach(GameObject object) {
		super.onAttach(object);
		lastRotation = object.getRotation();
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		Rotation r1 = lastRotation;
		Rotation r2 = getParentObject().getRotation();
		RotationalDelta delta = r1.smallestDeltaTo(r2).multiply(1.0 - snappyness);
		getParentObject().setRotation(r1.add(delta));
	}
}
