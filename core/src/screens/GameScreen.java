package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import services.Services;
import stages.GameStageUI;
import stages.GameStage;
import system.AbstractScreen;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{
    TextureRegion region;

    public GameScreen(Game game)
    {
        super(game);

        this.overlapResourceManager = Services.getResourceManager();

        Services.getGPGS().getLeaderboardGPGS();

        this.addStageComponent(new GameStage(overlapResourceManager));
        this.addStageComponent(new GameStageUI(overlapResourceManager));

        region = Services.getResourceManager().getTextureRegion("barrel (2)");
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(float delta)
    {
        spriteBatch.begin();
        spriteBatch.draw(region, 0, 0  );
        spriteBatch.end();
    }
}
