package com.anabode.fw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * @author Kristaps Kohs
 */
public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        GameObject gameObjectA = (GameObject) contact.getFixtureA().getBody().getUserData();
        GameObject gameObjectB = (GameObject) contact.getFixtureB().getBody().getUserData();

        if (gameObjectA != null && gameObjectB != null) {
            gameObjectA.onCollision(gameObjectB);
            gameObjectB.onCollision(gameObjectA);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
