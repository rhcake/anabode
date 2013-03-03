package com.anabode.fw;

import box2dLight.RayHandler;
import com.badlogic.gdx.InputProcessor;
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

    protected final void initialize() {
        initialized = true;
        for (ActionScript script : scripts) {
            script.initialize();
            if (script instanceof InputProcessor) {
                base.addInputProcessor((InputProcessor) script);
            }
        }

        for (Object attribute : attributes.values()) {
            if (attribute instanceof Body) {
                ((Body) attribute).setUserData(this);
            } else if (attribute instanceof Actor) {
                ((Actor) attribute).addListener(clickAction);
                ui = true;
                base.getUiStage().addActor((Actor) attribute);
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

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    protected void update() {
        for (ActionScript script : scripts) {
            if (script.isEnabled()) {
                script.onUpdate();
                if (script.isReadyToProcess()) {
                    script.onPeriodicUpdate();
                }
            }
        }
    }

    protected void onTouchDown() {
        for (ActionScript script : scripts) {
            script.onTouchDown();
        }
    }

    protected void onTouchUp() {
        for (ActionScript script : scripts) {
            script.onTouchUp();
        }
    }

    protected void onTouchDragged() {
        for (ActionScript script : scripts) {
            script.onTouchDragged();
        }
    }

    protected void render(SpriteBatch batch) {
        for (ActionScript script : scripts) {
            script.onRender(batch);
        }
    }

    protected void onCollision(final GameObject object) {
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

    protected Camera getCamera() {
        return base.getCamera();
    }

    protected World getPhysicsWorld() {
        return base.getPhysicsWorld();
    }

    protected GameObject getObject(final String name) {
        return base.getObject(name);
    }

    protected Base getBase() {
        return base;
    }

    protected final RayHandler getRayHandler() {
        return base.getRayHandler();
    }

    protected final GameObject getSelectionSource() {
        GameObject object = base.getSelectionSource();
        if (!this.equals(object)) {
            return object;
        } else {
            return null;
        }
    }

    private final class ClickAction extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            for (ActionScript script : scripts) {
                script.onGuiTouch();
            }
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.enter(event, x, y, pointer, fromActor);
            for (ActionScript script : scripts) {
                script.onGuiEnter();
            }
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            super.exit(event, x, y, pointer, toActor);
            for (ActionScript script : scripts) {
                script.onGuiExit();
            }
        }
    }


}
