package com.anabode.screen;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;

/**
 * @author Modris Vekmanis
 */
public class GameAssetDescriptor extends AssetDescriptor {
    private volatile int loadRequests = 0;

    public GameAssetDescriptor(String fileName, Class assetType) {
        super(fileName, assetType);
    }

    public GameAssetDescriptor(String fileName, Class assetType, AssetLoaderParameters params) {
        super(fileName, assetType, params);
    }
}
