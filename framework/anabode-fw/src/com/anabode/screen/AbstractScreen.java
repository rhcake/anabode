package com.anabode.screen;

import com.anabode.fw.Base;
import com.badlogic.gdx.Screen;

/**
 * @author Modris Vekmanis
 */
public abstract class AbstractScreen implements Screen {
    protected Base base = null;
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

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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
    @Deprecated
    public void dispose() {
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
}
