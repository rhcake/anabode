package com.anabode.screens;

import com.anabode.controller.SceneController;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.util.Constants;
import com.anabode.util.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Modris Vekmanis
 */
public class WelcomeScreen extends AbstractScreen {
    private WelcomeScreenInputHandler inputHandler;
    private SpriteBatch batch;
    private Texture welcomeScreen;

    public WelcomeScreen() throws Exception {
        this.setId(Constants.WELCOME_SCREEN);
        inputHandler = new WelcomeScreenInputHandler();

    }

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        batch = new SpriteBatch();
        assetManager.load("WelcomeScreen.png", Texture.class);
        assetManager.finishLoading();
        welcomeScreen = assetManager.get("WelcomeScreen.png", Texture.class);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(welcomeScreen, 0, 0);
        batch.end();
    }

    @Override
    public void handleInput() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void dispose(MultiScreenAssetManager assetManager) {
        assetManager.unload("WelcomeScreen.png");
    }

    private class WelcomeScreenInputHandler extends InputHandler {
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
