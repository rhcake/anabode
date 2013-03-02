package com.anabode.screen.test;

import com.anabode.fw.GameObject;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created with IntelliJ IDEA.
 * User: Kristaps
 * Date: 13.28.2
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class ButtonObject extends GameObject {
    private AssetManager assetManager;

    public ButtonObject(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void create() {
        addAttribute("Button", new TextButton("PUSH ME", assetManager.get("data/ui/uiskin.json", Skin.class)));

    }
}
