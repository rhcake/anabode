package com.proto.anabode.components;

import com.badlogic.gdx.math.Vector2;
import org.skinnyelephant.framework.annotations.Component;

/**
 * Date: 13.9.2
 * Time: 19:51
 *
 * @author Kristaps Kohs
 */
@Component
public class DimensionComponent {
    private float width;
    private float height;

    public void setDimensions(float width, float height) {
        setHeight(height);
        setWidth(width);
    }

    public void setDimensions(Vector2 dimensions) {
        setDimensions(dimensions.x, dimensions.y);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
