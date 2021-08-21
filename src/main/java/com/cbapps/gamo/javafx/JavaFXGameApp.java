package com.cbapps.gamo.javafx;

import com.cbapps.gamo.objects.Camera;
import com.cbapps.gamo.objects.GameObject;
import com.cbapps.gamo.state.GameApp;
import com.cbapps.gamo.state.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class JavaFXGameApp extends Application implements GameApp {
	private final Camera camera;
	private final GameObjectBase group2D;
	private final GameObjectBase group3D;
	private final UpdateMode updateMode;

	private SubScene scene2D;
	private SubScene scene3D;
	private Long lastNow;
	private GameState state;

	public JavaFXGameApp() {
		this(UpdateMode.SOLID);
	}

	public JavaFXGameApp(UpdateMode updateMode) {
		Objects.requireNonNull(updateMode);

		this.camera = new Camera();
		this.group2D = new GroupObject();
		this.group3D = new GroupObject();
		this.updateMode = updateMode;
	}

	public abstract GameState createState();

	@Override
	public void start(Stage primaryStage) {
		Pane root = new StackPane();

		Scene scene = new Scene(root, 600, 400, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.ANTIQUEWHITE);
		scene.setOnKeyPressed(this::onKeyPressed);
		scene.setOnKeyReleased(this::onKeyReleased);

		scene2D = new SubScene(group2D.getGroup(), 310, 310);
		scene2D.setFill(Color.TRANSPARENT);
		scene2D.setMouseTransparent(true);
		scene2D.heightProperty().bind(root.heightProperty());
		scene2D.widthProperty().bind(root.widthProperty());
		scene2D.setOnMouseMoved(this::onMouseMove2D);

		scene3D = new SubScene(group3D.getGroup(), 300, 300, true, SceneAntialiasing.BALANCED);
		scene3D.setFill(Color.TRANSPARENT);
		scene3D.heightProperty().bind(root.heightProperty());
		scene3D.widthProperty().bind(root.widthProperty());
		scene3D.setOnMouseMoved(this::onMouseMove3D);
		scene3D.setCamera(camera.camera);
		group3D.addObject(camera);

		root.getChildren().addAll(scene3D, scene2D);

		state = createState();
		Objects.requireNonNull(state, "State must be provided");
		state.start(this);

		var timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (lastNow == null)
					lastNow = now;

				var elapsedSeconds = switch (updateMode) {
					case REALTIME -> (double) (now - lastNow) / 1_000_000_000.0;
					case SOLID -> 0.013;
				};

				if (state == null) {
					return;
				}

				state.onUpdate(elapsedSeconds);
				group2D.onUpdate(elapsedSeconds);
				group3D.onUpdate(elapsedSeconds);
				lastNow = now;
			}
		};
		timer.start();

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public Camera getCamera() {
		return camera;
	}

	@Override
	public double getHeight() {
		return scene2D.getHeight();
	}

	@Override
	public GameObject getScene2D() {
		return group2D;
	}

	@Override
	public GameObject getScene3D() {
		return group3D;
	}

	@Override
	public double getWidth() {
		return scene2D.getWidth();
	}

	private void onKeyPressed(KeyEvent event)
	{
		if (state != null) {
			state.onKeyPressed(event);
		}
	}

	private void onKeyReleased(KeyEvent event)
	{
		if (state != null) {
			state.onKeyReleased(event);
		}
	}

	private void onMouseMove2D(MouseEvent event) {
		if (state != null) {
			state.onMouseMove2D(event);
		}
	}

	private void onMouseMove3D(MouseEvent event) {
		if (state != null) {
			state.onMouseMove3D(event);
		}
	}

	@Override
	public void setState(GameState state) {
		this.state.stop();
		this.state = state;
		this.state.start(this);
	}

	public enum UpdateMode {
		REALTIME,
		SOLID
	}
}