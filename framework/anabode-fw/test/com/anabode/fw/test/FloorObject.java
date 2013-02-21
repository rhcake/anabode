package com.anabode.fw.test;

import com.anabode.fw.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created with IntelliJ IDEA.
 * User: kristapsk
 * Date: 13.19.2
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class FloorObject extends GameObject {
    private Vector2 position;
    private float width;
    private float height;

    public FloorObject(Vector2 position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    @Override
    public void create() {

        addAttribute("position", position);

        addAttribute("width", width);
        addAttribute("height", height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = getPhysicsWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();

        addAttribute("body", body);
        //  addAttribute("light", light);
        //    }
    }
}
