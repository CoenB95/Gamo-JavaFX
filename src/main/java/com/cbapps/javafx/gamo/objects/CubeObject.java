package com.cbapps.javafx.gamo.objects;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class CubeObject extends GameObjectBase {

	public CubeObject(double w, double h, double d) {
		Box box = new Box(w, h, d);
		box.setMaterial(new PhongMaterial(Color.GREEN));

		setNode(box);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
	}
}
