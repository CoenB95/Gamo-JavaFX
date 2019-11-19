package com.cbapps.javafx.gamo.scenes;

import com.cbapps.javafx.gamo.groups.GameObjectGroup;
import com.cbapps.javafx.gamo.objects.GameObject;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.*;

public class GameScene extends GameObject {
	private GameScene parentScene;

	GameObjectGroup group2D;
	GameObjectGroup group3D;
	SubScene scene2D;
	SubScene scene3D;

	private Map<KeyCode, Boolean> keyMap = new HashMap<>();

	public GameScene(Scene scene, Pane root) {
		super();

		group2D = new GameObjectGroup();
		group3D = new GameObjectGroup();

		scene2D = new SubScene(group2D.getGroup(), 310, 310);
		scene2D.setFill(Color.TRANSPARENT);
		scene2D.setMouseTransparent(true);
		scene2D.heightProperty().bind(root.heightProperty());
		scene2D.widthProperty().bind(root.widthProperty());

		scene3D = new SubScene(group3D.getGroup(), 300, 300, true, SceneAntialiasing.BALANCED);
		scene3D.setFill(Color.DARKBLUE);
		scene3D.heightProperty().bind(root.heightProperty());
		scene3D.widthProperty().bind(root.widthProperty());

		root.getChildren().addAll(scene3D, scene2D);

		LightBase light = new AmbientLight();
		group3D.getGroup().getChildren().add(light);

		scene.setOnKeyPressed(this::onKeyPressed);
		scene.setOnKeyReleased(this::onKeyReleased);
		scene3D.setOnMouseMoved(this::onMouseMove);
	}

	public final void add2DObject(GameObject object) {
		group2D.addObject(object);
	}

	public final void add2DObjects(Collection<? extends GameObject> objects) {
		objects.forEach(this::add2DObject);
	}

	public final void add3DObject(GameObject object) {
		group3D.addObject(object);
	}

	public final void add3DObjects(Collection<? extends GameObject> objects) {
		objects.forEach(this::add3DObject);
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

	@Override
	public void onUpdate(double elapsedSeconds) {
		super.onUpdate(elapsedSeconds);
		group2D.onUpdate(elapsedSeconds);
		group3D.onUpdate(elapsedSeconds);
	}

	public final void setCamera(Camera camera) {
		scene3D.setCamera(camera);
	}
}
