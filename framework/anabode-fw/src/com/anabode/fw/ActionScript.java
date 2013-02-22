package com.anabode.fw;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Kristaps Kohs
 */
public abstract class ActionScript implements Disposable {

    private final Vector2 vector2 = new Vector2();
    private final Vector3 vector3 = new Vector3();

    private GameObject parent;
    private boolean enabled = true;
    private long period = 0;
    private long accumulatedDelta;

    protected abstract void initialize();

    /**
     * Method called every frame when object is being rendered.
     */
    protected void onRender(SpriteBatch batch) {
    }

    /**
     * Update method is called every frame if script is enabled.
     */
    protected void onUpdate() {
    }

    /**
     * Called if objects collision box is touched.
     */
    protected void onTouchDown() {
    }

    /**
     * Called when object is released
     */
    protected void onTouchUp() {
    }

    /**
     * Called if object is selected and cursor is being dragged.
     */
    protected void onTouchDragged() {
    }

    /**
     * Called if period is set > 0 and  passed time since last update is greater than period.
     */
    protected void onPeriodicUpdate() {
    }

    /**
     * Called when object physical body collides with something.
     *
     * @param from Object that body collided with.
     */
    protected void onCollision(final GameObject from) {
    }

    /**
     * Called if object is marked as UI and is being clicked on.
     */
    protected void onGuiTouch() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected void setEnabled(boolean enabled) {
        this.enabled = true;
    }

    public void dispose() {
        parent = null;
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
        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        parent.getCamera().unproject(vector3);
        return vector2.set(vector3.x, vector3.y);
    }

    protected final GameObject getSelectionSource() {
        return parent.getSelectionSource();
    }

    protected final Joint createJoint(JointDef jointDef) {
        return parent.getPhysicsWorld().createJoint(jointDef);
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String name) {
        return (T) parent.get(name);
    }

    protected void addAttribute(String name, Object data) {
        parent.addAttribute(name, data);
    }

    /**
     * Translates given vector to screen coordinates. Note returned vector is only usable to reading values DO NOT use it for computation.
     *
     * @param pos coordinates to translate.
     * @return translated coordinates.
     */
    protected final Vector2 toScreenCords(Vector2 pos) {
        return toScreenCords(pos.x, pos.y);
    }

    /**
     * Translates given coordinates to screen coordinates. Note returned vector is only usable to reading values DO NOT use it for computation.
     *
     * @param x x coordinate to translate.
     * @param y y coordinate to translate.
     * @return translated coordinates.
     */
    protected final Vector2 toScreenCords(float x, float y) {
        vector3.set(x, y, 0);
        parent.getCamera().unproject(vector3);
        return vector2.set(vector3.x, vector3.y);
    }

    protected final Vector2 toWindowCords(float x, float y) {
        vector3.set(x, y, 0);
        parent.getCamera().project(vector3);
        return vector2.set(vector3.x, vector3.y);
    }

    protected Base getBase() {
        return parent.getBase();
    }

    protected final <T> T getAsset(final String name, final Class<T> type) {
        return parent.getAsset(name, type);
    }

    protected final <T> T getAsset(final String name) {
        return parent.getAsset(name);
    }

    protected final RayHandler getRayHandler() {
        return parent.getRayHandler();
    }
}
