package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;
import com.cbapps.javafx.gamo.objects.GameVector;

public class AccelerationComponent extends GameObjectComponentBase {
	private double acceleration;
	private double maxSpeed;
	private double speed;
	private double targetValue;
	private double value;
	private double lastTargetValue;
	private boolean forwards;
	private boolean braking;

	private TargetSupplier targetGetter;
	private ValueConsumer valueSetter;

	private AccelerationComponent() {}

	public static AccelerationComponent horizontalRotation(double acceleration, double maxSpeed) {
		AccelerationComponent editor = new AccelerationComponent();
		editor.acceleration = acceleration;
		editor.maxSpeed = maxSpeed;
		editor.targetGetter = (o -> o.getRotation().getHorizontal());
		editor.valueSetter = (o, v) -> o.setRotation(o.getRotation().withHorizontal(v));
		return editor;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		targetValue = targetGetter.getTargetValue(getParentObject());

		double stopDistance = speed * speed / (2 * acceleration);

		// To prevent the value from wobbling, once the breaks are applied these can
		// only be lifted by changing the target again.
		if (lastTargetValue != targetValue) {
			braking = false;
			lastTargetValue = targetValue;
		}

		if (!braking)
			forwards = targetValue > value;

		if (forwards) {
			if (braking || stopDistance >= targetValue - value) {
				speed -= acceleration * elapsedSeconds;
				braking = true;
				if (speed < 0) {
					speed = 0;
				}
			} else {
				speed += acceleration * elapsedSeconds;
			}
		} else {
			if (braking || stopDistance >= value - targetValue) {
				speed += acceleration * elapsedSeconds;
				braking = true;
				if (speed > 0) {
					speed = 0;
				}
			} else {
				speed -= acceleration * elapsedSeconds;
			}
		}

		value += speed * elapsedSeconds;

		// Cap speed.
		speed = speed > maxSpeed ? maxSpeed : speed;
		speed = speed < -maxSpeed ? -maxSpeed : speed;

		valueSetter.setValue(getParentObject(), value);
	}

	private interface TargetSupplier {
		double getTargetValue(GameObject object);
	}

	private interface ValueConsumer {
		void setValue(GameObject object, double value);
	}
}
