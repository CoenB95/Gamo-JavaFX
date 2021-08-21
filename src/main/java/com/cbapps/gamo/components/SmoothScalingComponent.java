package com.cbapps.gamo.components;

import com.cbapps.gamo.objects.GameObject;

public class SmoothScalingComponent extends GameObjectComponentBase {
	private double lastScale;
	private double snappyness;

	public SmoothScalingComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothScalingComponent direct() {
		return new SmoothScalingComponent(0);
	}

	@Override
	public void onAttach(GameObject object) {
		super.onAttach(object);
		lastScale = object.getScale();
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		double s1 = lastScale;
		double s2 = getParentObject().getScale();
		if (snappyness > 0) {
			double delta = (s2 - s1) * (elapsedSeconds / snappyness);
			getParentObject().setScale(s1 + delta);
		}
		lastScale = getParentObject().getScale();
	}
}
