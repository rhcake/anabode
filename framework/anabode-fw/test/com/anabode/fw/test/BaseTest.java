package com.anabode.fw.test;

import com.anabode.fw.Base;
import com.anabode.screen.AbstractScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author Kristaps Kohs
 */
public class BaseTest extends Game {
    private Base base;
    private AbstractScreen screen;

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
        base.setAmbientLight(0f);
        AssetManager assetManager = new AssetManager();
        assetManager.load("uiskin.json", Skin.class);
        assetManager.load("chain.png", Texture.class);
        assetManager.finishLoading();
        // Creates objects
        BoxObject boxObject = new BoxObject(new Vector2(0, 0), 0.1f, 0, assetManager);
        FloorObject floorObject = new FloorObject(new Vector2(0, -4.5f), 4.5f, 0.1f);
        FloorObject floorObject2 = new FloorObject(new Vector2(4.6f, -1.6f), 0.1f, 3f);
        FloorObject floorObject3 = new FloorObject(new Vector2(-4.6f, -1.6f), 0.1f, 3f);
        BackgroundObject backgroundObject = new BackgroundObject();

        // add script to object.
        boxObject.addScript(new AttachmentScript());
        floorObject.addScript(new AttachmentScript());
        floorObject3.addScript(new AttachmentScript());
        floorObject2.addScript(new AttachmentScript());
        boxObject.addScript(new LightingScript());
        boxObject.addScript(new RenderScript());
        backgroundObject.addScript(new AddBoxScript(assetManager));
        TestButtonObject buttonObject = new TestButtonObject(assetManager);
        buttonObject.addScript(new ButtonScript(screen));


        //Add object to game
        base.addObject(boxObject);
        base.addObject(floorObject);
        base.addObject(floorObject2);
        base.addObject(floorObject3);
        base.addObject(backgroundObject);
        base.addObject(buttonObject);
    }

    @Override
    public void render() {
        base.update();
        base.render();
    }

    @Override
    public void dispose() {
        base.dispose();
    }

    public Base getBase() {
        return base;
    }

    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }
}
