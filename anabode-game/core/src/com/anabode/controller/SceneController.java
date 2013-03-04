package com.anabode.controller;

import com.anabode.screen.GameController;
import com.anabode.screens.WelcomeScreen;
import com.anabode.screens.menu.Menu;
import com.anabode.util.Constants;

/**
 * @author Kristaps Kohs
 */
public class SceneController extends GameController {
    @Override
    public void initialize() throws Exception {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        screenHandler.loadActiveScreen(welcomeScreen);
        welcomeScreen.handleInput();
    }

    @Override
    public void update() {

    }

    public void continueOnWelcomeScreen() {
        try {
            screenHandler.unloadScreen(Constants.WELCOME_SCREEN);
            screenHandler.loadActiveScreen(new Menu());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
