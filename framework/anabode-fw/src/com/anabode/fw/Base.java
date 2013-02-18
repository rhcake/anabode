package com.anabode.fw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.*;

/**
 * @author Kristaps Kohs
 */
public class Base {
    /**
     * Gravity vector.
     */
    private final Vector2 gravity = new Vector2();
    /**
     * Input multiplexer for managing input processors.
     */
    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();
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

    private Box2DDebugRenderer dDebugRenderer;

    private boolean debug;

    /**
     * Method for initializing framework.
     * Initializes physics world, UI stage and input multiplexer.
     */
    public void initialize() {
        uiStage = new Stage();
        physicsWorld = new World(gravity, true);
        camera = new OrthographicCamera();
        physicsWorld.setContactListener(new CollisionListener());
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(new TouchProcessor(this));
        dDebugRenderer = new Box2DDebugRenderer();
    }

    /**
     * Update method to update all objects.
     */
    public void update() {
        physicsWorld.step(1.0f/60.0f,10,10);
        for (GameObject gameObject : objects) {
            gameObject.update();
        }
    }

    /**
     * Render method to render objects.
     */
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        camera.update();
        if(debug) {
            dDebugRenderer.render(physicsWorld,camera.combined);
        }
        for (Long layer : layerObjects.keySet()) {
            for (GameObject object : layerObjects.get(layer)) {
                if (!object.isUi()) {
                    object.render();
                }
            }
        }
        uiStage.draw();
    }

    /**
     * Adds object to framework and initializes it.
     *
     * @param gameObject object to add.
     */
    public void addObject(GameObject gameObject) {
        gameObject.setBase(this);
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

    public GameObject getObject(String name) {
        return referencedObjects.get(name);
    }

    public void destroyObject(GameObject object) {
        if (object.getName() != null) {
            referencedObjects.remove(object.getName());
        }

        if (layerObjects.containsKey(object.getLayer())) {
            layerObjects.get(object.getLayer()).remove(object);
        }

        objects.remove(object);
        object.dispose();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setGravity(float x, float y) {
        gravity.set(x, y);
        physicsWorld.setGravity(gravity);
    }

    public void setGravity(final Vector2 gravity) {
        setGravity(gravity.x,gravity.y);
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    public void setViewPort(float viewPortWidth, float viewPortHeight) {
        camera.viewportHeight = viewPortHeight;
        camera.viewportWidth = viewPortWidth;
    }

    public void toggleDebugRenderer(){
       debug ^= true;
    }
}
