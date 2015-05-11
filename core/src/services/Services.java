package services;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import services.asset.NullResourceService;
import services.resource.CustomResourceManager;
import system.AchievementService;
import system.GameSettings;
import system.InputService;
import system.LocalResourceInterface;

/**
 * - uses service locator design pattern
 * - static class
 *
 * Created by Keshav on 3/22/2015.
 */
public class Services
{
    private static Game mainGame;
    private static LocalResourceInterface textureService;
    private static LocalResourceInterface nullTextureService;
    private static GoogleGameServices googleServices;
    private static CustomResourceManager resourceManager;
    private static SpriteBatch spriteBatch;
    private static AchievementService achievementService;
    private static GameSettings gameSettings;
    private static Vector2 playerPosition = new Vector2(0, 0);
    public static InputService inputService; // TODO refactor;

    /**
     * init any services including null object services
     */
    public static void initialize()
    {
        nullTextureService = new NullResourceService();
    }

    /**
     * Init services
     */
    public static void initTextureService(LocalResourceInterface service)
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

    public static LocalResourceInterface getResourceService()
    {
        if (textureService == null)
            return nullTextureService;
        return textureService;
    }
    public static GoogleGameServices getGPGS(){ return googleServices; }

    public static CustomResourceManager getResourceManager() { return resourceManager; }

    public static AchievementService getAchievementService() { return achievementService; }

    public static GameSettings getGameSettings() { return gameSettings; }

    public static SpriteBatch getSpriteBatch()
    {
        if (spriteBatch != null)
            return spriteBatch;
        spriteBatch = new SpriteBatch();
        return spriteBatch;
    }

    public static void updatePlayerPosition(float x, float y)
    {
        playerPosition.set(x, y);
    }

    public static Vector2 getPlayerPosition () { return playerPosition; }
}
