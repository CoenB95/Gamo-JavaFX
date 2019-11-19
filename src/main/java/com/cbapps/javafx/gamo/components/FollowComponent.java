package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.math.Position;
import com.cbapps.javafx.gamo.math.PositionalDelta;
import com.cbapps.javafx.gamo.objects.GameObject;
import com.cbapps.javafx.gamo.objects.GameVector;

public class FollowComponent extends GameObjectComponentBase {

	private GameObject subject;
	private PositionalDelta offset;
	private boolean rotate;
	private boolean translate;

	private FollowComponent(GameObject subject, PositionalDelta offset, boolean rotate, boolean translate) {
		this.subject = subject;
		this.offset = offset;
		this.rotate = rotate;
		this.translate = translate;
	}

	public static FollowComponent rotating(GameObject subject) {
		return rotating(subject, PositionalDelta.ZERO);
	}

	public static FollowComponent rotating(GameObject subject, PositionalDelta offset) {
		return new FollowComponent(subject, offset, true, false);
	}

	public static FollowComponent rotatingAndTranslating(GameObject subject) {
		return rotatingAndTranslating(subject, PositionalDelta.ZERO);
	}

	public static FollowComponent rotatingAndTranslating(GameObject subject, PositionalDelta offset) {
		return new FollowComponent(subject, offset, true, true);
	}

	public static FollowComponent translating(GameObject subject) {
		return translating(subject, PositionalDelta.ZERO);
	}

	public static FollowComponent translating(GameObject subject, PositionalDelta offset) {
		return new FollowComponent(subject, offset, false, true);
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		if (rotate)
			getParentObject().setRotation(subject.getRotation());

		if (translate)
			getParentObject().setPosition(subject.getPosition().add(getParentObject().getRotation(), offset).asPosition());
	}
}
