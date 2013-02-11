package com.proto.anabode.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.skinnyelephant.framework.core.Manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 13.4.2
 * Time: 19:55
 *
 * @author Kristaps Kohs
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager implements Manager {
    public static String TEXTURE_ATLAS = "textures/textures.pack";
    private final Map<String, Sprite> spriteCache = new HashMap<String, Sprite>();
    private boolean initialized;

    @Override
    public void initialize() {
        if (!initialized) {
            load(TEXTURE_ATLAS, TextureAtlas.class);
            finishLoading();
            initialized = true;
        }
    }

    public Sprite getSprite(final String name) {
        if (spriteCache.containsKey(name)) {
            return spriteCache.get(name);
        } else {
            TextureAtlas atlas = get(TEXTURE_ATLAS, TextureAtlas.class);
            Sprite sprite = atlas.createSprite(name);
            spriteCache.put(name, sprite);
            return sprite;
        }
    }

    @Override
    public void dispose() {

    }
}
