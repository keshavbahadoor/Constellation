package services;

import com.badlogic.gdx.Game;

import services.asset.NullTextureService;
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
}
