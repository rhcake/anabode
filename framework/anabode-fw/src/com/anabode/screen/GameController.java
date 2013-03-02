package com.anabode.screen;

/**
 * @author Modris Vekmanis
 */
public abstract class GameController {
    protected ScreenHandler screenHandler;

    public abstract void initialize() throws Exception;

    public abstract void update();

    public void setScreenHandler(ScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
    }
}
