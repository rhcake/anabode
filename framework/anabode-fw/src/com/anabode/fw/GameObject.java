package com.anabode.fw;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Kristaps Kohs
 */
public abstract class GameObject implements Disposable {

    private final Map<String, Object> attributes = new HashMap<String, Object>();
    private final List<ActionScript> scripts = new LinkedList<ActionScript>();
    private final ClickAction clickAction = new ClickAction();
    private Base base;
    private String name;
    private boolean ui = false;
    private boolean initialized = false;
    private long layer = 0;

    public final void initialize() {
        initialized = true;
        for (ActionScript script : scripts) {
            script.initialize();
        }

        for (Object attribute : attributes.values()) {
            if (attribute instanceof Body) {
                ((Body) attribute).setUserData(this);
            }
        }
    }

    /**
     * Method for setting up object, create attributes set values here etc.
     */
    public abstract void create();

    public final Object get(final String name) {
        return attributes.get(name);
    }

    public final void addAttribute(final String name, final Object data) {
        if (data instanceof Actor) {
            ((Actor) data).addListener(clickAction);
            ui = true;
            base.getUiStage().addActor((Actor) data);
        } else if (data instanceof Body) {
            ((Body) data).setUserData(this);
        }
        attributes.put(name, data);
    }

    public final void addScript(final ActionScript script) {
        if (initialized) {
            script.initialize();
        }
        script.setParent(this);
        scripts.add(script);
    }

    public void removeScript(final ActionScript script) {
        scripts.remove(script);
        script.dispose();
    }

    public void update() {
        for (ActionScript script : scripts) {
            if (script.isEnabled()) {
                script.onUpdate();
                if (script.isReadyToProcess()) {
                    script.onPeriodicUpdate();
                }
            }
        }
    }

    public void onTouchDown() {
        for (ActionScript script : scripts) {
            script.onTouchDown();
        }
    }

    public void onTouchUp() {
        for (ActionScript script : scripts) {
            script.onTouchUp();
        }
    }

    public void onTouchDragged() {
        for (ActionScript script : scripts) {
            script.onTouchDragged();
        }
    }

    public void render(SpriteBatch batch) {
        for (ActionScript script : scripts) {
            script.onRender(batch);
        }
    }

    public void onCollision(final GameObject object) {
        for (ActionScript script : scripts) {
            script.onCollision(object);
        }
    }

    public void dispose() {
        for (ActionScript script : scripts) {
            script.dispose();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    protected final void setBase(final Base base) {
        this.base = base;
    }

    public boolean isUi() {
        return ui;
    }

    public void setUi(boolean ui) {
        this.ui = ui;
    }

    public long getLayer() {
        return layer;
    }

    public void setLayer(long layer) {
        this.layer = layer;
    }

    public Camera getCamera() {
        return base.getCamera();
    }

    public World getPhysicsWorld() {
        return base.getPhysicsWorld();
    }

    private final class ClickAction extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            for (ActionScript script : scripts) {
                script.onGuiTouch();
            }
        }
    }
}