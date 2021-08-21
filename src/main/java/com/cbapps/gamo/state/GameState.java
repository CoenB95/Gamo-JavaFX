package com.cbapps.gamo.state;

import com.cbapps.gamo.objects.Camera;
import com.cbapps.gamo.objects.GameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameState implements GameApp {
    private final Map<KeyCode, Boolean> keyMap;

    private GameApp app;

    public GameState() {
        this.keyMap = new HashMap<>();
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

    public void onStart() {

    }

    public void onStop() {

    }

    public void onUpdate(double elapsedSeconds) {

    }

    @Override
    public Camera getCamera() {
        return app.getCamera();
    }

    @Override
    public double getHeight() {
        return app.getHeight();
    }

    @Override
    public GameObject getScene2D() {
        return app.getScene2D();
    }

    @Override
    public GameObject getScene3D() {
        return app.getScene3D();
    }

    @Override
    public double getWidth() {
        return app.getWidth();
    }

    @Override
    public void setState(GameState state) {
        app.setState(state);
    }

    public final void start(GameApp app) {
        Objects.requireNonNull(app);

        this.app = app;
        onStart();
    }

    public final void stop() {
        onStop();

        this.app = null;
    }
}
