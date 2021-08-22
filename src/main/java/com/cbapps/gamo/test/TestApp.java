package com.cbapps.gamo.test;

import com.cbapps.gamo.javafx.JavaFXGameApp;
import com.cbapps.gamo.state.GameStateBase;

public class TestApp extends JavaFXGameApp {

	@Override
	public GameStateBase createState() {
		return new StartState();
	}
}
