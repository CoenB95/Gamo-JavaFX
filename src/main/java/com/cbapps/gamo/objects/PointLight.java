package com.cbapps.gamo.objects;

import com.cbapps.gamo.javafx.GameObjectBase;

public class PointLight extends GameObjectBase {
    private final javafx.scene.PointLight light;

    public PointLight() {
        this(new javafx.scene.PointLight());
    }

    private PointLight(javafx.scene.PointLight light) {
        super(light);

        this.light = light;
    }

    public void on(boolean value) {
        light.setLightOn(value);
    }

    public void setMaxRange(double value) {
        light.setMaxRange(value);
        light.setLinearAttenuation(0.1);
    }
}
