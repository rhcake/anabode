package com.anabode.fw.test;

import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;

/**
 * @author Modris Vekmanis
 */
public class TestScreen extends AbstractScreen {
    private BaseTest baseTest;

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        baseTest = new BaseTest();
        baseTest.setScreen(this);
        baseTest.create();
    }

    @Override
    public void update() {
        baseTest.getBase().update();
    }

    @Override
    public void render(float delta) {
        baseTest.getBase().render();
    }
}
