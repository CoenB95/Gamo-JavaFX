package com.cbapps.javafx.gamo.test;

import com.cbapps.javafx.gamo.math.Rotation;
import com.cbapps.javafx.gamo.math.RotationalDelta;
import com.cbapps.javafx.gamo.scenes.GameScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestApp extends Application {
	private GameScene mainScene;

	@Override
	public void start(Stage primaryStage) {
		for (int i = 0; i < 100; i++) {
			Rotation r1 = Rotation.ORIGIN.withHorizontal(Math.random() * 360);
			Rotation r2 = Rotation.ORIGIN.withHorizontal(Math.random() * 360);
			RotationalDelta small = r1.smallestDeltaTo(r2);
			System.out.format("r1:%6.1f |r2:%6.1f |smallest:%6.1f%n", r1.getHorizontal(), r2.getHorizontal(), small.getHorizontal());
		}

		Pane group = new StackPane();
		Scene scene = new Scene(group, 600, 400);
		scene.setFill(Color.ANTIQUEWHITE);

		mainScene = new TestScene(scene, group);

		//Walking
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
