package com.anabode.controller;

import com.anabode.screen.GameController;
import com.anabode.screens.WelcomeScreen;

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
    public void update() throws Exception {
    }

    public void continueOnWelcomeScreen() {
        System.out.println("asd");
    }
}
