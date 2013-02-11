package com.proto.anabode.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.proto.anabode.managers.CameraManager;
import com.proto.anabode.managers.PhysicsManager;
import org.skinnyelephant.framework.core.Entity;
import org.skinnyelephant.framework.systems.EntitySystem;

/**
 * Date: 13.9.2
 * Time: 19:49
 *
 * @author Kristaps Kohs
 */
public class RenderingSystem extends EntitySystem {
    private Box2DDebugRenderer debugRenderer;
    private Camera camera;
    private World world;

    @Override
    public void initialize() {
        CameraManager cameraManager = getManager(CameraManager.class);
        PhysicsManager manager = getManager(PhysicsManager.class);
        world = manager.getPhysicsWorld();
        camera = cameraManager.getCamera();
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void processEntity(Entity entity) {

    }


    @Override
    public void processSystem() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        camera.update();
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {

    }
}
