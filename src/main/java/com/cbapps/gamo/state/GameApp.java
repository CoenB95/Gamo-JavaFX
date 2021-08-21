package com.cbapps.gamo.state;

import com.cbapps.gamo.objects.Camera;
import com.cbapps.gamo.objects.GameObject;

public interface GameApp {
	Camera getCamera();
	double getHeight();
	GameObject getScene2D();
	GameObject getScene3D();
	double getWidth();
	void setState(GameState state);
}