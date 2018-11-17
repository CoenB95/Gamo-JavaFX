package com.cbapps.javafx.gamo.groups;

import com.cbapps.javafx.gamo.objects.GameObject;
import com.cbapps.javafx.gamo.objects.GameObjectBase;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameObjectGroup extends GameObjectBase {
	private Group group;
	private List<GameObject> objects = new ArrayList<>();

	public GameObjectGroup() {
		this.group = new Group();

		setNode(group);
	}

	public final void addObject(GameObject object) {
		object.setParent(this);
		objects.add(object);
	}

	public final void addObjects(Collection<? extends GameObject> objects) {
		objects.forEach(this::addObject);
	}

	public final Group getGroup() {
		return group;
	}

	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
		objects.forEach(o -> o.onUpdate(elapsedSeconds));
	}

	public final void removeObject(GameObject object) {
		objects.remove(object);
	}

	public final void removeObjects(Collection<? extends GameObject> objects) {
		this.objects.forEach(this::removeObject);
	}
}
