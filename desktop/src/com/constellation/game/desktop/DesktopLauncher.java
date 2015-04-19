package com.constellation.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.constellation.game.MainGame;

public class DesktopLauncher
{
    private static DesktopGoogleServices googleServices;

	public static void main (String[] arg)
    {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;

        googleServices = new DesktopGoogleServices();

		new LwjglApplication(new MainGame(googleServices), config);
	}


}
