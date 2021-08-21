package com.cbapps.gamo.objects;

import com.cbapps.gamo.math.Position;
import com.cbapps.gamo.math.PositionalDelta;
import com.cbapps.gamo.math.Rotation;
import com.cbapps.gamo.math.RotationalDelta;

public class GameVector {
	private final Position position;
	private final Rotation rotation;
	private final double scale;

	public GameVector() {
		this(Position.ORIGIN, Rotation.ORIGIN, 1.0);
	}

	public GameVector(Position position, Rotation rotation) {
		this(position, rotation, 1.0);
	}

	public GameVector(Position position, Rotation rotation, double scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Position getPosition() {
		return position;
	}

	public Rotation getRotation() {
		return rotation;
	}

	public double getScale() {
		return scale;
	}

	public GameVector addPosition(PositionalDelta positionalDelta) {
		return new GameVector(position.add(positionalDelta), rotation, scale);
	}

	public GameVector addRotation(RotationalDelta rotationalDelta) {
		return new GameVector(position, rotation.add(rotationalDelta), scale);
	}

	public GameVector withPosition(Position newPosition) {
		return new GameVector(newPosition, rotation, scale);
	}

	public GameVector withRotation(Rotation newRotation) {
		return new GameVector(position, newRotation, scale);
	}

	public GameVector withScale(double newScale) {
		return new GameVector(position, rotation, newScale);
	}
}
