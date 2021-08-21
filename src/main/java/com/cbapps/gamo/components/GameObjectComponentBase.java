package com.cbapps.gamo.components;

import com.cbapps.gamo.objects.GameObject;

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
