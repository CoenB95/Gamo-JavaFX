package com.cbapps.javafx.gamo.apps;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public abstract class GameApp extends Application {
	private GameObjectGroup gameStage;
	private long lastNow = -1;
	private double elapsedSeconds;
	private Map<KeyCode, Boolean> keyMap = new HashMap<>();

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
		scene.setOnKeyPressed(this::onKeyPressed);
		scene.setOnKeyReleased(this::onKeyReleased);
		scene.setOnMouseMoved(this::onMouseMove);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (lastNow < 0)
					lastNow = now;

				elapsedSeconds += (double) (now - lastNow) / 1_000_000_000.0;
				if (useStableTiming)
					onUpdate(0.013);
				else
					onUpdate(elapsedSeconds);
				lastNow = now;
			}
		};
		timer.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public final boolean isKeyPressed(KeyCode key) {
		return keyMap.getOrDefault(key, false);
	}

	public void onKeyPressed(KeyEvent event)
	{
		keyMap.put(event.getCode(), true);
	}

	public void onKeyReleased(KeyEvent event)
	{
		keyMap.put(event.getCode(), false);
	}

	public void onMouseMove(MouseEvent event) {

	}

	public void onUpdate(double elapsedSeconds) {
		gameStage.onUpdate(elapsedSeconds);
	}
}