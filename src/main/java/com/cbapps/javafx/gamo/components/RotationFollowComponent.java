package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public class RotationFollowComponent extends GameObjectComponent {

	private GameObject subject;
	private double snappyness;

	public RotationFollowComponent(GameObject subject, double snappyness) {
		this.snappyness = snappyness;
		this.subject = subject;
	}

	@Override
	public void onUpdate(double elapsedSeconds) {
		getParentObject().setTargetRotation(getParentObject().getRotation().multiply(snappyness).add(
				subject.getRotation().multiply(1.0 - snappyness)));
	}
}
