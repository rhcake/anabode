package com.proto.anabode.managers;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.skinnyelephant.framework.core.Manager;

/**
 * Date: 13.9.2
 * Time: 19:49
 *
 * @author Kristaps Kohs
 */
public class CameraManager implements Manager {
    private Camera camera;

    @Override
    public void initialize() {
        camera = new OrthographicCamera(24, 16);
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public void dispose() {

    }
}
