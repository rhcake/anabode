package com.anabode.fw.test;

import com.anabode.fw.GameObject;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Date: 13.24.2
 * Time: 18:07
 *
 * @author Kristaps Kohs
 */
public class TestButtonObject extends GameObject {
    @Override
    public void create() {
        TextButton button = new TextButton("BUTTOOOON", getAsset("uiskin.json", Skin.class));
        addAttribute("button", button);
    }
}
