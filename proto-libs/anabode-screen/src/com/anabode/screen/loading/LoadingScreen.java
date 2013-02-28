package com.anabode.screen.loading;

import com.anabode.fw.Base;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Date: 13.28.2
 * Time: 12:55
 *
 * @author Kirstaps Kohs
 */
public class LoadingScreen extends AbstractScreen {
    public static final String SCREEN_NAME = "LoadingScreen";
    private final AssetManager mainAssetManager;
    private final Image logo;
    private final Image loadingFrame;
    private final Image loadingBarHidden;
    private final Image screenBg;
    private final Image loadingBg;
    private final Stage stage;
    private final Actor loadingBar;
    private float startX, endX;
    private float percent;
    private AssetManager toLoad;
    private AssetManager toUnload;
    private boolean active;


    public LoadingScreen(GameManager gameManager) {
        super(gameManager);
        mainAssetManager = gameManager.getMainAssetManager();
        stage = new Stage();

        TextureAtlas loadingAtlas = mainAssetManager.get("data/loading/loadingTextureAtlas.atlas", TextureAtlas.class);
        logo = new Image(loadingAtlas.findRegion("logo"));
        loadingFrame = new Image(loadingAtlas.findRegion("loading-frame"));
        loadingBarHidden = new Image(loadingAtlas.findRegion("loading-bar-hidden"));
        screenBg = new Image(loadingAtlas.findRegion("background"));
        loadingBg = new Image(loadingAtlas.findRegion("frame-background"));

        Animation animation = new Animation(0.05f, loadingAtlas.findRegions("loading-animations"));
        animation.setPlayMode(Animation.LOOP_REVERSED);
        loadingBar = new LoadingBar(animation);
        stage.addActor(screenBg);
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingBarHidden);
        stage.addActor(loadingFrame);
        stage.addActor(logo);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        if (active) {
            float progress = 0;
            boolean toLoadDone;
            boolean toUnloadDone;
            toLoadDone = toLoad == null || toLoad.update();

            if (toLoad != null) {
                progress += toLoad.getProgress();
            }
            toUnloadDone = toUnload == null || toUnload.update();

            if (toUnload != null) {
                progress += toUnload.getProgress();
            }
            if (toLoadDone && toUnloadDone) {
                gameManager.showScreen(getNextScreen());
            }

            if (progress > 1) {
                progress *= .5f;
            }


            percent = Interpolation.linear.apply(percent, progress, 1f);

            // Update positions (and size) to match the percentage
            loadingBarHidden.setX(startX + endX * percent);
            loadingBg.setX(loadingBarHidden.getX() + 30);
            loadingBg.setWidth(450 - 450 * percent);
            loadingBg.invalidate();

            // Show the loading screen
            stage.act();
            stage.draw();

        }
    }

    @Override
    public void resize(int width, int height) {
        screenBg.setSize(width, height);

        width = 480 * width / height;
        height = 480;
        stage.setViewport(width, height, false);


        logo.setX((width - logo.getWidth()) / 2);
        logo.setY((height - logo.getHeight()) / 2 + 100);

        loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

        loadingBar.setX(loadingFrame.getX() + 15);
        loadingBar.setY(loadingFrame.getY() + 5);

        loadingBarHidden.setX(loadingBar.getX() + 35);
        loadingBarHidden.setY(loadingBar.getY() - 3);
        startX = loadingBarHidden.getX();
        endX = 440;

        loadingBg.setSize(450, 50);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setY(loadingBarHidden.getY() + 3);
    }

    @Override
    public void show() {
        toLoad = getNextScreen().getAssetManager();
        AbstractScreen currentScreen = getCurrentScreen();
        if (currentScreen != null) {
            toUnload = getCurrentScreen().getAssetManager();
        }
        active = true;
    }

    @Override
    public void hide() {
        active = false;
        toLoad = null;
        toUnload = null;
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

    @Override
    protected void init() {
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public Base getBase() {
        return null;
    }

    @Override
    public AssetManager getAssetManager() {
        return null;
    }

    @Override
    public String getScreenName() {
        return SCREEN_NAME;
    }
}
