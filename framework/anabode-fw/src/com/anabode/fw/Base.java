package com.anabode.fw;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
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
     * Asset manager
     */
    private AssetManager assetManager;
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
        assetManager = new AssetManager();
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
        if (!assetLoadingFinished()) {
            if (debug) {
                Gdx.app.log("Loading assets, progress ", "" + getAssetProgress());
            }
            assetManager.update();
        }
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
        batch.begin();
        if (debug) {
            dDebugRenderer.render(physicsWorld, camera.combined);
        }
        for (Long layer : layerObjects.keySet()) {
            for (GameObject object : layerObjects.get(layer)) {
                if (!object.isUi()) {
                    object.render(batch);
                }
            }
        }
        batch.end();
        uiStage.draw();
        rayHandler.render();
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

    public final AssetManager getAssetManager() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return assetManager;
    }

    public final <T> void loadAsset(final String name, final Class<T> type) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        assetManager.load(name, type);
    }

    public final <T> T getAsset(final String name, final Class<T> type) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return assetManager.get(name, type);
    }

    public final <T> T getAsset(final String name) {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return assetManager.get(name);
    }

    public final boolean assetLoadingFinished() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return assetManager.getProgress() == 1;
    }

    public final float getAssetProgress() {
        if (!initialized) {
            throw new IllegalStateException("Base not initialized!");
        }
        return assetManager.getProgress();
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
        assetManager.dispose();
        dDebugRenderer.dispose();
        batch.dispose();
        physicsWorld.dispose();
        initialized = false;

    }
}
