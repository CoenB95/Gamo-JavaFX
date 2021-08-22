package com.cbapps.gamo.state;

import com.cbapps.gamo.objects.Camera;
import com.cbapps.gamo.objects.GameObject;

public interface IGameScene {
	Camera getCamera();
	GameObject get2D();
	GameObject get3D();
	double getHeight();
	double getWidth();
	void popState();
	void pushState(IGameState state);
	void setState(IGameState state);
}