package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameVector;

public class SmoothScalingComponent implements GameObjectEditor {

	private double snappyness;

	public SmoothScalingComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothScalingComponent direct() {
		return new SmoothScalingComponent(0);
	}

	@Override
	public GameVector onUpdate(double elapsedSeconds, GameVector current, GameVector target) {
		double s1 = current.getScale();
		double s2 = target.getScale();
		double delta = (s2 - s1) * (1.0 - snappyness);
		return current.withScale(s1 + delta);
	}
}
