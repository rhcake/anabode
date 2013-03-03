package com.anabode.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Modris Vekmanis
 */
public class MultiScreenAssetManager extends AssetManager {
    private final Map<String, Map<String, Sprite>> atlasCache;

    public MultiScreenAssetManager() {
        atlasCache = new HashMap<String, Map<String, Sprite>>();
    }

    public synchronized Sprite getSprite(String atlas, String name) {
        if (atlasCache.containsKey(atlas)) {
            Map<String, Sprite> spriteCache = atlasCache.get(atlas);
            if (spriteCache.containsKey(name)) {
                return spriteCache.get(name);
            } else {
                Sprite sprite = get(atlas, TextureAtlas.class).createSprite(name);
                spriteCache.put(name, sprite);
                return sprite;
            }
        } else {
            TextureAtlas textureAtlas = get(atlas, TextureAtlas.class);
            if (textureAtlas != null) {
                Sprite sprite = textureAtlas.createSprite(name);
                Map<String, Sprite> spriteCache = new HashMap<String, Sprite>();
                atlasCache.put(atlas, spriteCache);
                spriteCache.put(name, sprite);

                return sprite;
            }
        }
        return null;
    }

    @Override
    public synchronized void unload(String fileName) {
        if (atlasCache.containsKey(fileName)) {
            atlasCache.get(fileName).clear();
            atlasCache.remove(fileName);
        }
        super.unload(fileName);
    }
}
