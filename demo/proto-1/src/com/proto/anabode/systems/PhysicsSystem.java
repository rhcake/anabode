package com.proto.anabode.systems;

import com.badlogic.gdx.physics.box2d.World;
import com.proto.anabode.components.PhysicsComponent;
import com.proto.anabode.managers.PhysicsManager;
import org.skinnyelephant.framework.core.Entity;
import org.skinnyelephant.framework.systems.EntitySystem;

/**
 * Date: 13.11.2
 * Time: 21:01
 *
 * @author Kristaps Kohs
 */
public class PhysicsSystem extends EntitySystem {
    private World world;


    @Override
    public void initialize() {
        PhysicsManager manager = getManager(PhysicsManager.class);
        world = manager.getPhysicsWorld();

        addUsedComponent(PhysicsComponent.class);
    }

    @Override
    public void processEntity(Entity entity) {

    }

    @Override
    public void processSystem() {
        world.step(1.0f / 60.0f, 10, 10);
    }

    @Override
    public void dispose() {

    }
}
