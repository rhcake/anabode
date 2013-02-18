import com.anabode.fw.ActionScript;
import com.anabode.fw.Base;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.Matrix4;
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
        // Creates and sets up framework base.
        base = new Base();
        base.initialize();
        base.toggleDebugRenderer();
        base.setGravity(0, -1f);
        base.setViewPort(10, 10);


        // Creates objects
        TestObject testObject = new TestObject();
        TestObject2 testObject2 = new TestObject2();

        // add script to object.
        testObject.addScript(new TestScrpt());

        //Add object to game
        base.addObject(testObject);
        base.addObject(testObject2);
    }

    @Override
    public void render() {
        base.update();
        base.render();
    }

    private final class TestObject extends GameObject {

        @Override
        public void create() {
            addAttribute("position", new Vector2(1.f, 2));
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(0, 2);
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            Body body = getPhysicsWorld().createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.1f, 0.1f);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            shape.dispose();

            addAttribute("body", body);
        }
    }

    private final class TestObject2 extends GameObject {

        @Override
        public void create() {
            addAttribute("position", new Vector2(1.f, 2));
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(0, 0);
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = getPhysicsWorld().createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.1f, 0.1f);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            shape.dispose();

            addAttribute("body", body);
        }
    }

    private final class TestScrpt extends ActionScript {
        private Vector2 position;
        private Body body;
        private Matrix4 matrix4 = new Matrix4();

        @Override
        public void initialize() {
            position = get("position");
            body = get("body");
        }

        @Override
        public void onUpdate() {
            position.add(1, 1);
        }


        @Override
        public void onCollision(GameObject from) {
            Gdx.app.log("IIT COLLIDED!!!!", "!!!");
            super.onCollision(from);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onTouchUp() {
            body.setActive(true);
        }

        @Override
        public void onTouchDragged() {

            Gdx.app.log("IITSS DRAGGINN!!!!", "!!!");
            body.setActive(false);
            body.setTransform(getPointer(), 0);
        }
    }
}
