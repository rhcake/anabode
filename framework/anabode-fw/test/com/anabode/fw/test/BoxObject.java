package com.anabode.fw.test;

import box2dLight.ConeLight;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created with IntelliJ IDEA.
 * User: kristapsk
 * Date: 13.19.2
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class BoxObject extends GameObject {
    private Vector2 position;
    private float width;
    private float angle;

    public BoxObject(Vector2 position, float width, float angle) {
        this.position = position;
        this.width = width;
        this.angle = angle;
    }

    @Override
    public void create() {
        addAttribute("position", position);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.angle = angle;
        Body body = getPhysicsWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, 0.1f);

        fixtureDef.density = 0.2f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();

        addAttribute("body", body);

        //PointLight light = new PointLight(getRayHandler(), 12, new Color(1,1,1,1), 1, 0, 0);
        ConeLight coneLight = new ConeLight(getRayHandler(), 11, new Color(1, 0, 0, 1), 10, position.x, position.y, angle, angle);
        //  addAttribute("light", light);
        addAttribute("coneLight", coneLight);
    }
}
