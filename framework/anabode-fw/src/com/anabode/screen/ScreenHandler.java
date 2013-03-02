package com.anabode.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Modris Vekmanis
 */
public final class ScreenHandler implements ApplicationListener {
    private MultiScreenAssetManager assetManager;
    private List<AbstractScreen> activeScreens;
    private List<AbstractScreen> loadedScreens;
    private GameController controller;

    private Comparator<AbstractScreen> comparator = new Comparator<AbstractScreen>() {
        @Override
        public int compare(AbstractScreen o1, AbstractScreen o2) {
            if (o1.getRenderOrder() < o2.getRenderOrder()) return 1;
            if (o1.getRenderOrder() > o2.getRenderOrder()) return -1;
            return 0;
        }
    };

    public ScreenHandler() {
        assetManager = new MultiScreenAssetManager();
        activeScreens = new LinkedList<AbstractScreen>();
        loadedScreens = new LinkedList<AbstractScreen>();
    }

    @SuppressWarnings("unused")
    public void loadActiveScreen(AbstractScreen screen) throws Exception {
        loadScreen(screen);
        activeScreens.add(screen);
        Collections.sort(activeScreens, comparator);
    }

    public void loadScreen(AbstractScreen screen) throws Exception {
        if (screen.getId() == null) {
            throw new Exception("Screen has no id");
        }

        if (!loadedScreens.contains(screen)) {
            screen.create(assetManager);
            loadedScreens.add(screen);
        } else {
            throw new Exception("Screen with such id is already loaded");
        }
    }

    @SuppressWarnings("unused")
    public void unloadScreen(AbstractScreen screen) {
        activeScreens.remove(screen);
        loadedScreens.remove(screen);
        screen.dispose(assetManager);
    }

    @SuppressWarnings("unused")
    public void unloadScreen(String id) throws Exception {
        AbstractScreen screen;
        for (int i = 0; i < loadedScreens.size(); i++) {
            screen = loadedScreens.get(i);
            if (id.equals(screen.getId())) {
                activeScreens.remove(screen);
                loadedScreens.remove(i);
                return;
            }
        }
        throw new Exception("Screen with such id is not loaded");
    }

    @SuppressWarnings("unused")
    public void activate(String id) throws Exception {
        AbstractScreen screen;
        for (int i = 0; i < loadedScreens.size(); i++) {
            screen = loadedScreens.get(i);
            if (id.equals(screen.getId())) {
                if (!activeScreens.contains(screen)) {
                    activeScreens.add(screen);
                }
                return;
            }
        }
        throw new Exception("Screen with such id is not loaded");
    }

    @SuppressWarnings("unused")
    public void deactivate(String id) throws Exception {
        AbstractScreen screen;
        for (int i = 0; i < activeScreens.size(); i++) {
            screen = activeScreens.get(i);
            if (id.equals(screen.getId())) {
                activeScreens.remove(i);
                return;
            }
        }
        throw new Exception("Screen with such id is not active");
    }

    @Override
    public void create() {
        controller.setScreenHandler(this);
        try {
            controller.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resize(int width, int height) {
        for (AbstractScreen screen : loadedScreens) {
            screen.resize(width, height);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        try {
            controller.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (AbstractScreen screen : activeScreens) {
            screen.update();
            if (screen.isVisible()) {
                screen.render(Gdx.graphics.getDeltaTime());
            }
        }
    }

    @Override
    public void pause() {
        for (AbstractScreen screen : activeScreens) {
            screen.pause();
        }
    }

    @Override
    public void resume() {
        for (AbstractScreen screen : activeScreens) {
            screen.resume();
        }
    }

    @Override
    public void dispose() {
        for (AbstractScreen screen : loadedScreens) {
            screen.dispose(assetManager);
        }
    }

    @SuppressWarnings("unused")
    public void setController(GameController controller) {
        this.controller = controller;
    }
}
