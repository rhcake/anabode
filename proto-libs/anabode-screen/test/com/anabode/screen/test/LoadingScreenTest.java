package com.anabode.screen.test;

import com.anabode.screen.GameManager;
import com.anabode.screen.loading.LoadingScreen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Date: 13.28.2
 * Time: 13:49
 *
 * @author Kirstaps Kohs
 */
public class LoadingScreenTest extends GameManager {
    private AssetManager assetManager;

    @Override
    public AssetManager getMainAssetManager() {
        return assetManager;
    }

    @Override
    public void init() {
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        assetManager.load("data/loading/loadingTextureAtlas.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        LoadingScreen screen = new LoadingScreen(this);

        setLoadingScreen(screen);

        addScreen(new TestScreen(this));
        addScreen(new TestScreen2(this));
        showScreen(TestScreen.NAME);

    }


    public static void main(String[] args) {
        new LwjglApplication(new LoadingScreenTest(), "Loadin", 680, 480, true);
    }
}
