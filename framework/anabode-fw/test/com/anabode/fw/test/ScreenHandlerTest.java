package com.anabode.fw.test;

import com.anabode.fw.ScreenHandler;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * @author Modris Vekmanis
 */
public class ScreenHandlerTest {

    public static void main(String[] args) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.setController(new TestController());
        new LwjglApplication(screenHandler, "(╯°□°）╯︵ ┻━┻　┳━┳ ", 800, 600, true);
    }

}
