package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.RotationalDelta;
import com.cbapps.javafx.gamo.objects.GameVector;

public class SpinComponent implements GameObjectComponent {
	private RotationalDelta rotationPerSecond;

	public SpinComponent(RotationalDelta rotationPerSecond) {
		this.rotationPerSecond = rotationPerSecond;
	}

	@Override
	public GameVector onUpdate(double elapsedSeconds, GameVector target) {
		return target.withRotation(target.getRotation()
				.add(rotationPerSecond.multiply(elapsedSeconds)));
	}
}
