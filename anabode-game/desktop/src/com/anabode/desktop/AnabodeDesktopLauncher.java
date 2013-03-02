package com.anabode.desktop;

import com.anabode.controller.SceneController;
import com.anabode.screen.ScreenHandler;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import java.util.prefs.Preferences;

/**
 * @author Kristaps Kohs
 */
public class AnabodeDesktopLauncher {
    public static void main(String[] args) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.setController(new SceneController());

        int width = Preferences.userRoot().getInt("anabode.screen.width", 800);
        int height = Preferences.userRoot().getInt("anabode.screen.height", 600);

        new LwjglApplication(screenHandler, "Anabode", width, height, true);
    }
}
