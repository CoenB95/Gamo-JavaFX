package com.cbapps.javafx.gamo.components;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class AccelerationComponent/* extends GameObjectEditingComponent {
	private double acceleration;
	private double maxSpeed;
	private double speed;
	private double targetValue;
	private double value;

	private DoubleSupplier targetGetter;
	private DoubleConsumer valueSetter;

	@Override
	public void onUpdate(double elapsedSeconds) {
		double stopDistance = speed * speed / (2 * acceleration);

		boolean forwards = targetValue > value;

		if (forwards) {
			if (stopDistance >= targetValue - value)
				speed -= acceleration * elapsedSeconds;
			else
				speed += acceleration * elapsedSeconds;
		} else {
			if (stopDistance >= value - targetValue)
				speed += acceleration * elapsedSeconds;
			else
				speed -= acceleration * elapsedSeconds;
		}

		value += speed * elapsedSeconds;

		// Cap speed.
		speed = speed > maxSpeed ? maxSpeed : speed;
		speed = speed < -maxSpeed ? -maxSpeed : speed;
	}*/{
}
