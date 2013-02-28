package com.anabode.screen;

import com.anabode.fw.Base;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

/**
 * Date: 13.28.2
 * Time: 13:01
 *
 * @author Kirstaps Kohs
 */
public abstract class AbstractScreen implements Screen {
    protected GameManager gameManager;

    protected AbstractScreen(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    protected abstract void init();

    public abstract Base getBase();

    public abstract AssetManager getAssetManager();

    public abstract String getScreenName();

    protected AbstractScreen getCurrentScreen() {
        return gameManager.getCurrentScreen();
    }

    protected AbstractScreen getNextScreen() {
        return gameManager.getNextScreen();
    }
}
