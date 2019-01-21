package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameVector;

public interface GameObjectComponent {
	GameVector onUpdate(double elapsedSeconds, GameVector target);
}
