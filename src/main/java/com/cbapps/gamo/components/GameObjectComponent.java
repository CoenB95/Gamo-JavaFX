package com.cbapps.gamo.components;

import com.cbapps.gamo.objects.GameObject;

public interface GameObjectComponent {
	void onAttach(GameObject object);
	void onDetach();
	void onUpdate(double elapsedSeconds);
}
