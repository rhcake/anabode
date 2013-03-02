package com.anabode.android;

import android.os.Bundle;
import com.anabode.controller.SceneController;
import com.anabode.screen.ScreenHandler;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AnabodeAndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useGL20 = true;
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.setController(new SceneController());
        initialize(screenHandler, configuration);
        super.onCreate(savedInstanceState);

    }
}
