package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.RotationalDelta;

public class SpinComponent extends GameObjectComponentBase {
	private RotationalDelta rotationPerSecond;

	public SpinComponent(RotationalDelta rotationPerSecond) {
		this.rotationPerSecond = rotationPerSecond;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		getParentObject().setRotation(getParentObject().getRotation()
				.add(rotationPerSecond.multiply(elapsedSeconds)));
	}
}
