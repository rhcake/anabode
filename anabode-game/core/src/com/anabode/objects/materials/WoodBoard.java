package com.anabode.objects.materials;

import com.anabode.fw.GameObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.anabode.util.Constants.*;

/**
 * @author Kristaps Kohs
 */
public class WoodBoard extends GameObject {

    public static final float WOOD_BOARD_DENSITY = 1f;
    public static final float WOOD_BOARD_FRICTION = 0.5f;


    private Sprite sprite;
    private Vector2 position;
    private Vector2 dimension;
    private Vector2 origin;
    //DO not use this angle use angle from body instead.
    private float angle;
    private Body body;

    public WoodBoard(Sprite sprite, Vector2 position, Vector2 dimension, Vector2 origin, Float angle) {
        this.sprite = sprite;
        this.position = position;
        this.dimension = dimension;
        this.origin = origin;
        this.angle = angle;
        setLayer(CONSTRUCTION_OBJECT_RENDER_LAYER);
    }

    @Override
    public void create() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.angle = angle;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = CONSTRUCTION_OBJECT_COLLISION_CATEGORY;
        fixtureDef.filter.maskBits = CONSTRUCTION_OBJECT_COLLISION_MASK;

        fixtureDef.density = WOOD_BOARD_DENSITY;
        fixtureDef.friction = WOOD_BOARD_FRICTION;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dimension.x * .5f, dimension.y * .5f);
        fixtureDef.shape = shape;

        body = getPhysicsWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        shape.dispose();

        addAttribute(MATERIAL_BODY_KEY, body);
        addAttribute(MATERIAL_DIMENSION_KEY, dimension);
        addAttribute(MATERIAL_POS_KEY, position);
        addAttribute(MATERIAL_SPRITE_KEY, sprite);
        addAttribute(MATERIAL_SPRITE_ORIGIN_KEY, origin);
    }

    @Override
    protected void update() {
        position.set(body.getPosition());


        Vector2 tmp = toWindowCords(position.x, position.y);

        sprite.setOrigin(dimension.x * .5f, dimension.y * .5f);
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.setSize(dimension.x, dimension.y);
        sprite.setPosition(tmp.x - dimension.x * .5f, tmp.y - dimension.y * .5f);
        body.setTransform(position.x, position.y, body.getAngle() + 0.01f);
        super.update();
    }

    @Override
    protected void render(SpriteBatch batch) {
        sprite.draw(batch);

        super.render(batch);
    }

    @Override
    public void dispose() {
        super.dispose();

        sprite = null;
        getPhysicsWorld().destroyBody(body);
        body = null;
        position = null;
        dimension = null;

        removeAttribute(MATERIAL_BODY_KEY);
        removeAttribute(MATERIAL_DIMENSION_KEY);
        removeAttribute(MATERIAL_POS_KEY);
        removeAttribute(MATERIAL_SPRITE_KEY);
        removeAttribute(MATERIAL_SPRITE_ORIGIN_KEY);
    }
}
