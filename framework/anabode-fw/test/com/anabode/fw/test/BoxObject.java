package com.anabode.fw.test;

import box2dLight.ConeLight;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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
    private Vector2 dimension = new Vector2();
    private float angle;
    private final AssetManager assetManager;


    public BoxObject(Vector2 position, float width, float angle, AssetManager assetManager) {
        this.position = position;
        this.assetManager = assetManager;
        this.dimension.x = width;
        this.angle = angle;
        this.dimension.y = .5f;
    }

    @Override
    public void create() {
        addAttribute("position", position);
        Sprite sprite = new Sprite(assetManager.get("chain.png", Texture.class));
        addAttribute("texture", sprite);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.angle = angle;
        Body body = getPhysicsWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dimension.x, dimension.y);

        fixtureDef.density = 0.2f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();

        addAttribute("body", body);
        addAttribute("dimension", dimension);
        //PointLight light = new PointLight(getRayHandler(), 12, new Color(1,1,1,1), 1, 0, 0);
        ConeLight coneLight = new ConeLight(getRayHandler(), 100, new Color(1, 0, 0, 1), dimension.x * 10, position.x, position.y, body.getAngle() * MathUtils.degreesToRadians, 10);

        //  addAttribute("light", light);
        addAttribute("light", coneLight);
    }
}
