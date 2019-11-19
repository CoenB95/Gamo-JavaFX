package com.cbapps.javafx.gamo.objects;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.components.GameObjectComponent;
import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.Rotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GameObject {
	private GameObjectGroup parentGroup;
	private List<GameObjectComponent> components = new ArrayList<>();
	private Position position;
	private Rotation rotation;
	private double scale;

	public GameObject() {
		this.position = Position.ORIGIN;
		this.rotation = Rotation.ORIGIN;
		this.scale = 1.0;
	}

	public final void addComponent(GameObjectComponent component) {
		if (component == null)
			return;

		component.onAttach(this);
		components.add(component);
	}

	public final List<GameObjectComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	public GameObjectGroup getParentGroup() {
		return parentGroup;
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

	protected void onAttach(GameObjectGroup newParent) {}

	protected void onDetach(GameObjectGroup oldParent) {}

	public void onUpdate(double elapsedSeconds) {
		components.forEach(c -> c.onUpdate(elapsedSeconds));
	}

	public final boolean removeComponent(GameObjectComponent component) {
		if (component == null || !components.contains(component))
			return false;

		component.onDetach();
		components.remove(component);
		return true;
	}

	public void setParentGroup(GameObjectGroup parent) {
		if (parentGroup != null)
			onDetach(parentGroup);

		parentGroup = parent;

		if (parentGroup != null)
			onAttach(parentGroup);
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
