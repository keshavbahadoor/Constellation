package com.constellation.game;

import com.badlogic.gdx.Game;

import screens.*;
import services.GoogleGameServices;
import services.Services;
import services.asset.TextureService;

/**
 * This is the entry point of the game
 */
public class MainGame extends Game
{
    /**
     * Google services interface
     */
    private GoogleGameServices googleServices;


    /**
     * Constructor
     * - pass platform specific code here
     */
    public MainGame(GoogleGameServices googleServices)
    {
        this.googleServices = googleServices;
    }

    @Override
    public void create ()
    {
        // Initialize services
        Services.initGameObject(this);
        Services.initTextureService(new TextureService());
        Services.initGooglePlayGameServices(this.googleServices);

        // Start first screen
        //this.setScreen(new IntroScreen(this));
        this.setScreen(new MenuScreen(this));
    }

}
