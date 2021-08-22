package com.cbapps.gamo.components;

import com.cbapps.gamo.math.Quaternion;
import com.cbapps.gamo.objects.GameObject;

public class SmoothRotationComponent extends GameObjectComponentBase {
	private Quaternion lastRotation;
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
		lastRotation = object.getOrientation();
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		Quaternion r1 = lastRotation;
		Quaternion r2 = getParentObject().getOrientation();
		if (snappyness > 0) {
			Quaternion delta = r1.lerp(r2, elapsedSeconds / snappyness);
			//RotationalDelta delta = r1.smallestDeltaTo(r2).multiply(elapsedSeconds / snappyness);
			getParentObject().setOrientation(delta);
		}
		lastRotation = getParentObject().getOrientation();
	}
}
