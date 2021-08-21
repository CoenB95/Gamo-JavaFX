package com.cbapps.gamo.components;

import com.cbapps.gamo.objects.GameObject;

public class AccelerationComponent extends GameObjectComponentBase {
	private double acceleration;
	private double maxSpeed;
	private double speed;
	private double targetValue;
	private double value;
	private double lastTargetValue;
	private boolean forwards;
	private boolean braking;

	private AccelerationComponent() {}

	public static AccelerationComponent horizontalRotation(double acceleration, double maxSpeed) {
		AccelerationComponent editor = new AccelerationComponent();
		editor.acceleration = acceleration;
		editor.maxSpeed = maxSpeed;
		return editor;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		targetValue = 0;//targetGetter.getTargetValue(getParentObject());

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

		//valueSetter.setValue(getParentObject(), value);
	}
}
