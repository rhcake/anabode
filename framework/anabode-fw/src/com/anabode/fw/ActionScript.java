package com.anabode.fw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Kristaps Kohs
 */
public abstract class ActionScript implements Disposable {

    private GameObject parent;
    private boolean enabled = true;
    private long period = 0;
    private long accumulatedDelta;


    public abstract void initialize();

    /**
     * Method called every frame when object is being rendered.
     */
    public void onRender(SpriteBatch batch) {
    }

    /**
     * Update method is called every frame if script is enabled.
     */
    public void onUpdate() {
    }

    /**
     * Called if objects collision box is touched.
     */
    public void onTouchDown() {
    }

    /**
     * Called when object is released
     */
    public void onTouchUp() {
    }

    /**
     * Called if object is selected and cursor is being dragged.
     */
    public void onTouchDragged() {
    }

    /**
     * Called if period is set > 0 and  passed time since last update is greater than period.
     */
    public void onPeriodicUpdate() {
    }

    /**
     * Called when object physical body collides with something.
     *
     * @param from Object that body collided with.
     */
    public void onCollision(final GameObject from) {
    }

    /**
     * Called if object is marked as UI and is being clicked on.
     */
    public void onGuiTouch() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void dispose() {
        parent = null;
    }

    protected void setEnabled(boolean enabled) {
        this.enabled = true;
    }

    protected boolean isReadyToProcess() {
        if (period > 0) {
            accumulatedDelta += Gdx.graphics.getDeltaTime();
            if (accumulatedDelta > period) {
                accumulatedDelta = 0;
                return true;
            }
        }
        return false;
    }

    protected void setPeriod(long periodInMS) {
        period = periodInMS;
    }

    protected void setParent(GameObject parent) {
        this.parent = parent;
    }

    protected final Vector2 getPointer() {
        Vector3 tmp = Vector3.tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        parent.getCamera().unproject(tmp);
        return Vector2.tmp.set(tmp.x, tmp.y);
    }

    protected final GameObject getSelectionSource(){
        return parent.getSelectionSource();
    }

    protected final void createJoint(JointDef jointDef) {
        parent.getPhysicsWorld().createJoint(jointDef);
    }

    @SuppressWarnings("unchecked")
    protected  <T> T get(String name) {
        return (T) parent.get(name);
    }
}
