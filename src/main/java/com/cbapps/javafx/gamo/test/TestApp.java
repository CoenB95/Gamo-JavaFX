package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.apps.GameApp;
import com.cbapps.javafx.gamo.groups.GameObjectGroup;

public class TestApp extends GameApp {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void onStart(GameObjectGroup gameStage) {
		gameStage.addObject(new TestScene());
	}
}
