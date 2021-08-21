package com.cbapps.gamo.math;

public class Rotation extends RotationalDelta {

	public static final Rotation ORIGIN = new Rotation(0, 0, 0);

	public Rotation(double horizontal, double vertical, double roll) {
		super(horizontal, vertical, roll);

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

	@Override
	public Rotation add(RotationalDelta rotation) {
		return new Rotation(
				horizontal + rotation.getHorizontal(),
				vertical + rotation.getVertical(),
				roll + rotation.getRoll());
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
