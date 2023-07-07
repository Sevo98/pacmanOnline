package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class KeyboardAdapter extends InputAdapter {
    private final Vector2 direction = new Vector2();

    private final InputState inputState;

    public KeyboardAdapter(InputState inputState) {
        this.inputState = inputState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) inputState.setLeftPressed(true);
        if (keycode == Input.Keys.D) inputState.setRightPressed(true);
        if (keycode == Input.Keys.W) inputState.setUpPressed(true);
        if (keycode == Input.Keys.S) inputState.setDownPressed(true);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) inputState.setLeftPressed(false);
        if (keycode == Input.Keys.D) inputState.setRightPressed(false);
        if (keycode == Input.Keys.W) inputState.setUpPressed(false);
        if (keycode == Input.Keys.S) inputState.setDownPressed(false);

        return false;
    }

    public Vector2 getDirection() {
        direction.set(0, 0);

        if (inputState.isLeftPressed()) direction.add(-5, 0);
        if (inputState.isRightPressed()) direction.add(5, 0);
        if (inputState.isUpPressed()) direction.add(0, 5);
        if (inputState.isDownPressed()) direction.add(0, -5);

        return direction;
    }

    public InputState updateAndGetInputState(Vector2 playerOrigin) {

        return inputState;
    }
}
