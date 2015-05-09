package screens;

import com.badlogic.gdx.Game;

import services.Services;
import stages.MainMenuBackgroundStage;
import stages.MainMenuStage;
import system.AbstractScreen;
import services.resource.CustomResourceManager;

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

        this.overlapResourceManager = Services.getResourceManager();

        this.addStageComponent(new MainMenuBackgroundStage(overlapResourceManager));
        this.addStageComponent( new MainMenuStage(overlapResourceManager));
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
