import com.anabode.fw.Base;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.Vector2;

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
        BoxObject boxObject = new BoxObject(new Vector2(0, 0), 0.1f, 0);
        FloorObject floorObject = new FloorObject();
        BackgroundObject backgroundObject = new BackgroundObject();

        // add script to object.
        boxObject.addScript(new AttachmentScript());
        floorObject.addScript(new AttachmentScript());
        backgroundObject.addScript(new AddBoxScript());


        //Add object to game
        base.addObject(boxObject);
        base.addObject(floorObject);
        base.addObject(backgroundObject);
    }

    @Override
    public void render() {
        base.update();
        base.render();
    }
}
