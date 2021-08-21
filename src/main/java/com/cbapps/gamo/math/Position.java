package com.cbapps.gamo.math;

public class Position extends PositionalDelta {

	public static final Position ORIGIN = new Position(0, 0, 0);

	public Position(double x, double y, double z) {
		super(x, y, z);
	}

	@Override
	public Position add(PositionalDelta position) {
		return new Position(
				x + position.x,
				y + position.y,
				z + position.z);
	}

	public Position withX(double value) {
		return new Position(value, y, z);
	}

	public Position withY(double value) {
		return new Position(x, value, z);
	}

	public Position withZ(double value) {
		return new Position(x, y, value);
	}
}
