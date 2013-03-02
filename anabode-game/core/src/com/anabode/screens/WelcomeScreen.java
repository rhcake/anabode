package com.anabode.screens;

import com.anabode.controller.SceneController;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Modris Vekmanis
 */
public class WelcomeScreen extends AbstractScreen {
    private WelcomeScreenInputHandler inputHandler;
    private SpriteBatch batch;
    private Sprite background;

    public WelcomeScreen() throws Exception {
        this.setId(Constants.WELCOME_SCREEN);
        inputHandler = new WelcomeScreenInputHandler();

    }

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        batch = new SpriteBatch();
        assetManager.load("WelcomeScreen.png", Texture.class);
        assetManager.finishLoading();
        background = new Sprite(assetManager.get("WelcomeScreen.png", Texture.class));
    }

    @Override
    public void render(float delta) {
        batch.begin();
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.draw(batch);
        batch.end();
    }

    @Override
    public void handleInput() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void dispose(MultiScreenAssetManager assetManager) {
        assetManager.unload("WelcomeScreen.png");
        Gdx.input.setInputProcessor(null);
    }

    private class WelcomeScreenInputHandler extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {
            ((SceneController) controller).continueOnWelcomeScreen();
            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            ((SceneController) controller).continueOnWelcomeScreen();
            return true;
        }
    }
}
