package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.PositionalDelta;
import com.cbapps.javafx.gamo.objects.GameObject;
import com.cbapps.javafx.gamo.objects.GameVector;

public class SmoothTranslationComponent extends GameObjectComponentBase {
	private Position lastPosition;
	private double snappyness;

	public SmoothTranslationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothTranslationComponent direct() {
		return new SmoothTranslationComponent(0);
	}

	@Override
	public void onAttach(GameObject object) {
		super.onAttach(object);
		lastPosition = object.getPosition();
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		Position p1 = lastPosition;
		Position p2 = getParentObject().getPosition();
		if (snappyness > 0) {
			PositionalDelta delta = p2.subtract(p1).multiply(elapsedSeconds / snappyness);
			getParentObject().setPosition(p1.add(delta));
		}
		lastPosition = getParentObject().getPosition();
	}
}
