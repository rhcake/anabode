package com.anabode.objects.environment;

import box2dLight.PointLight;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Date: 13.5.3
 * Time: 10:53
 *
 * @author Kirstaps Kohs
 */
public class Sun extends GameObject {
    public static final String SUN_LIGHT_KEY = "directionalLight";
    public static final int SUN_RAY_COUNT = 5000;
    public static final Color SUN_COLOR = new Color(1, 1, .6f, 1);
    private PointLight directionalLight;
    private Vector2 position;

    @Override
    public void create() {
        directionalLight = new PointLight(getRayHandler(), SUN_RAY_COUNT, SUN_COLOR, Gdx.graphics.getWidth() * 4, -250, 500);
        addAttribute(SUN_LIGHT_KEY, directionalLight);
    }
}
