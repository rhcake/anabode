package com.anabode.screens;

import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * @author Modris Vekmanis
 */
public class Menu extends AbstractScreen {
    private SpriteBatch batch;
    private Sprite background;

    public Menu() throws Exception {
        this.setId(Constants.MENU_SCREEN);
    }

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        batch = new SpriteBatch();
        assetManager.load("textures/backgrounds/backgrounds.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        //background = assetManager.get("textures/backgrounds/backgrounds.atlas", TextureAtlas.class).createSprite("MenuScreen");
        background = assetManager.getSprite("textures/backgrounds/backgrounds.atlas", "MenuScreen");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose(MultiScreenAssetManager assetManager) {
        assetManager.unload("MenuScreen.png");
    }
}
