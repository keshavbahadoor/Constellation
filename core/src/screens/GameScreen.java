package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import GameEntities.LevelSegment;
import services.Services;
import stages.GameStageUI;
import stages.GameStage;
import stages.TestStage;
import system.AbstractScreen;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 3/28/2015.
 */
public class GameScreen extends AbstractScreen
{
    TextureRegion region;
    LevelSegment ls ;

    public GameScreen(Game game)
    {
        super(game);

        this.overlapResourceManager = Services.getResourceManager();
        Services.initSpriteBatch(this.spriteBatch);

        Services.getGPGS().getLeaderboardGPGS();

//        this.addStageComponent(new GameStage(overlapResourceManager));
//        this.addStageComponent(new GameStageUI(overlapResourceManager));
        this.addStageComponent(new TestStage(overlapResourceManager));

        //region = Services.getResourceManager().getTextureRegion("barrel (2)");
//        ls = new LevelSegment("");
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void update(float delta) {
        //ls.setX(ls.getX() + 1 * delta);

//        ls.update(delta);
    }

    @Override
    public void draw(float delta)
    {
        spriteBatch.begin();
        //spriteBatch.draw(region, 0, 0  );
//        ls.draw(delta);
        spriteBatch.end();
    }
}
