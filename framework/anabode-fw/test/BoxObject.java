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
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class BoxObject extends GameObject {
    private Vector2 position;

    public BoxObject(Vector2 position) {
        this.position = position;
    }

    @Override
    public void create() {
        addAttribute("position", position);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = getPhysicsWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.1f, 0.1f);

        fixtureDef.density = 0.2f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();

        addAttribute("body", body);
    }
}
