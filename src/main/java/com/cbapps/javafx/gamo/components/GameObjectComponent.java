package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public abstract class GameObjectComponent {
	private GameObject parentObject;

	protected final GameObject getParentObject() {
		return parentObject;
	}

	protected void onAttach(GameObject newParent) {}

	protected void onDetach(GameObject oldParent) {}

	public abstract void onUpdate(double elapsedSeconds);

	public final void setParentObject(GameObject parent) {
		if (parentObject != null)
			onDetach(parentObject);

		parentObject = parent;

		if (parentObject != null)
			onAttach(parentObject);
	}
}
