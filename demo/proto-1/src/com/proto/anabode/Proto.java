package com.proto.anabode;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.math.Vector3;
import com.proto.anabode.managers.CameraManager;
import com.proto.anabode.managers.InputManager;
import com.proto.anabode.managers.PhysicsManager;
import com.proto.anabode.systems.PhysicsSystem;
import com.proto.anabode.systems.RenderingSystem;
import com.proto.anabode.util.EntityFactory;
import org.skinnyelephant.framework.core.Core;

/**
 * Date: 13.11.2
 * Time: 20:33
 *
 * @author Kristaps Kohs
 */
public class Proto implements ApplicationListener {
    private Core core;
    private CameraManager cameraManager;
    private FPSLogger fpsLogger;



    @Override
    public void create() {
        core = new Core();
        core.initialize();
        core.addManager(new PhysicsManager());
        cameraManager = new CameraManager();
        core.addManager(cameraManager);
        InputManager manager = new InputManager();
        core.addManager(manager);
        manager.addInputProcessor(new AddBoxAction());
        //  core.addManager(new AssetManager());

        core.addSystem(new RenderingSystem());
        core.addSystem(new PhysicsSystem());

        EntityFactory.createBorder(0, -8, 12, .2f, core);
        EntityFactory.createBorder(0, 8, 12, .2f, core);
        EntityFactory.createBorder(-12, 0, .2f, 12f, core);
        EntityFactory.createBorder(12, 0, .2f, 12f, core);
        fpsLogger = new FPSLogger();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        fpsLogger.log();
        core.process(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private class AddBoxAction extends InputAdapter {
        private final Vector3 pos = new Vector3();



        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

            pos.set(screenX, screenY, 0);
            cameraManager.getCamera().unproject(pos);

            if (Input.Buttons.LEFT == button)
            EntityFactory.createBox(pos.x, pos.y, 0.08f, 1f, core);
            else
                EntityFactory.createBox(pos.x, pos.y, 1, 0.08f, core);
            return super.touchDown(screenX, screenY, pointer, button);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) {
        new LwjglApplication(new Proto(), "ttile", 800, 600, true);
    }
}
