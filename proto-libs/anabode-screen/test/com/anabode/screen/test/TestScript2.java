package com.anabode.screen.test;

import com.anabode.fw.ActionScript;
import com.anabode.screen.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: Kristaps
 * Date: 13.28.2
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class TestScript2 extends ActionScript {
    private GameManager gameManager;

    public TestScript2(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void onGuiTouch() {
        gameManager.showScreen(TestScreen.NAME);
    }
}
