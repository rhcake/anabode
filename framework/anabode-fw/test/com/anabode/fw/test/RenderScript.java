package com.anabode.fw.test;

import com.anabode.fw.ActionScript;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Date: 13.22.2
 * Time: 21:09
 *
 * @author Kristaps Kohs
 */
public class RenderScript extends ActionScript {
    private Sprite sprite;
    private Vector2 position;
    private Body body;
    private Vector2 dimension;

    @Override
    protected void initialize() {
        sprite = get("texture");
        position = get("position");
        body = get("body");
        dimension = get("dimension");
    }

    @Override
    protected void onUpdate() {
        Vector2 tmp = toWindowCords(position.x, position.y);

        sprite.setSize(dimension.x * 2, dimension.y * 2);
        sprite.setOrigin(dimension.x / 2 * 2, dimension.y / 2 * 2);
        sprite.setScale(100);
        sprite.setPosition(tmp.x, tmp.y);
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);


    }

    @Override
    protected void onRender(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
