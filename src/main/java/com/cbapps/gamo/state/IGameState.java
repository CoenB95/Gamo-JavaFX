package com.cbapps.gamo.state;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface IGameState {
    void onKeyPressed(KeyEvent event);

    void onKeyReleased(KeyEvent event);

    void onMouseMove2D(MouseEvent event);

    void onMouseMove3D(MouseEvent event);

    void onPause();

    void onResume();

    void onStart(IGameScene scene);

    void onStop();

    void onUpdate(double elapsedSeconds);
}
