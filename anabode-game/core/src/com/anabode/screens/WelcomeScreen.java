package com.anabode.screens;

import com.anabode.controller.SceneController;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.screens.menu.Menu;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
        assetManager.load("textures/welcome/welcome.atlas", TextureAtlas.class);
        assetManager.load(Menu.TEXTURE_ATLAS_NAME, TextureAtlas.class);
        assetManager.finishLoading();
        //background = assetManager.get("textures/ui/ui.atlas", TextureAtlas.class).createSprite("WelcomeScreen");
        background = assetManager.getSprite("textures/welcome/welcome.atlas", "WelcomeScreen");
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
