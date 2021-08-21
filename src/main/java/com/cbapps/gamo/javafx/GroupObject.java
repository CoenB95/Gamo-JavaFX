package com.cbapps.gamo.javafx;

import javafx.scene.LightBase;

import java.util.Objects;

public class GroupObject extends GameObjectBase {

	public GroupObject() {
		super(null);
	}

	public GroupObject(LightBase light) {
		super(null);

		Objects.requireNonNull(light);

		getGroup().getChildren().add(light);
	}
}
