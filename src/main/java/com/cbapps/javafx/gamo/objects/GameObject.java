package com.cbapps.javafx.gamo.objects;

import com.cbapps.javafx.gamo.components.GameObjectEditor;
import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.components.GameObjectComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GameObject {
	private GameObjectGroup parentGroup;
	private List<GameObjectComponent> components = new ArrayList<>();
	private List<GameObjectEditor> editors = new ArrayList<>();
	private GameVector currentVector;
	private GameVector targetVector;

	public GameObject() {
		currentVector = new GameVector();
		targetVector = new GameVector();
	}

	public final void addComponent(GameObjectComponent component) {
		if (component == null)
			return;

		components.add(component);
	}

	public final void addEditor(GameObjectEditor editor) {
		if (editor == null)
			return;

		editors.add(editor);
	}

	public final List<GameObjectComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	public GameObjectGroup getParentGroup() {
		return parentGroup;
	}

	public GameVector getCurrentVector() {
		return currentVector;
	}

	public GameVector getTargetVector() {
		return targetVector;
	}

	protected void onAttach(GameObjectGroup newParent) {}

	protected void onDetach(GameObjectGroup oldParent) {}

	public void onUpdate(double elapsedSeconds) {
		components.forEach(c -> targetVector = c.onUpdate(elapsedSeconds, targetVector));

		//If no component is taking care of actually adjusting the position/rotation
		//based on the target, we'll do the basics.
		if (editors.isEmpty()) {
			currentVector = targetVector;
		} else {
			editors.forEach(e -> currentVector = e.onUpdate(elapsedSeconds, currentVector, targetVector));
		}
	}

	public final boolean removeComponent(GameObjectComponent component) {
		if (component == null || !components.contains(component))
			return false;

		components.remove(component);
		return true;
	}

	public final boolean removeEditor(GameObjectEditor editor) {
		if (editor == null || !editors.contains(editor))
			return false;

		editors.remove(editor);
		return true;
	}

	public void setParentGroup(GameObjectGroup parent) {
		if (parentGroup != null)
			onDetach(parent);

		parentGroup = parent;

		if (parentGroup != null)
			onAttach(parentGroup);
	}

	public void setTargetVector(GameVector targetVector) {
		this.targetVector = targetVector;
	}
}
