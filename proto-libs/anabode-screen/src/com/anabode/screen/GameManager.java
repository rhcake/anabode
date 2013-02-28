package com.anabode.screen;

import com.anabode.screen.loading.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 13.28.2
 * Time: 12:58
 *
 * @author Kirstaps Kohs
 */
public abstract class GameManager extends Game {
    protected AssetManager assetManager;
    private Map<String, AbstractScreen> screenMap = new HashMap<String, AbstractScreen>();
    private AbstractScreen activeScreen;
    private AbstractScreen nextScreen;
    private LoadingScreen loadingScreen;


    protected GameManager() {
        assetManager = new AssetManager();
    }

    public abstract AssetManager getMainAssetManager();

    public abstract void init();

    public void showScreen(String name) {
        AbstractScreen screen = screenMap.get(name);
        if (screen == null) {
            throw new IllegalStateException("Invalid screen name passed: " + screen);
        }
        showScreen(screen);
    }

    public void showScreen(AbstractScreen screen) {
        if (loadingScreen != null && !loadingScreen.isActive()) {
            AbstractScreen nextScreen = screenMap.get(screen.getScreenName());
            if (nextScreen == null) {
                throw new IllegalStateException("Invalid screen name passed: " + screen);
            }
            this.nextScreen = nextScreen;
            setScreen(loadingScreen);
        } else {
            activeScreen = getNextScreen();
            setScreen(getNextScreen());
            nextScreen = null;
        }
    }

    public void setLoadingScreen(LoadingScreen loadingScreen) {
        this.loadingScreen = loadingScreen;
    }

    public void addScreen(AbstractScreen screen) {
        screenMap.put(screen.getScreenName(), screen);
        screen.init();
    }

    public AbstractScreen getCurrentScreen() {
        return activeScreen;
    }

    public AbstractScreen getNextScreen() {
        return nextScreen;
    }

}
