package screens;

import com.badlogic.gdx.Game;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

import services.Services;
import stages.ARInfoStage;
import stages.MainMenuBackgroundStage;
import stages.MainMenuStage;
import system.AbstractScreen;

/**
 * Created by Keshav on 5/16/2015.
 */
public class ARInfoScreen  extends AbstractScreen
{



    /**
     * Constructor
     * @param game
     */
    public ARInfoScreen(Game game)
    {
        super(game);

        this.overlapResourceManager = Services.getResourceManager();
        this.addStageComponent(new ARInfoStage(overlapResourceManager));

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
