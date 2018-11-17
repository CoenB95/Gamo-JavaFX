package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public class TranslationFollowComponent extends GameObjectComponent {

	private GameObject subject;
	private double snappyness;

	public TranslationFollowComponent(GameObject subject, double snappyness) {
		this.snappyness = snappyness;
		this.subject = subject;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		getParentObject().setTargetPosition(getParentObject().getPosition().multiply(snappyness).add(
				subject.getPosition().multiply(1.0 - snappyness)));
	}
}
