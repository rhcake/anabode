package com.anabode.scripts.ui;

import com.anabode.fw.ActionScript;
import com.anabode.objects.ui.Button;
import com.anabode.screen.ScreenHandler;
import com.anabode.screens.demolevel.DemoScreen;
import com.anabode.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * @author Kristaps Kohs
 */
public class NewGameScript extends ActionScript {
    private ScreenHandler screenHandler;
    private ImageButton button;

    public NewGameScript(ScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
    }

    @Override
    protected void initialize() {

        button = get(Button.BUTTON_KEY);
    }

    @Override
    protected void onUpdate() {
        button.setDisabled(!screenHandler.assetsLoaded());
    }

    @Override
    protected void onGuiTouch() {
        if (!button.isDisabled()) {

            try {
                screenHandler.unloadScreen(Constants.MENU_SCREEN);
                screenHandler.loadActiveScreen(new DemoScreen());
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        Gdx.app.log(NewGameScript.class.getName(), "Start new game");
    }
}
