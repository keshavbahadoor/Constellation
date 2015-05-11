package com.constellation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import screens.*;
import services.GoogleGameServices;
import services.Services;
import services.asset.ResourceService;
import services.resource.CustomResourceManager;
import system.GameSettings;

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

        // Initialize preferences
        Services.getGameSettings().initPreferences();

        // Start first screen
        this.setScreen(new IntroScreen(this));
        //this.setScreen(new MenuScreen(this));
        //this.setScreen(new GameScreen(this));

    }

}
