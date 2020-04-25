package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public class FollowComponent extends GameObjectComponentBase {

	private GameObject subject;
	private boolean rotate;
	private boolean translate;

	private FollowComponent(GameObject subject, boolean rotate, boolean translate) {
		this.subject = subject;
		this.rotate = rotate;
		this.translate = translate;
	}

	public static FollowComponent rotating(GameObject subject) {
		return new FollowComponent(subject, true, false);
	}

	public static FollowComponent rotatingAndTranslating(GameObject subject) {
		return new FollowComponent(subject, true, true);
	}

	public static FollowComponent translating(GameObject subject) {
		return new FollowComponent(subject, false, true);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		if (rotate)
			getParentObject().setRotation(subject.getRotation());

		if (translate)
			getParentObject().setPosition(subject.getPosition());
	}
}
