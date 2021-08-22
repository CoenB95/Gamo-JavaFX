package com.cbapps.gamo.javafx;

import com.cbapps.gamo.objects.Camera;
import com.cbapps.gamo.objects.GameObject;
import com.cbapps.gamo.state.IGameScene;
import com.cbapps.gamo.state.GameStateBase;
import com.cbapps.gamo.state.IGameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public abstract class JavaFXGameApp extends Application implements IGameScene {
	private final Camera camera;
	private final GameObjectBase group2D;
	private final GameObjectBase group3D;
	private final Deque<IGameState> states;

	private SubScene scene2D;
	private UpdateMode updateMode;
	private Long lastNow;

	public JavaFXGameApp() {
		this.camera = new Camera();
		this.group2D = new GroupObject();
		this.group3D = new GroupObject();
		this.states = new LinkedList<>();
	}

	protected abstract IGameState createState();

	@Override
	public void init() {
		updateMode = UpdateMode.valueOf(getParameters()
				.getNamed()
				.getOrDefault("update-mode", UpdateMode.SOLID.name()));
	}

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

		SubScene scene3D = new SubScene(group3D.getGroup(), 300, 300, true, SceneAntialiasing.BALANCED);
		scene3D.setFill(Color.TRANSPARENT);
		scene3D.heightProperty().bind(root.heightProperty());
		scene3D.widthProperty().bind(root.widthProperty());
		scene3D.setOnMouseMoved(this::onMouseMove3D);
		scene3D.setCamera(camera.camera);
		group3D.addObject(camera);

		root.getChildren().addAll(scene3D, scene2D);

		var state = createState();
		Objects.requireNonNull(state, "State must be provided");
		setState(state);

		var timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (lastNow == null)
					lastNow = now;

				var elapsedSeconds = switch (updateMode) {
					case REALTIME -> (double) (now - lastNow) / 1_000_000_000.0;
					case SOLID -> 0.013;
				};

				if (states.isEmpty()) {
					return;
				}

				states.peek().onUpdate(elapsedSeconds);
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
	public GameObject get2D() {
		return group2D;
	}

	@Override
	public GameObject get3D() {
		return group3D;
	}

	@Override
	public double getWidth() {
		return scene2D.getWidth();
	}

	private void onKeyPressed(KeyEvent event)
	{
		if (!states.isEmpty()) {
			states.peek().onKeyPressed(event);
		}
	}

	private void onKeyReleased(KeyEvent event)
	{
		if (!states.isEmpty()) {
			states.peek().onKeyReleased(event);
		}
	}

	private void onMouseMove2D(MouseEvent event) {
		if (!states.isEmpty()) {
			states.peek().onMouseMove2D(event);
		}
	}

	private void onMouseMove3D(MouseEvent event) {
		if (!states.isEmpty()) {
			states.peek().onMouseMove3D(event);
		}
	}

	@Override
	public void popState() {
		if (states.isEmpty()) {
			return;
		}

		var state = states.pop();
		state.onPause();
		state.onStop();

		var previousState = states.peek();

		if (previousState != null) {
			previousState.onResume();
		}
	}

	@Override
	public void pushState(IGameState newState) {
		Objects.requireNonNull(newState);

		var previousState = states.peek();

		if (previousState != null) {
			previousState.onPause();
		}

		states.push(newState);
		newState.onStart(this);
		newState.onResume();
	}

	@Override
	public void setState(IGameState newState) {
		var it = states.descendingIterator();

		while (it.hasNext()) {
			var previousState = it.next();
			previousState.onPause();
			previousState.onStop();
		}

		states.clear();
		states.push(newState);
		newState.onStart(this);
		newState.onResume();
	}

	public enum UpdateMode {
		REALTIME,
		SOLID
	}
}