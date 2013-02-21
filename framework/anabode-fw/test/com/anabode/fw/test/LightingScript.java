package com.anabode.fw.test;

import box2dLight.Light;
import com.anabode.fw.ActionScript;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Date: 13.21.2
 * Time: 21:01
 *
 * @author Kristaps Kohs
 */
public class LightingScript extends ActionScript {
    private Light light;
    private Vector2 position;
    private Body body;

    @Override
    protected void initialize() {
        light = get("light");
        position = get("position");
        body = get("body");
    }


    @Override
    protected void onUpdate() {
        light.setDirection(body.getAngle() * MathUtils.radiansToDegrees);
        light.setPosition(position);
    }
}
