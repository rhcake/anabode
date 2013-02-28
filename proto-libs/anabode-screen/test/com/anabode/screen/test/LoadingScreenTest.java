package com.anabode.screen.test;

import com.anabode.fw.Base;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.GameManager;
import com.anabode.screen.loading.LoadingScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        showScreen("TESTSCREEN");

    }

    private class TestScreen extends AbstractScreen {
        private AssetManager testManager;
        private SpriteBatch batch;
        private Sprite sprite;

        protected TestScreen(GameManager gameManager) {
            super(gameManager);
        }

        @Override
        protected void init() {
            batch = new SpriteBatch();
            testManager = new AssetManager();
            testManager.load("data/test/test2.jpg", Texture.class);
            testManager.load("data/test/test.jpg", Texture.class);
        }

        @Override
        public Base getBase() {
            return null;
        }

        @Override
        public AssetManager getAssetManager() {
            return testManager;
        }

        @Override
        public String getScreenName() {
            return "TESTSCREEN";
        }

        @Override
        public void render(float delta) {
            batch.begin();
            sprite.draw(batch);
            batch.end();
        }

        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {
            sprite = new Sprite(testManager.get("data/test/test.jpg", Texture.class));
            sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        @Override
        public void hide() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
        }
    }

    public static void main(String[] args) {
        new LwjglApplication(new LoadingScreenTest(), "Loadin", 680, 480, true);
    }
}
