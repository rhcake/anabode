package com.anabode.fw.test;

import com.anabode.fw.ActionScript;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Date: 13.19.2
 * Time: 10:45
 *
 * @author Kirstaps Kohs
 */
public class AddBoxScript extends ActionScript implements InputProcessor {
    private final Vector2 startPos = new Vector2();
    private final Vector2 tempVector = new Vector2();
    private final AssetManager assetManager;

    public AddBoxScript(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startPos.set(toScreenCords(screenX, screenY));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (getSelectionSource() == null) {
            tempVector.set(toScreenCords(screenX, screenY)).sub(startPos);
            float angle = tempVector.angle();
            float width = startPos.dst(toScreenCords(screenX, screenY)) * .5f;
            if (width < 0.01f) return false;
            BoxObject boxObject = new BoxObject(startPos.cpy(), startPos.dst(toScreenCords(screenX, screenY)) * .5f, angle * MathUtils.degreesToRadians, assetManager);
            boxObject.addScript(new AttachmentScript());
            boxObject.addScript(new LightingScript());
            boxObject.addScript(new RenderScript());
            getBase().addObject(boxObject);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
