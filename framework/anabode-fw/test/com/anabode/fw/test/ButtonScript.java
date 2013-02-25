package com.anabode.fw.test;

import com.anabode.fw.ActionScript;
import com.badlogic.gdx.Gdx;

/**
 * Date: 13.24.2
 * Time: 18:10
 *
 * @author Kristaps Kohs
 */
public class ButtonScript extends ActionScript {
    @Override
    protected void initialize() {

    }

    @Override
    protected void onGuiTouch() {
        Gdx.app.log("Button", "Button has been pressed!!");
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
