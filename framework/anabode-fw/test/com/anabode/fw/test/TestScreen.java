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
        base = baseTest.getBase();
    }

    @Override
    public void update() {
        base.update();
    }

    public void render(float delta) {
        base.render();
    }
}
