package com.anabode.fw.test;

import com.anabode.screen.GameController;
import com.badlogic.gdx.Gdx;

/**
 * @author Modris Vekmanis
 */
public class TestController extends GameController {
    private TestScreen screen;
    private int frameCounter = 0;
    private int idCounter = 1;

    @Override
    public void initialize() throws Exception {
        screen = new TestScreen();
        screen.setId("0");
        screenHandler.loadActiveScreen(screen);
        screen.handleInput();
    }

    @Override
    public void update() {
        try {
            frameCounter++;
            if (frameCounter == 600) {
                frameCounter = 0;
                Gdx.app.log("TestController", "Loading additional screen");
                TestScreen additionalScreen = new TestScreen();

                additionalScreen.setId(String.valueOf(idCounter++));

                additionalScreen.setRenderOrder(idCounter);
                screenHandler.loadActiveScreen(additionalScreen);
                additionalScreen.handleInput();
            } else if (frameCounter == 300 && idCounter > 2) {
                Gdx.app.log("TestController", "Unloading screen: " + (idCounter - 3));
                screenHandler.unloadScreen(String.valueOf(idCounter - 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
