package com.rmrichard.libgdx.example;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ExampleLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.title = "ExampleGame";

        ExampleGame game = new ExampleGame();
        LwjglApplication app = new LwjglApplication(game, config);
    }
}
