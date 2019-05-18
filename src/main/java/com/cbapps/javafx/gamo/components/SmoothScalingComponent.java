package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

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
		double delta = (s2 - s1) * (1.0 - snappyness);
		getParentObject().setScale(s1 + delta);
	}
}
