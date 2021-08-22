package com.cbapps.gamo.components;

import com.cbapps.gamo.math.Quaternion;

public class SpinComponent extends GameObjectComponentBase {
	private Quaternion rotationPerSecond;

	public SpinComponent(Quaternion rotationPerSecond) {
		this.rotationPerSecond = rotationPerSecond;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		var cur = getParentObject().getOrientation();
		var tar = cur.multiply(rotationPerSecond);
		getParentObject().setOrientation(cur.lerp(tar, elapsedSeconds));
//		getParentObject().setOrientation(getParentObject().getOrientation()
//				.multiply(rotationPerSecond.multiply(elapsedSeconds)));
	}
}
