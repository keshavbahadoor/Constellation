package screens;

import com.badlogic.gdx.Game;

import services.Services;
import stages.MainMenuStage;
import stages.MainStage;
import system.AbstractScreen;
import system.CustomResourceManager;

/**
 * Created by Keshav on 3/28/2015.
 */
public class MenuScreen extends AbstractScreen
{

    /**
     * Constructor
     * @param game
     */
    public MenuScreen(Game game)
    {
        super(game);

        // TODO : refactor into services
        overlapResourceManager = new CustomResourceManager();
        overlapResourceManager.initCustomResources();


        this.stage = new MainMenuStage(overlapResourceManager);
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
