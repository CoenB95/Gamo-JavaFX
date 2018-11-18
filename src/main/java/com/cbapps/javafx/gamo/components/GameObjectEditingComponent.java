package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public abstract class GameObjectEditingComponent extends GameObjectComponent {
	private GameObject.Editor parentObjectEditor;

	protected final GameObject.Editor getEditableParentObject() {
		return parentObjectEditor;
	}

	@Override
	public final void setParentObject(GameObject parent) {
		super.setParentObject(parent);

		if (parent != null)
		parentObjectEditor = parent.getEditor(this);
	}
}
