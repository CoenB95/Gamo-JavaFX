package com.cbapps.javafx.gamo.math;

public class RotationalDelta {
	protected double horizontal;
	protected double vertical;
	protected double roll;

	public static final RotationalDelta ZERO = new RotationalDelta(0, 0, 0);

	public RotationalDelta(double horizontal, double vertical, double roll) {
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.roll = roll;
	}

	public static RotationalDelta horizontal(double horizontal) {
		return new RotationalDelta(horizontal, 0, 0);
	}

	public static RotationalDelta vertical(double vertical) {
		return new RotationalDelta(0, vertical, 0);
	}

	public static RotationalDelta roll(double roll) {
		return new RotationalDelta(0, 0, roll);
	}

	public RotationalDelta add(RotationalDelta rotation) {
		return new RotationalDelta(
				horizontal + rotation.getHorizontal(),
				vertical + rotation.getVertical(),
				roll + rotation.getRoll());
	}

	public RotationalDelta add(double horizontalValue, double verticalValue, double rollValue) {
		return new RotationalDelta(horizontal + horizontalValue,
				vertical + verticalValue,
				roll + rollValue);
	}

	public RotationalDelta addHorizontal(double value) {
		return new RotationalDelta(horizontal + value, vertical, roll);
	}

	public RotationalDelta addVertical(double value) {
		return new RotationalDelta(horizontal, vertical + value, roll);
	}

	public RotationalDelta addRoll(double value) {
		return new RotationalDelta(horizontal, vertical, roll + value);
	}

	public Rotation asRotation() {
		return new Rotation(horizontal, vertical, roll);
	}

	public double getHorizontal() {
		return horizontal;
	}

	public double getVertical() {
		return vertical;
	}

	public double getRoll() {
		return roll;
	}

	public RotationalDelta multiply(double factor) {
		return new RotationalDelta(horizontal * factor, vertical * factor, roll * factor);
	}

	public RotationalDelta smallestDeltaTo(RotationalDelta other) {
		return new RotationalDelta(
				smallestHorizontalDeltaTo(other),
				smallestVerticalDeltaTo(other),
				smallestRollDeltaTo(other)
		);
	}

	public double smallestHorizontalDeltaTo(RotationalDelta other) {
		double delta = other.horizontal - horizontal;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}

	public double smallestVerticalDeltaTo(RotationalDelta other) {
		double delta = other.vertical - vertical;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}

	public double smallestRollDeltaTo(RotationalDelta other) {
		double delta = other.roll - roll;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}
}
