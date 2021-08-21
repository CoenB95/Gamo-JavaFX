package com.cbapps.gamo.test;

import com.cbapps.gamo.javafx.JavaFXGameApp;
import com.cbapps.gamo.state.GameState;

public class TestApp extends JavaFXGameApp {
	// Main program entry
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public GameState createState() {
		return new StartState();
	}
}
