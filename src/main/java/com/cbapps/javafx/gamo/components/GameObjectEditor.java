package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameVector;

public interface GameObjectEditor {
	GameVector onUpdate(double elapsedSeconds, GameVector current, GameVector target);
}
