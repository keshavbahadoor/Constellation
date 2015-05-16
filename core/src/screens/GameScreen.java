package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import GameEntities.LevelSegment;
import services.Services;
import stages.GameStage;
import stages.GameStageUI;
import system.AbstractScreen;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{
    private GameStage gameStage;
    private GameStageUI gameStageUI;

    public GameScreen(Game game)
    {
        super(game);

        // Create resource manager and services
        this.overlapResourceManager = Services.getResourceManager();
        Services.initSpriteBatch(this.spriteBatch);
        // TODO : move to button Services.getGPGS().getLeaderboardGPGS();

        this.gameStage = new GameStage(overlapResourceManager);
        this.gameStageUI = new GameStageUI(overlapResourceManager);

        // Register observers
        gameStage.addObserver(gameStageUI);
        gameStageUI.addObserver(gameStage);
        gameStage.getPlayer().addObserver(gameStageUI);
        gameStage.getPlayer().addObserver(gameStage);
        gameStage.getPlayer().addObserver(gameStage.getLevelGenerator());


        // Add to components
        this.addStageComponent(gameStage);
        this.addStageComponent(gameStageUI);
    }

    @Override
    public void loadContent()
    {

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void draw(float delta)
    {
    }
}
