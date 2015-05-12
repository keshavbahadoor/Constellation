package services;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import services.asset.NullTextureService;
import services.resource.CustomResourceManager;
import system.TextureInterface;

/**
 * - uses service locator design pattern
 * - static class
 *
 * Created by Keshav on 3/22/2015.
 */
public class Services
{
    private static Game mainGame;
    private static TextureInterface textureService;
    private static TextureInterface nullTextureService;
    private static GoogleGameServices googleServices;
    private static CustomResourceManager resourceManager;
    private static SpriteBatch spriteBatch;

    /**
     * init any services including null object services
     */
    public static void initialize()
    {
        nullTextureService = new NullTextureService();
    }

    /**
     * Init services
     */
    public static void initTextureService(TextureInterface service)
    {
        textureService = service;
    }

    public static void initGameObject(Game game)
    {
        mainGame = game;
    }

    public static void initGooglePlayGameServices(GoogleGameServices service) { googleServices = service; }

    public static void initResourceManager(CustomResourceManager service) { resourceManager = service; }

    public static void initSpriteBatch(SpriteBatch batch) { spriteBatch = batch; }

    public static Game getGameObject()
    {
        return mainGame;
    }

    public static TextureInterface getTextureService()
    {
        if (textureService == null)
            return nullTextureService;
        return textureService;
    }
    public static GoogleGameServices getGPGS(){ return googleServices; }

    public static CustomResourceManager getResourceManager() { return resourceManager; }

    public static SpriteBatch getSpriteBatch()
    {
        if (spriteBatch != null)
            return spriteBatch;
        spriteBatch = new SpriteBatch();
        return spriteBatch;
    }
}
