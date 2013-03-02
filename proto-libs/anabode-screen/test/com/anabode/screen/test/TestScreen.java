package com.anabode.screen.test;

import com.anabode.fw.Base;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.GameManager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 * Created with IntelliJ IDEA.
 * User: Kristaps
 * Date: 13.28.2
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class TestScreen extends AbstractScreen {
    public static final String NAME = "TestScreen";
    private Base base;
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
        testManager.load("data/test/test3.jpg", Texture.class);
        testManager.load("data/test/test4.jpg", Texture.class);
        testManager.load("data/test/test5.jpg", Texture.class);
        testManager.load("data/test/test6.jpg", Texture.class);
        testManager.load("data/test/test.jpg", Texture.class);
        testManager.load("data/ui/uiskin.json", Skin.class);
        base = new Base();
    }

    @Override
    public Base getBase() {
        return base;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AssetManager getAssetManager() {
        return testManager;
    }

    @Override
    public String getScreenName() {
        return NAME;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(float delta) {
        base.update();
        base.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        base.initialize();
        ButtonObject object = new ButtonObject(getAssetManager());
        object.addScript(new TestScript(gameManager));
        base.addObject(object);
    }

    @Override
    public void hide() {
        base.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Array<String> names = getAssetManager().getAssetNames();
        for (String name : names) {
            getAssetManager().unload(name);
        }
    }
}
