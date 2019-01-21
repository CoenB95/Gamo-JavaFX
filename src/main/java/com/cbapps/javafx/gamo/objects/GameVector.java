package com.cbapps.javafx.gamo.objects;

import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.Rotation;

public class GameVector {
	private Position position;
	private Rotation rotation;
	private double scale;

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
