package com.cbapps.gamo.javafx;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

public class CubeObject extends GameObjectBase {
	private final Box box;

	private CubeObject(Box box, Color color) {
		super(box);

		this.box = box;
		this.box.setMaterial(new PhongMaterial(color));
	}

	public static CubeObject of(double w, double h, double d) {
		return of(w, h, d, Color.GREEN);
	}

	public static CubeObject of(double w, double h, double d, Color color) {
		return new CubeObject(new Box(w, h, d), color);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
	}

	public void wireframe(boolean value) {
		this.box.setDrawMode(value ? DrawMode.LINE : DrawMode.FILL);
	}
}
