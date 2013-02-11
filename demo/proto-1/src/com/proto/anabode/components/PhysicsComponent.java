package com.proto.anabode.components;

import com.badlogic.gdx.physics.box2d.Body;
import org.skinnyelephant.framework.annotations.Component;

/**
 * Date: 13.9.2
 * Time: 19:59
 *
 * @author Kristaps Kohs
 */
@Component
public class PhysicsComponent {

    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
