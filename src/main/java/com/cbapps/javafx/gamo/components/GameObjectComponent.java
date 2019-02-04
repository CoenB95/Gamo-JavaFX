package com.cbapps.javafx.gamo.components;

import com.cbapps.javafx.gamo.objects.GameObject;

public interface GameObjectComponent {
	void onAttach(GameObject object);
	void onDetach();
	void onUpdate(double elapsedSeconds);
}
