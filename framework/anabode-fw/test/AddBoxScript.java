import com.anabode.fw.ActionScript;
import com.badlogic.gdx.InputProcessor;

/**
 * Date: 13.19.2
 * Time: 10:45
 *
 * @author Kirstaps Kohs
 */
public class AddBoxScript extends ActionScript implements InputProcessor {
    @Override
    public void initialize() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (getSelectionSource() == null) {
            BoxObject boxObject = new BoxObject(getPointer());
            boxObject.addScript(new AttachmentScript());
            getBase().addObject(boxObject);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
