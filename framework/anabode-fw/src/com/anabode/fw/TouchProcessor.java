package com.anabode.fw;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * @author Kristaps Kohs
 */
public class TouchProcessor extends InputAdapter {
    private final Vector3 touchPoint = new Vector3();
    private final TouchCallback touchCallback = new TouchCallback();
    private Base base;
    private GameObject selected;

    public TouchProcessor(Base base) {
        this.base = base;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        base.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        base.getPhysicsWorld().QueryAABB(touchCallback, touchPoint.x - 0.0001f, touchPoint.y - 0.0001f, touchPoint.x + 0.0001f, touchPoint.y + 0.0001f);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (selected != null) {
            selected.onTouchUp();
            selected = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (selected != null) {
            selected.onTouchDragged();
            return true;
        }
        return false;
    }

    private final class TouchCallback implements QueryCallback {

        @Override
        public boolean reportFixture(Fixture fixture) {
            GameObject object = (GameObject) fixture.getBody().getUserData();
            if (object != null) {
                object.onTouchDown();
                selected = object;
                return true;
            }
            return false;
        }
    }
}
