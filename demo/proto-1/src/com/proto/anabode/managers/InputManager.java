package com.proto.anabode.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import org.skinnyelephant.framework.core.Manager;

/**
 * Date: 13.11.2
 * Time: 20:41
 *
 * @author Kristaps Kohs
 */
public class InputManager implements Manager {
    private InputMultiplexer multiplexer;

    @Override
    public void initialize() {
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void addInputProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
    }

    @Override
    public void dispose() {

    }
}
