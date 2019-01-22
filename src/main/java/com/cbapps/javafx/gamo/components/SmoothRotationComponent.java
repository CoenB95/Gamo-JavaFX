package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.math.RotationalDelta;
import com.cbapps.javafx.gamo.objects.GameVector;

public class SmoothRotationComponent implements GameObjectEditor {

	private double snappyness;

	public SmoothRotationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothRotationComponent direct() {
		return new SmoothRotationComponent(0);
	}

	@Override
	public GameVector onUpdate(double elapsedSeconds, GameVector current, GameVector target) {
		Rotation r1 = current.getRotation();
		Rotation r2 = target.getRotation();
		RotationalDelta delta = r1.smallestDeltaTo(r2).multiply(1.0 - snappyness);
		return current.addRotation(delta);
	}
}
