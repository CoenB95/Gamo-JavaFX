package com.cbapps.gamo.objects;

import com.cbapps.gamo.components.GameObjectComponent;
import com.cbapps.gamo.math.Quaternion;
import com.cbapps.gamo.math.Vector3;

import java.util.*;

public abstract class GameObject {
	private List<GameObject> children = new ArrayList<>();
	private List<GameObjectComponent> components = new ArrayList<>();
	private Quaternion orientation;
	private GameObject parent;
	private Vector3 position;
	private double scale;

	public GameObject() {
		this.position = Vector3.identity();
		this.orientation = Quaternion.identity();
		this.scale = 1.0;
	}

	public final void addComponent(GameObjectComponent component) {
		Objects.requireNonNull(component);

		component.onAttach(this);
		components.add(component);
	}

	public final List<GameObjectComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	public void addObject(GameObject object) {
		Objects.requireNonNull(object);

		object.onAttach(this);
		children.add(object);
	}

	public final void addObjects(Collection<? extends GameObject> objects) {
		objects.forEach(this::addObject);
	}

	public final void addObjects(GameObject... objects) {
		addObjects(Arrays.asList(objects));
	}

	public Quaternion getOrientation() {
		return orientation;
	}

	public Optional<GameObject> getParent() {
		return Optional.ofNullable(parent);
	}

	public Vector3 getPosition() {
		return position;
	}

	public double getScale() {
		return scale;
	}

	protected void onAttach(GameObject newParent) {
		this.parent = newParent;
	}

	protected void onDetach() {
		this.parent = null;
	}

	public void onUpdate(double elapsedSeconds) {
		var childrenSnapshot = List.copyOf(children);
		childrenSnapshot.forEach(o -> o.onUpdate(elapsedSeconds));

		var componentsSnapshot = List.copyOf(components);
		componentsSnapshot.forEach(c -> c.onUpdate(elapsedSeconds));
	}

	public final void removeAllComponents() {
		var componentsSnapshot = List.copyOf(components);
		componentsSnapshot.forEach(this::removeComponent);
	}

	public final boolean removeComponent(GameObjectComponent component) {
		if (component == null || !components.contains(component))
			return false;

		component.onDetach();
		components.remove(component);
		return true;
	}

	public boolean removeObject(GameObject object) {
		if (object == null || !children.contains(object))
			return false;

		object.onDetach();
		children.remove(object);
		return true;
	}

	public void removeObjects(GameObject... objects) {
		removeObjects(Arrays.asList(objects));
	}

	public void removeObjects(Collection<GameObject> objects) {
		var childrenSnapshot = List.copyOf(children);
		childrenSnapshot.stream()
				.filter(objects::contains)
				.forEach(child -> {
					child.removeAllComponents();
					removeObject(child);
				});
	}

	public void setOrientation(Quaternion orientation) {
		this.orientation = orientation;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
