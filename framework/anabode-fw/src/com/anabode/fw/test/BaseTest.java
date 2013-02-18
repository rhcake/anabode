package com.anabode.fw.test;


import com.anabode.fw.ActionScript;
import com.anabode.fw.Base;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created with IntelliJ IDEA.
 * User: KristapsK
 * Date: 13.18.2
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest extends Game {
    private Base base;

    public static void main(String[] args) {
        new LwjglApplication(new BaseTest(), "title", 800, 600, true);
    }

    @Override
    public void create() {
        base = new Base();
        base.initialize();
        base.toggleDebugRenderer();
        base.setGravity(0, -1f);
        base.setViewPort(10, 10);
        TestObject testObject = new TestObject();
        testObject.addScript(new TestScrpt());
        base.addObject(testObject);
    }

    @Override
    public void render() {
        base.update();
        base.render();
    }

    private final class TestObject extends GameObject {
        @Override
        public void initialize() {
            addAttribute("position", new Vector2(1.f, 2));
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(0, 0);
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            Body body = getPhysicsWorld().createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.1f, 0.1f);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            shape.dispose();

            addAttribute("body", body);
            super.initialize();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    private final class TestScrpt extends ActionScript {
        private Vector2 position;

        @Override
        public void initialize() {
            position = get("position");
        }

        @Override
        public void onUpdate() {
            position.add(1, 1);
        }
    }
}
