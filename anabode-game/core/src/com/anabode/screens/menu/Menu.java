package com.anabode.screens.menu;

import com.anabode.fw.Base;
import com.anabode.objects.ui.Button;
import com.anabode.screen.AbstractScreen;
import com.anabode.screen.MultiScreenAssetManager;
import com.anabode.screens.demolevel.DemoScreen;
import com.anabode.scripts.ui.NewGameScript;
import com.anabode.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Modris Vekmanis
 */
public class Menu extends AbstractScreen {
    public static final String TEXTURE_ATLAS_NAME = "textures/menu/menu.atlas";
    public static final String BUTTON_UP_IMAGE = "newGameUp";
    public static final String BUTTON_DOWN_IMAGE = "newGameDown";

    private MultiScreenAssetManager assetManager;

    public Menu() throws Exception {
        this.setId(Constants.MENU_SCREEN);
    }

    @Override
    public void create(MultiScreenAssetManager assetManager) {
        base = new Base();

        Button button = new Button
                (assetManager.getSprite(TEXTURE_ATLAS_NAME, BUTTON_UP_IMAGE), assetManager.getSprite(TEXTURE_ATLAS_NAME, BUTTON_DOWN_IMAGE),
                        new Vector2(300, 400), new Vector2(200, 200));
        button.setName("NewGameButton");
        button.addScript(new NewGameScript(controller.getScreenHandler()));
        base.addObject(button);

        base.initialize();
        base.processInput();
        this.assetManager = assetManager;
        assetManager.load(DemoScreen.DEMO_TEXTURE_ATLAS, TextureAtlas.class);

    }

    @Override
    public void render(float delta) {
        assetManager.update();
        base.update();
        base.render();

    }

    @Override
    public void dispose(MultiScreenAssetManager assetManager) {
    }
}
