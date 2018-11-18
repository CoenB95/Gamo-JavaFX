package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.PositionalDelta;

public class SmoothTranslationComponent extends GameObjectEditingComponent {

	private double snappyness;

	public SmoothTranslationComponent(double snappyness) {
		this.snappyness = snappyness;
	}

	public static SmoothTranslationComponent direct() {
		return new SmoothTranslationComponent(0);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		Position p1 = getParentObject().getPosition();
		Position p2 = getParentObject().getTargetPosition();
		PositionalDelta delta = p2.subtract(p1).multiply(1.0 - snappyness);
		getEditableParentObject().setPosition(getParentObject().getPosition().add(delta));
	}
}
