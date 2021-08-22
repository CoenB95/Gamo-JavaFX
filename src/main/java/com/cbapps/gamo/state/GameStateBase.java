package com.cbapps.gamo.state;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class GameStateBase implements IGameState {
    private final Map<KeyCode, Boolean> keyMap;

    private IGameScene scene;

    public GameStateBase() {
        this.keyMap = new HashMap<>();
    }

    public IGameScene getScene() {
        return scene;
    }

    protected final boolean isKeyPressed(KeyCode key) {
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

    public void onMouseMove2D(MouseEvent event) {

    }

    public void onMouseMove3D(MouseEvent event) {

    }

    public void onPause() {
        keyMap.clear();
    }

    public void onResume() {

    }

    public void onStart(IGameScene scene) {
        Objects.requireNonNull(scene);

        this.scene = scene;
    }

    public void onStop() {

    }

    public void onUpdate(double elapsedSeconds) {

    }
}
