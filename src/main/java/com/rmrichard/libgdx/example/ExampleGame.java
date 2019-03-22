package com.rmrichard.libgdx.example;

import com.rmrichard.libgdx.base.BaseGame;

public class ExampleGame extends BaseGame {

    @Override
    public void create() {
        setScreen(new ExampleLevel(this));
    }
}
