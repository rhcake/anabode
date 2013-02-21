package com.anabode.fw.test;

import com.anabode.fw.Base;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Texture;
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
        backgroundObject.addScript(new AddBoxScript());

        base.loadAsset("chain.png", Texture.class);
        //Add object to game
        base.addObject(boxObject);
        base.addObject(floorObject);
        base.addObject(floorObject2);
        base.addObject(floorObject3);
        base.addObject(backgroundObject);
    }

    @Override
    public void render() {
        base.update();
        base.render();

    }
}
