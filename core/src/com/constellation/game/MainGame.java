package com.constellation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import screens.GameScreen;
import screens.IntroScreen;
import services.Services;
import services.asset.TextureService;

/**
 * This is the entry point of the game
 */
public class MainGame extends Game
{
    @Override
    public void create ()
    {
        // Initialize services
        Services.initGameObject(this);
        Services.initTextureService(new TextureService());

        // Start first screen
        //this.setScreen(new IntroScreen(this));
        this.setScreen(new GameScreen(this));
    }

}
