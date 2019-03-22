package com.rmrichard.libgdx.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class BaseGame extends Game {

    public Skin skin;

    public BaseGame() {
        this.skin = new Skin();
    }

    public abstract void create();

    public void dispose() {
        skin.dispose();
    }
}
