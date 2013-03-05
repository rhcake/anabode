package com.anabode.objects.environment;

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
 * Date: 13.5.3
 * Time: 09:58
 *
 * @author Kirstaps Kohs
 */
public class ShaftWall extends GameObject {
    public static final float SHAFT_WALL_DENSITY = 50f;
    public static final float SHAFT_WALL_FRICTION = 0.9f;

    public static final float SHAFT_WALL_THICKNESS_PERCENT = 20;
    public static final float SHAFT_WALL_HEIGHT_PERCENT = 90;

    public static final float SHAFT_FLOOR_THICKNESS_PERCENT = 100 - SHAFT_WALL_THICKNESS_PERCENT * 2;
    public static final float SHAFT_FLOOR_HEIGHT_PERCENT = 2;

    private Vector2 position;
    private Vector2 dimension;
    private Vector2 origin;
    private Body body;
    private Sprite sprite;


    public ShaftWall(Vector2 position, Vector2 dimension, Sprite sprite) {
        this.position = position;
        this.dimension = dimension;
        this.sprite = sprite;
        this.origin = new Vector2(dimension.x * .5f, dimension.y * .5f);

        setLayer(ENVIRONMENT_OBJECT_RENDER_LAYER);
    }

    @Override
    public void create() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = CONSTRUCTION_OBJECT_COLLISION_CATEGORY;
        fixtureDef.filter.maskBits = CONSTRUCTION_OBJECT_COLLISION_MASK;

        fixtureDef.density = SHAFT_WALL_DENSITY;
        fixtureDef.friction = SHAFT_WALL_FRICTION;

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
        super.update();
    }

    @Override
    protected void render(SpriteBatch batch) {
        Vector2 tmp = toWindowCords(position.x, position.y);
        sprite.setSize(dimension.x, dimension.y);
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.setOrigin(origin.x, origin.y);
        sprite.setPosition(tmp.x - origin.x, tmp.y - origin.y);
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
        origin = null;

        removeAttribute(MATERIAL_BODY_KEY);
        removeAttribute(MATERIAL_DIMENSION_KEY);
        removeAttribute(MATERIAL_POS_KEY);
        removeAttribute(MATERIAL_SPRITE_KEY);
        removeAttribute(MATERIAL_SPRITE_ORIGIN_KEY);
    }

}
