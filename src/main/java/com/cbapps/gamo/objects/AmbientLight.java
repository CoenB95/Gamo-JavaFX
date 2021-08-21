package com.cbapps.gamo.objects;

import com.cbapps.gamo.javafx.GameObjectBase;

public class AmbientLight extends GameObjectBase {
    private final javafx.scene.AmbientLight light;

    public AmbientLight() {
        this(new javafx.scene.AmbientLight());
    }

    private AmbientLight(javafx.scene.AmbientLight light) {
        super(light);

        this.light = light;
    }

    public void on(boolean value) {
        light.setLightOn(value);
    }
}
