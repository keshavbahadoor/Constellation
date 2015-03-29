package com.constellation.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create ()
    {
        // Initialize services
        Services.initGameObject(this);
        Services.initAssetService(new TextureService());

        // Initialize camera and viewport
        camera = new OrthographicCamera();
        viewport = new FillViewport(100, 100, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);

        // Start first screen
        this.setScreen(new IntroScreen(this));
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

}
