package com.anabode.scripts.ui;

import com.anabode.controller.SceneController;
import com.anabode.fw.ActionScript;
import com.badlogic.gdx.Gdx;

/**
 * @author Kristaps Kohs
 */
public class NewGameScript extends ActionScript {
    private SceneController sceneController;

    public NewGameScript(SceneController screenHandler) {
        this.sceneController = screenHandler;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void onGuiTouch() {
        Gdx.app.log(NewGameScript.class.getName(), "Start new game");
    }
}
