import com.anabode.fw.ActionScript;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * Created with IntelliJ IDEA.
 * User: kristapsk
 * Date: 13.19.2
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class AttachmentScript extends ActionScript {
    private Vector2 position;
    private Body body;

    @Override
    public void initialize() {
        position = get("position");
        body = get("body");
    }

    @Override
    public void onUpdate() {
        position.set(body.getPosition());
    }

    @Override
    public void onCollision(GameObject from) {
        super.onCollision(from);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onTouchUp() {
        body.setActive(true);
        GameObject source = getSelectionSource();
        if (source != null) {
            RevoluteJointDef jointDef = new RevoluteJointDef();
            jointDef.collideConnected = true;
            Body bodyA = (Body) source.get("body");

            jointDef.initialize(bodyA, body, position);
            createJoint(jointDef);
        }
    }


}
