package screens;

import com.badlogic.gdx.Game;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import stages.MainStage;
import system.AbstractScreen;
import system.CustomResourceManager;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{
    private CustomResourceManager rm;

    public GameScreen(Game game)
    {
        super(game);

        rm = new CustomResourceManager();
        rm.initCustomResources();

        this.stage = new MainStage(rm);
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
