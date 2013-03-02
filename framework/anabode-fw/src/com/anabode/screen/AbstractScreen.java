package com.anabode.screen;

import com.anabode.fw.Base;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Modris Vekmanis
 */
public abstract class AbstractScreen {
    protected Base base = null;
    protected GameController controller;
    private String id = null;
    private boolean visible = true;
    private int renderOrder = 0;

    public void create(MultiScreenAssetManager assetManager) {
    }

    public void handleInput() {
        if (base != null) {
            base.processInput();
        }
    }

    public void update() {
    }


    public void render(float delta) {
    }


    public void resize(int width, int height) {
    }


    public void show() {
    }


    public void hide() {
    }


    public void pause() {
    }


    public void resume() {
    }

    public void dispose(MultiScreenAssetManager assetManager) {
    }

    public int getRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new Exception("Screen already has an id: " + id);
        }
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
