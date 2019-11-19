package com.cbapps.javafx.gamo.apps;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class GameApp extends Application {
	private GameObjectGroup gameStage;
	private long lastNow = -1;
	private double elapsedSeconds;

	public boolean useStableTiming = true;

	public GameApp() {
		gameStage = new GameObjectGroup();
	}

	public GameObjectGroup getGameStage() {
		return gameStage;
	}

	public abstract void onStart(GameObjectGroup gameStage);

	@Override
	public void start(Stage primaryStage) {
		Pane root = new StackPane();

		Scene scene = new Scene(root, 600, 400, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.ANTIQUEWHITE);
		root.getChildren().add(gameStage.getGroup());
		onStart(gameStage);
		//scene.setOnKeyPressed(this::onKeyPressed);
		//scene.setOnKeyReleased(this::onKeyReleased);
		//scene.setOnMouseMoved(this::onMouseMove);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (lastNow < 0)
					lastNow = now;

				elapsedSeconds += (double) (now - lastNow) / 1_000_000_000.0;
				if (useStableTiming)
					gameStage.onUpdate(0.013);
				else
					gameStage.onUpdate(elapsedSeconds);
				lastNow = now;
			}
		};
		timer.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}