package screens;

import com.badlogic.gdx.Game;

import stages.MainStage;
import system.AbstractScreen;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{

    public GameScreen(Game game)
    {
        super(game);
        this.stage = new MainStage();
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
