import com.anabode.fw.ActionScript;
import com.anabode.fw.Base;
import com.anabode.fw.GameObject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * @author Kristaps Kohs
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
        BoxObject boxObject = new BoxObject();
        FloorObject floorObject = new FloorObject();

        // add script to object.
        boxObject.addScript(new AttachmentScript());
        floorObject.addScript(new AttachmentScript());

        //Add object to game
        base.addObject(boxObject);
        base.addObject(floorObject);
    }

    @Override
    public void render() {
        base.update();
        base.render();
    }
}
