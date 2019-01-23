package com.cbapps.javafx.gamo.test;

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

public class TestApp extends Application {
	private GameScene mainScene;

	@Override
	public void start(Stage primaryStage) {
		Pane group = new StackPane();
		Scene scene = new Scene(group, 600, 400, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.ANTIQUEWHITE);

		mainScene = new TestScene(scene, group);

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainScene.onUpdate(0.013);
			}
		}.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
