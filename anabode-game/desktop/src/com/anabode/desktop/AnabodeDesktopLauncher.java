package com.anabode.desktop;

import com.anabode.controller.SceneController;
import com.anabode.screen.ScreenHandler;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * @author Kristaps Kohs
 */
public class AnabodeDesktopLauncher {
    public static void main(String[] args) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.setController(new SceneController());
        new LwjglApplication(screenHandler, "Anabode", 800, 600, true);
    }
}
