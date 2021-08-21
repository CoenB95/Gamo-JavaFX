package com.cbapps.gamo.components;

public class DespawnComponent extends GameObjectComponentBase {
    private double secondsLeft;

    public DespawnComponent(double elapsedSeconds) {
        this.secondsLeft = elapsedSeconds;
    }

    @Override
    public void onUpdate(double elapsedSeconds) {
        secondsLeft -= elapsedSeconds;

        if (secondsLeft <= 0) {
            getParentObject().getParent().ifPresent(parent -> parent.removeObject(getParentObject()));
        }
    }
}
