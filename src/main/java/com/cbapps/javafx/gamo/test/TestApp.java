package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.apps.GameApp;
import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.math.RotationalDelta;
import com.cbapps.javafx.gamo.scenes.GameScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestApp extends GameApp {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void onStart(GameObjectGroup gameStage) {
		gameStage.addObject(new TestScene());
	}
}
