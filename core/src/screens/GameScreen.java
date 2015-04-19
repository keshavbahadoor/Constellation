package screens;

import com.badlogic.gdx.Game;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import services.Services;
import stages.MainStage;
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

        this.stage = new MainStage(overlapResourceManager);
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
