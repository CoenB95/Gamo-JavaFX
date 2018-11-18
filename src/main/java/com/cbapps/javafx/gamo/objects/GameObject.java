package com.cbapps.javafx.gamo.objects;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.components.GameObjectComponent;
import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.Rotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class GameObject {
	private GameObjectGroup parentGroup;
	private List<GameObjectComponent> components = new ArrayList<>();
	private List<GameObjectComponent> editableComponents = new ArrayList<>();
	private Position position;
	private Rotation rotation;
	private Position targetPosition;
	private Rotation targetRotation;

	public GameObject() {
		targetPosition = position = Position.ORIGIN;
		targetRotation = rotation = Rotation.ORIGIN;
	}

	public final void addComponent(GameObjectComponent component) {
		if (component == null)
			return;

		components.add(component);
		component.setParentObject(this);
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

	public Position getTargetPosition() {
		return targetPosition;
	}

	public Rotation getTargetRotation() {
		return targetRotation;
	}

	protected void onAttach(GameObjectGroup newParent) {}

	protected void onDetach(GameObjectGroup oldParent) {}

	public void onUpdate(double elapsedSeconds) {
		components.forEach(c -> c.onUpdate(elapsedSeconds));

		//If no component is taking care of actually adjusting the position/rotation
		//based on the target, we'll do the basics.
		if (editableComponents.isEmpty()) {
			position = targetPosition;
			rotation = targetRotation;
		}
	}

	public final boolean removeComponent(GameObjectComponent component) {
		if (component == null || !components.contains(component))
			return false;

		component.setParentObject(null);
		components.remove(component);
		editableComponents.remove(component);
		return true;
	}

	public void setParentGroup(GameObjectGroup parent) {
		if (parentGroup != null)
			onDetach(parent);

		parentGroup = parent;

		if (parentGroup != null)
			onAttach(parentGroup);
	}

	public final Editor getEditor(GameObjectComponent component) {
		if (component == null || !components.contains(component))
			return null;

		if (!editableComponents.contains(component))
			editableComponents.add(component);

		return new Editor();
	}

	public final void setTargetPosition(Position targetPosition) {
		this.targetPosition = targetPosition;
	}

	public final void setTargetRotation(Rotation targetRotation) {
		this.targetRotation = targetRotation;
	}

	public class Editor {
		public final void setPosition(Position nextPosition) {
			position = nextPosition;
		}

		public final  void setRotation(Rotation nextRotation) {
			rotation = nextRotation;
		}
	}
}
