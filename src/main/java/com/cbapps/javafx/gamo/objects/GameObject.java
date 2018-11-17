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
	private Position targetPosition;
	private Rotation targetRotation;

	public GameObject() {
		targetPosition = position = Position.ORIGIN;
		targetRotation = rotation = Rotation.ORIGIN;
	}

	public void addComponent(GameObjectComponent component) {
		component.setParentObject(this);
		components.add(component);
	}

	public List<GameObjectComponent> getComponents() {
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

	public void onUpdate(double elapsedSeconds) {
		components.forEach(c -> c.onUpdate(elapsedSeconds));
		position = targetPosition;
		rotation = targetRotation;
	}

	public void setParent(GameObjectGroup parent){
		parentGroup = parent;
	}

	public void setTargetPosition(Position targetPosition) {
		this.targetPosition = targetPosition;
	}

	public void setTargetRotation(Rotation targetRotation) {
		this.targetRotation = targetRotation;
	}
}
