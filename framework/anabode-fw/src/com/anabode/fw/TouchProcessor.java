package com.anabode.fw;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * @author Kristaps Kohs
 */
public class TouchProcessor extends InputAdapter implements QueryCallback {

    private final Vector3 touchPoint = new Vector3();
    private Base base;
    private GameObject selected;

    public TouchProcessor(Base base) {
        this.base = base;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        base.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        base.getPhysicsWorld().QueryAABB(this, touchPoint.x - 0.0001f, touchPoint.y - 0.0001f, touchPoint.x + 0.0001f, touchPoint.y + 0.0001f);
        return selected != null;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (selected != null) {
            base.setSelectionSource(selected);
            selected.onTouchUp();
            base.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
            base.getPhysicsWorld().QueryAABB(this, touchPoint.x - 0.0001f, touchPoint.y - 0.0001f, touchPoint.x + 0.0001f, touchPoint.y + 0.0001f);
            if(!base.getSelectionSource().equals(selected))  {
               base.setSelectionTarget(selected);
               selected.onTouchUp();
            }

            base.setSelectionTarget(null);
            base.setSelectionTarget(null);
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
