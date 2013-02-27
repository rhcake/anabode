package com.anabode.fw;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import java.util.*;

/**
 * @author Kristaps Kohs
 */
//TODO Creative name of this class required!!!
public final class Base implements Disposable {

    private static final float UPDATE_STEP = 1.0f / 60.0f;
    private static final int VELOCITY_ITERATION_STEP = 8;
    private static final int POSITION_ITERATION_STEP = 3;
    /**
     * Gravity vector.
     */
    private final Vector2 gravity = new Vector2();
    /**
     * Input multiplexer for managing input processors.
     */
    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();
    /**
     * FPS logger for debugging FPS.
     */
    private final FPSLogger fpsLogger = new FPSLogger();
    /**
     * Flag indicating if Base hase been initialized.
     */
    protected boolean initialized;
    /**
     * Map containing list of objects sorted by rendering layers.
     */
    private SortedMap<Long, LinkedList<GameObject>> layerObjects = new TreeMap<Long, LinkedList<GameObject>>();
    /**
     * Map with Objects that have name set.
     */
    private Map<String, GameObject> referencedObjects = new HashMap<String, GameObject>();
    /**
     * List of all objects.
     */
    private LinkedList<GameObject> objects = new LinkedList<GameObject>();
    /**
     * Physics world.
     */
    private World physicsWorld;
    /**
     * Game camera.
     */
    private Camera camera;
    /**
     * Stage container for UI.
     */
    private Stage uiStage;
    /**
     * Sprite batch for rendering textures.
     */
    private SpriteBatch batch;
    /**
     * Debug renderer.
     */
    private Box2DDebugRenderer dDebugRenderer;
    /**
     * Flag indicating if debug is enabled.
     */
    private boolean debug;
    // TODO Figure out what to do with these two!?!?
    private GameObject selectionSource;
    private GameObject selectionTarget;
    /**
     * Handler for processing and rendering lights.
     */
    private RayHandler rayHandler;

    /**
     * Method for initializing framework.
     * Initializes physics world, UI stage and input multiplexer.
     */
    public void initialize() {
        if (initialized) {
            throw new IllegalStateException("Base already initialized");
        }
        uiStage = new Stage();
        physicsWorld = new World(gravity, true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        physicsWorld.setContactListener(new CollisionListener());
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(new TouchProcessor(this));
        dDebugRenderer = new Box2DDebugRenderer();
        rayHandler = new RayHandler(physicsWorld);
        initialized = true;
    }

    /**
     * Update method to update all objects.
     */
    public void update() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        if (debug) {
            fpsLogger.log();
        }
        rayHandler.setCombinedMatrix(camera.combined);
        physicsWorld.step(UPDATE_STEP, VELOCITY_ITERATION_STEP, POSITION_ITERATION_STEP);
        for (GameObject gameObject : objects) {
            gameObject.update();
        }
        uiStage.act();
        rayHandler.update();
    }

    /**
     * Render method to render objects.
     */
    public void render() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        camera.update();
        if (debug) {
            dDebugRenderer.render(physicsWorld, camera.combined);
        }
        batch.begin();
        for (Long layer : layerObjects.keySet()) {
            for (GameObject object : layerObjects.get(layer)) {
                if (!object.isUi()) {
                    object.render(batch);
                }
            }
        }
        batch.end();
        rayHandler.render();
        uiStage.draw();
    }

    /**
     * Adds object to framework and initializes it.
     *
     * @param gameObject object to add.
     */
    public void addObject(GameObject gameObject) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        gameObject.setBase(this);
        gameObject.create();
        gameObject.initialize();
        if (gameObject.getName() != null) {
            referencedObjects.put(gameObject.getName(), gameObject);
        }

        if (layerObjects.get(gameObject.getLayer()) != null) {
            layerObjects.get(gameObject.getLayer()).add(gameObject);
        } else {
            layerObjects.put(gameObject.getLayer(), new LinkedList<GameObject>());
            layerObjects.get(gameObject.getLayer()).add(gameObject);
        }
        objects.add(gameObject);
    }

    protected void addInputProcessor(InputProcessor processor) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        inputMultiplexer.addProcessor(processor);
    }

    public GameObject getObject(String name) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return referencedObjects.get(name);
    }

    public void destroyObject(GameObject object) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        if (object.getName() != null) {
            referencedObjects.remove(object.getName());
        }

        if (layerObjects.containsKey(object.getLayer())) {
            layerObjects.get(object.getLayer()).remove(object);
        }

        objects.remove(object);
        object.dispose();
    }

    public void toggleDebugRenderer() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        debug = !debug;
    }

    public Camera getCamera() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return camera;
    }

    public void setGravity(float x, float y) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        gravity.set(x, y);
        physicsWorld.setGravity(gravity);
    }

    public void setGravity(final Vector2 gravity) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        setGravity(gravity.x, gravity.y);
    }

    public Stage getUiStage() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return uiStage;
    }

    public World getPhysicsWorld() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return physicsWorld;
    }

    public void setViewPort(float viewPortWidth, float viewPortHeight) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        camera.viewportHeight = viewPortHeight;
        camera.viewportWidth = viewPortWidth;
    }

    public void setStageViewPort(float viewPortWidth, float viewPortHeight) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        uiStage.setViewport(viewPortWidth, viewPortHeight, true);
    }

    public GameObject getSelectionTarget() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return selectionTarget;
    }

    public void setSelectionTarget(GameObject selectionTarget) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        this.selectionTarget = selectionTarget;
    }

    public GameObject getSelectionSource() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return selectionSource;
    }

    public void setSelectionSource(GameObject selectionSource) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        this.selectionSource = selectionSource;
    }

    public RayHandler getRayHandler() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return rayHandler;
    }

    public void setAmbientLightColor(Color color) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        rayHandler.setAmbientLight(color);
    }

    public void setAmbientLight(float intensity) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        rayHandler.setAmbientLight(intensity);
    }

    public void setShadows(boolean shadows) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        rayHandler.setShadows(shadows);
    }

    public void setCulling(boolean culling) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        rayHandler.setCulling(culling);
    }

    public void setBlur(boolean blur) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        rayHandler.setBlur(blur);
    }

    @Override
    public void dispose() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        for (GameObject object : objects) {
            object.dispose();
        }

        referencedObjects.clear();
        objects.clear();
        layerObjects.clear();

        rayHandler.dispose();
        uiStage.dispose();
        dDebugRenderer.dispose();
        batch.dispose();
        physicsWorld.dispose();
        initialized = false;

    }
}
