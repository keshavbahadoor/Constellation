package com.constellation.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import screens.GameScreen;
import screens.IntroScreen;
import services.Services;
import services.asset.TextureService;
import stages.MainStage;

/**
 * This is the entry point of the game
 */
public class MainGame extends Game
{
    private MainStage mainStage;

    @Override
    public void create ()
    {
        // Initialize services
        Services.initGameObject(this);
        Services.initAssetService(new TextureService());

        // Start first screen
        this.setScreen(new IntroScreen(this));
    }
}
