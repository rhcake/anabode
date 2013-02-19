import com.anabode.fw.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created with IntelliJ IDEA.
 * User: kristapsk
 * Date: 13.19.2
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class FloorObject extends GameObject {
    @Override
    public void create() {

        addAttribute("position", new Vector2());

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = getPhysicsWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.1f, 1f);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();

        addAttribute("body", body);
    }
}
