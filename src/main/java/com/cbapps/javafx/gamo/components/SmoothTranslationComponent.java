package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.PositionalDelta;
import com.cbapps.javafx.gamo.objects.GameVector;

public class SmoothTranslationComponent implements GameObjectEditor {

	private double snappyness;

	public SmoothTranslationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothTranslationComponent direct() {
		return new SmoothTranslationComponent(0);
	}

	@Override
	public GameVector onUpdate(double elapsedSeconds, GameVector current, GameVector target) {
		Position p1 = current.getPosition();
		Position p2 = target.getPosition();
		PositionalDelta delta = p2.subtract(p1).multiply(1.0 - snappyness);
		return current.addPosition(delta);
	}
}
