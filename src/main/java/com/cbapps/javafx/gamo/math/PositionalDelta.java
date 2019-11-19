package com.cbapps.javafx.gamo.math;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class PositionalDelta {
	public final double x;
	public final double y;
	public final double z;

	public static final PositionalDelta ZERO = new PositionalDelta(0, 0, 0);

	public PositionalDelta(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PositionalDelta add(PositionalDelta position) {
		return new PositionalDelta(
				x + position.x,
				y + position.y,
				z + position.z);
	}

	public PositionalDelta add(double x, double y, double z) {
		return new PositionalDelta(this.x + x,
				this.y + y,
				this.z + z);
	}

	public PositionalDelta addX(double value) {
		return new PositionalDelta(x + value, y, z);
	}

	public PositionalDelta addY(double value) {
		return new PositionalDelta(x, y + value, z);
	}

	public PositionalDelta addZ(double value) {
		return new PositionalDelta(x, y, z + value);
	}

	public Position asPosition() {
		return new Position(x, y, z);
	}

	public PositionalDelta limitX(double minValue, double maxValue)
	{
		return new PositionalDelta(x < minValue ? minValue : (x > maxValue ? maxValue : x), y, z);
	}

	public PositionalDelta limitY(double minValue, double maxValue)
	{
		return new PositionalDelta(x, y < minValue ? minValue : (y > maxValue ? maxValue : y), z);
	}

	public PositionalDelta limitZ(double minValue, double maxValue)
	{
		return new PositionalDelta(x, y, z < minValue ? minValue : (z > maxValue ? maxValue : z));
	}

	public PositionalDelta multiply(double factor) {
		return new PositionalDelta(x * factor, y * factor, z * factor);
	}

	public PositionalDelta subtract(PositionalDelta position) {
		return new PositionalDelta(x - position.x,
				y - position.y,
				z - position.z);
	}

	public PositionalDelta subtractX(double value) {
		return new PositionalDelta(x - value, y, z);
	}

	public PositionalDelta subtractY(double value) {
		return new PositionalDelta(x, y - value, z);
	}

	public PositionalDelta subtractZ(double value) {
		return new PositionalDelta(x, y, z - value);
	}
}
