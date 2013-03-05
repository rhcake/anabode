package com.anabode.screens.demolevel;

import com.anabode.fw.Base;
import com.anabode.objects.environment.ShaftWall;
import com.anabode.objects.environment.Sun;
import com.anabode.objects.materials.WoodBoard;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
        base.setAmbientLight(.40f);


        float shaftWidth = (Gdx.graphics.getWidth() * ShaftWall.SHAFT_WALL_THICKNESS_PERCENT) / 100;
        float shaftHeight = (Gdx.graphics.getHeight() * ShaftWall.SHAFT_WALL_HEIGHT_PERCENT) / 100;

        base.setViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        base.setStageViewPort(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        base.getCamera().update();
        Vector3 v3 = new Vector3().set(0 + shaftWidth * .5f, Gdx.graphics.getHeight() * .5f + (Gdx.graphics.getHeight() - shaftHeight) * .5f, 0);
        base.getCamera().unproject(v3);
        ShaftWall shaftWall = new ShaftWall(
                new Vector2(v3.x, v3.y),
                new Vector2(shaftWidth, shaftHeight),
                assetManager.getSprite(DEMO_TEXTURE_ATLAS, Constants.SHAFT_WALL_IMAGE)
        );
        base.addObject(shaftWall);

        v3.set(Gdx.graphics.getWidth() - shaftWidth * .5f, Gdx.graphics.getHeight() * .5f + (Gdx.graphics.getHeight() - shaftHeight) * .5f, 0);
        base.getCamera().unproject(v3);
        shaftWall = new ShaftWall(
                new Vector2(v3.x, v3.y),
                new Vector2(shaftWidth, shaftHeight),
                assetManager.getSprite(DEMO_TEXTURE_ATLAS, Constants.SHAFT_WALL_IMAGE)
        );
        base.addObject(shaftWall);


        shaftWidth = (Gdx.graphics.getWidth() * ShaftWall.SHAFT_FLOOR_THICKNESS_PERCENT) / 100;
        shaftHeight = (Gdx.graphics.getHeight() * ShaftWall.SHAFT_FLOOR_HEIGHT_PERCENT) / 100;

        v3.set(Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() - shaftHeight * .5f, 0);
        base.getCamera().unproject(v3);
        shaftWall = new ShaftWall(
                new Vector2(v3.x, v3.y),
                new Vector2(shaftWidth, shaftHeight),
                assetManager.getSprite(DEMO_TEXTURE_ATLAS, Constants.SHAFT_WALL_IMAGE)
        );
        base.addObject(shaftWall);

        base.addObject(new Sun());
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
