package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public abstract class GameObjectComponentBase implements GameObjectComponent {
	private GameObject parentObject;

	public GameObject getParentObject() {
		return parentObject;
	}

	public boolean isAttached() {
		return parentObject != null;
	}

	@Override
	public void onAttach(GameObject object) {
		parentObject = object;
	}

	@Override
	public void onDetach() {
		parentObject = null;
	}
}
