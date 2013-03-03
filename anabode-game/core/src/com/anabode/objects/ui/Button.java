package com.anabode.objects.ui;

import com.anabode.fw.GameObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * @author Kristaps Kohs
 */
public class Button extends GameObject {
    public static final String BUTTON_KEY = "IMAGE_BUTTON";
    public static final String BUTTON__KEY_POSITION = "IMAGE_BUTTON_POSITION";
    public static final String BUTTON_KEY_DIMENSION = "IMAGE_BUTTON_DIMENSION";

    private ImageButton imageButton;
    private Sprite buttonUp;
    private Sprite buttonDown;
    private Vector2 position;
    private Vector2 dimension;


    public Button(Sprite buttonUp, Sprite buttonDown, Vector2 position, Vector2 dimension) {
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void create() {
        imageButton = new ImageButton(new SpriteDrawable(buttonUp), new SpriteDrawable(buttonDown));
        imageButton.setPosition(position.x, position.y);
        imageButton.setSize(dimension.x, dimension.y);
        addAttribute(BUTTON_KEY, imageButton);
        addAttribute(BUTTON_KEY_DIMENSION, dimension);
        addAttribute(BUTTON__KEY_POSITION, position);
    }
}
