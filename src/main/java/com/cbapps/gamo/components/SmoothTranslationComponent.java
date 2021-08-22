package com.cbapps.gamo.components;

import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.objects.GameObject;

public class SmoothTranslationComponent extends GameObjectComponentBase {
	private Vector3 lastPosition;
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
		Vector3 p1 = lastPosition;
		Vector3 p2 = getParentObject().getPosition();
		if (snappyness > 0) {
			Vector3 delta = p2.subtract(p1).multiply(elapsedSeconds / snappyness);
			getParentObject().setPosition(p1.add(delta));
		}
		lastPosition = getParentObject().getPosition();
	}
}
