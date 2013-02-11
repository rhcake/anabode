package com.proto.anabode.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.skinnyelephant.framework.core.Manager;

/**
 * Date: 13.9.2
 * Time: 19:50
 *
 * @author Kristaps Kohs
 */
public class PhysicsManager implements Manager {
    private World physicsWorld;

    @Override
    public void initialize() {
        physicsWorld = new World(new Vector2(0, -1), true);
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    @Override
    public void dispose() {

    }
}
