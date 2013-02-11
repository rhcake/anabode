package com.proto.anabode.util;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.proto.anabode.components.DimensionComponent;
import com.proto.anabode.components.PhysicsComponent;
import com.proto.anabode.managers.PhysicsManager;
import org.skinnyelephant.framework.core.Core;
import org.skinnyelephant.framework.core.Entity;

/**
 * Date: 13.11.2
 * Time: 20:01
 *
 * @author Kristaps Kohs
 */
public class EntityFactory {

    public static Entity createBorder(float x, float y, float width, float height, Core core) {
        Entity e = core.createEntity();
        PhysicsManager physicsManager = core.getManager(PhysicsManager.class);
        DimensionComponent dimensionComponent = new DimensionComponent();

        dimensionComponent.setDimensions(width, height);

        e.addComponent(dimensionComponent);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2;
        fixtureDef.shape = shape;

        Body body = physicsManager.getPhysicsWorld().createBody(bodyDef);

        body.createFixture(fixtureDef);
        shape.dispose();

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBody(body);

        e.addComponent(physicsComponent);

        return e;
    }


    public static Entity createBox(float x, float y, float width, float height, Core core) {
        Entity e = core.createEntity();
        PhysicsManager physicsManager = core.getManager(PhysicsManager.class);
        DimensionComponent dimensionComponent = new DimensionComponent();

        dimensionComponent.setDimensions(width, height);

        e.addComponent(dimensionComponent);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2;
        fixtureDef.shape = shape;

        Body body = physicsManager.getPhysicsWorld().createBody(bodyDef);

        body.createFixture(fixtureDef);
        shape.dispose();

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBody(body);

        e.addComponent(physicsComponent);

        return e;
    }
}
