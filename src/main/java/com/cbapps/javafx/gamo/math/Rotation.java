package com.cbapps.javafx.gamo.math;

public class Rotation {
	private double horizontal;
	private double vertical;
	private double roll;

	public static final Rotation ORIGIN = new Rotation(0, 0, 0);

	public Rotation(double horizontal, double vertical, double roll) {
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.roll = roll;

		while (this.horizontal < 0)
			this.horizontal += 360;
		while (this.horizontal >= 360)
			this.horizontal -= 360;

		while (this.vertical < 0)
			this.vertical += 360;
		while (this.vertical >= 360)
			this.vertical -= 360;

		while (this.roll < 0)
			this.roll += 360;
		while (this.roll >= 360)
			this.roll -= 360;
	}

	public Rotation add(Rotation rotation) {
		return new Rotation(horizontal + rotation.horizontal,
				vertical + rotation.vertical,
				roll + rotation.roll);
	}

	public Rotation add(double horizontalValue, double verticalValue, double rollValue) {
		return new Rotation(horizontal + horizontalValue,
				vertical + verticalValue,
				roll + rollValue);
	}

	public Rotation addHorizontal(double value) {
		return new Rotation(horizontal + value, vertical, roll);
	}

	public Rotation addVertical(double value) {
		return new Rotation(horizontal, vertical + value, roll);
	}

	public Rotation addRoll(double value) {
		return new Rotation(horizontal, vertical, roll + value);
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

	public Rotation multiply(double factor) {
		return new Rotation(horizontal * factor, vertical * factor, roll * factor);
	}

	public double smallestHorizontalDeltaTo(Rotation other) {
		double delta = other.horizontal - horizontal;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}

	public double smallestVerticalDeltaTo(Rotation other) {
		double delta = other.vertical - vertical;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}

	public double smallestRollDeltaTo(Rotation other) {
		double delta = other.roll - roll;
		if (delta > 180)
			return delta - 360;
		if (delta < -180)
			return delta + 360;
		return delta;
	}

	public Rotation withHorizontal(double value) {
		return new Rotation(value, vertical, roll);
	}

	public Rotation withVertical(double value) {
		return new Rotation(horizontal, value, roll);
	}

	public Rotation withRoll(double value) {
		return new Rotation(horizontal, vertical, value);
	}
}
