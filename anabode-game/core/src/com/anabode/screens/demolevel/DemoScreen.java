package com.anabode.screens.demolevel;

import com.anabode.fw.Base;
import com.anabode.objects.materials.WoodBoard;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Kristaps Kohs
 */
public class DemoScreen extends AbstractScreen {
    public static final String DEMO_SCREEN_NAME = "demoScreen";
    public static final String DEMO_TEXTURE_ATLAS = "textures/demolevel/demo.atlas";

    public DemoScreen() {
        setId(DEMO_SCREEN_NAME);
    }

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        base = new Base();
        WoodBoard board = new WoodBoard(
                assetManager.getSprite(DEMO_TEXTURE_ATLAS, Constants.WOOD_BOARD_IMAGE),
                new Vector2(0, 0),
                new Vector2(50, 25),
                new Vector2(0, 0),
                0f);

        base.initialize();
        base.setGravity(0, -10f);
        base.setAmbientLight(1.0f);
        base.getCamera().near = 0;
        base.getCamera().far = 10;

        base.setViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        base.setStageViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        base.addObject(board);
        base.toggleDebugRenderer();
        base.processInput();

    }

    @Override
    public void render(float delta) {
        base.render();
    }

    @Override
    public void update() {
        base.update();

    }


    @Override
    public void resize(int width, int height) {
        base.setViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        base.setStageViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
