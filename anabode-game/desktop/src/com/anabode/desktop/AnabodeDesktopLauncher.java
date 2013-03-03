package com.anabode.desktop;

import com.anabode.controller.SceneController;
import com.anabode.screen.ScreenHandler;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import java.util.prefs.Preferences;

/**
 * @author Kristaps Kohs
 */
public class AnabodeDesktopLauncher {
    public static void main(String[] args) {

        TexturePacker2.Settings settings = new TexturePacker2.Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        TexturePacker2.process(settings, "assets-prepacked/textures/welcome/", "assets/textures/welcome", "welcome");
        TexturePacker2.process(settings, "assets-prepacked/textures//menu/", "assets/textures/menu", "menu");
        TexturePacker2.process(settings, "assets-prepacked/textures/level1", "assets/textures/level1", "level1");


        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.setController(new SceneController());

        int width = Preferences.userRoot().getInt("anabode.screen.width", 800);
        int height = Preferences.userRoot().getInt("anabode.screen.height", 600);

        new LwjglApplication(screenHandler, "Anabode", width, height, true);
    }
}
