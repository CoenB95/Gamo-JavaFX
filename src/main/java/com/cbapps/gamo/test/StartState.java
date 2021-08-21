package com.cbapps.gamo.test;

import com.cbapps.gamo.components.FollowComponent;
import com.cbapps.gamo.components.SmoothTranslationComponent;
import com.cbapps.gamo.components.SpinComponent;
import com.cbapps.gamo.javafx.CubeObject;
import com.cbapps.gamo.javafx.GroupObject;
import com.cbapps.gamo.javafx.TextObject;
import com.cbapps.gamo.math.Quaternion;
import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.state.GameState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDateTime;

public class StartState extends GameState {
	private final CubeObject cube;
	private final CubeObject mouseCube;
	private final GroupObject mouseDrag;
	private final TextObject welcomeText;

	public StartState() {
		this.cube = CubeObject.of(1, 1, 1, Color.DARKBLUE);
		this.mouseCube = CubeObject.of(50, 50, 50, Color.DARKORANGE);
		this.mouseDrag = new GroupObject();
		this.welcomeText = new TextObject();
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		super.onKeyPressed(event);

		if (event.getCode() == KeyCode.SPACE) {
			setState(new CubeState());
		}
	}

	@Override
	public void onStart() {
		var camera = getCamera();
		camera.setClip(0.1, 100);
		camera.setPosition(Vector3.z(5));

		cube.addComponent(new SpinComponent(Quaternion.of(Vector3.unitY(), 10)));

		mouseDrag.setPosition(new Vector3(getWidth() / 2, -getHeight() / 2, 0));

		mouseCube.setPosition(mouseDrag.getPosition());
		mouseCube.setOrientation(Quaternion.of(Vector3.unitX(), 45));
		mouseCube.addComponent(new SpinComponent(Quaternion.of(Vector3.unitY(), 30)));
		mouseCube.addComponent(FollowComponent.translating(mouseDrag));
		mouseCube.addComponent(new SmoothTranslationComponent(0.3));

		welcomeText.setPosition(Vector3.z(1.5));
		welcomeText.setScale(0.0025);
		welcomeText.setFontSize(64);
		welcomeText.setCentered(true);
		welcomeText.textProperty().set("""
				===== WELCOME =====
				Hit space to start""");

		getScene2D().addObjects(mouseCube, mouseDrag);
		getScene3D().addObjects(cube, welcomeText);
	}

	@Override
	public void onStop() {
		getScene2D().removeAllObjects(true);
		getScene3D().removeAllObjects(true);
	}

	@Override
	public void onMouseMove3D(MouseEvent event) {
		mouseDrag.setPosition(new Vector3(event.getSceneX(), -event.getSceneY(), 0));
	}
}
