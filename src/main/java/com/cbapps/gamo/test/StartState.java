package com.cbapps.gamo.test;

import com.cbapps.gamo.components.FollowComponent;
import com.cbapps.gamo.components.SmoothTranslationComponent;
import com.cbapps.gamo.components.SpinComponent;
import com.cbapps.gamo.javafx.CubeObject;
import com.cbapps.gamo.javafx.GroupObject;
import com.cbapps.gamo.javafx.TextObject;
import com.cbapps.gamo.math.Quaternion;
import com.cbapps.gamo.math.Vector3;
import com.cbapps.gamo.state.GameStateBase;
import com.cbapps.gamo.state.IGameScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class StartState extends GameStateBase {
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
			getScene().setState(new CubeState());
		}
	}

	@Override
	public void onStart(IGameScene scene) {
		super.onStart(scene);

		var camera = scene.getCamera();
		camera.setClip(0.1, 100);
		camera.setPosition(Vector3.z(5));

		mouseCube.setOrientation(Quaternion.of(Vector3.unitX(), 45));

		welcomeText.setPosition(Vector3.z(1.5));
		welcomeText.setScale(0.0025);
		welcomeText.setFontSize(64);
		welcomeText.setCentered(true);
		welcomeText.textProperty().set("""
				===== WELCOME =====
				Hit space to start""");

		scene.get2D().addObjects(mouseCube, mouseDrag);
		scene.get3D().addObjects(cube, welcomeText);
	}

	@Override
	public void onResume() {
		cube.addComponent(new SpinComponent(Quaternion.of(Vector3.unitY(), 10)));

		mouseDrag.setPosition(new Vector3(getScene().getWidth() / 2, -getScene().getHeight() / 2, 0));

		mouseCube.setPosition(mouseDrag.getPosition());
		mouseCube.addComponent(new SpinComponent(Quaternion.of(Vector3.unitY(), 30)));
		mouseCube.addComponent(FollowComponent.translating(mouseDrag));
		mouseCube.addComponent(new SmoothTranslationComponent(0.3));
	}

	@Override
	public void onPause() {
		cube.removeAllComponents();
		mouseCube.removeAllComponents();
	}

	@Override
	public void onStop() {
		getScene().get2D().removeObjects(mouseCube, mouseDrag);
		getScene().get3D().removeObjects(cube, welcomeText);
	}

	@Override
	public void onMouseMove3D(MouseEvent event) {
		mouseDrag.setPosition(new Vector3(event.getSceneX(), -event.getSceneY(), 0));
	}
}
