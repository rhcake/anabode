package com.anabode.fw.test;

import com.anabode.fw.AbstractScreen;
import com.anabode.fw.ActionScript;
import com.badlogic.gdx.Gdx;

/**
 * Date: 13.24.2
 * Time: 18:10
 *
 * @author Kristaps Kohs
 */
public class ButtonScript extends ActionScript {
    private AbstractScreen screen;

    public ButtonScript(AbstractScreen screen) {
        this.screen = screen;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void onGuiTouch() {
        Gdx.app.log("Button", "Button has been pressed!!");
        if (screen.isVisible()) {
            Gdx.app.log("Button", "Hiding screen: " + screen.getId());
            screen.setVisible(false);
        } else {
            Gdx.app.log("Button", "Rendering screen: " + screen.getId());
            screen.setVisible(true);

        }
    }

    @Override
    protected void onGuiEnter() {
        Gdx.app.log("Button", "Mouse is over button!!!!");
    }

    @Override
    protected void onGuiExit() {
        Gdx.app.log("Button", "Mouse left button!!!!");
    }
}
