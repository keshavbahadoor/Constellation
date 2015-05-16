package com.constellation.game;

import com.badlogic.gdx.Game;

import screens.*;
import services.GoogleGameServices;
import services.Services;
import services.asset.ResourceService;
import services.resource.CustomResourceManager;

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
        Services.initTextureService(new ResourceService());
        Services.initGooglePlayGameServices(this.googleServices);
        Services.initResourceManager(new CustomResourceManager());
        Services.getResourceManager().initCustomResources();

        // Start first screen
        //this.setScreen(new IntroScreen(this));
        this.setScreen(new MenuScreen(this));
        //this.setScreen(new GameScreen(this));
    }

}
