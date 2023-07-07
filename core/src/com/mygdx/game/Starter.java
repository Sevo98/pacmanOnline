package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class Starter extends ApplicationAdapter {
    SpriteBatch batch;
    private String meId;
    private ObjectMap<String, Pacman> pacmans = new ObjectMap<>();

    private final KeyboardAdapter inputProcessor;

    private MessageSender messageSender;

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public Starter(InputState inputState) {
        this.inputProcessor = new KeyboardAdapter(inputState);
    }


    @Override
    public void create() {
        Gdx.input.setInputProcessor(inputProcessor);

        batch = new SpriteBatch();

        Pacman me = new Pacman(100, 200);
        pacmans.put(meId, me);

    }

    @Override
    public void render() {

        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        for (String key : pacmans.keys()) {
            pacmans.get(key).render(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Pacman value : pacmans.values()) {
            value.dispose();
        }
    }

    public void handleTimer() {
        if (inputProcessor != null) {
            Pacman me = pacmans.get(meId);
            InputState playerState = inputProcessor.updateAndGetInputState(me.getOrigin());
            messageSender.sendMessage(playerState);
        }
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public void evict(String idToEvict) {
        pacmans.remove(idToEvict);
    }

    public void updatePacman(String id, float x, float y) {
        if (pacmans.isEmpty()) {
            return;
        }

        Pacman pacman = pacmans.get(id);
        if (pacman == null) {
            pacman = new Pacman(x, y, "pacman_open.png");
            pacmans.put(id, pacman);
        } else {
            pacman.moveTo(x, y);
        }

    }
}
