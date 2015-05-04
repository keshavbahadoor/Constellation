package screens;

import com.badlogic.gdx.Game;

import services.Services;
import stages.GameStageUI;
import stages.GameStage;
import system.AbstractScreen;
import system.CustomResourceManager;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{

    public GameScreen(Game game)
    {
        super(game);

        overlapResourceManager = new CustomResourceManager();
        overlapResourceManager.initCustomResources();

        Services.getGPGS().getLeaderboardGPGS();

        this.addStageComponent(new GameStage(overlapResourceManager));
        this.addStageComponent(new GameStageUI(overlapResourceManager));
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {

    }
}
