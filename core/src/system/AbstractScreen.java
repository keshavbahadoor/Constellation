package system;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.constellation.game.MainGame;

/**
 * Abstract screen implementation
 * Created by Keshav on 3/26/2015.
 */
public abstract class AbstractScreen implements Screen
{
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    protected SpriteBatch spriteBatch;
    protected Game game;
    protected Stage stage;
    protected float timer;
    protected float timeDuration;

    /**
     * Constructor method
     * @param game
     */
    public AbstractScreen(Game game)
    {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(800, 480));
        this.timer = 0.0F;
        this.timeDuration = 1.0F;

        // set input processor to stage
        Gdx.input.setInputProcessor(stage);

        //stage.setViewport(game.getViewport());
        stage.getViewport().update(480, 800);

        // TODO : update current stage - use service?

        // Load any content
        this.loadContent();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update time
        timer += delta;
        if (timer > timeDuration)
        {
            timer = 0.0F;

            //Execute any tasks after duration
            executeTimedTask();
        }

        //update screen
        update(delta);

        //draw screen
        draw(delta);

        if (stage!=null)
        {
            stage.act(delta);
            stage.draw();
        }
    }


    /**
     * Load all content here
     */
    public abstract void loadContent();

    /**
     * Used to separate game logic here
     * @param delta
     */
    public abstract void update(float delta);

    /**
     * Used to separate game drawing here
     * @param delta
     */
    public abstract void draw(float delta);

    /**
     * For overriding.  Executed a timed tasks in the update
     * after a specified amount of time (timeDuration)
     */
    protected void executeTimedTask()
    {

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

        if (stage!=null)
        {
            this.stage.getViewport().update(width, height, false);
        }

    }

    @Override
    public void dispose()
    {
        if (spriteBatch != null) spriteBatch.dispose();
        if (stage != null) stage.dispose();
    }

    @Override
    public void show() {


    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

}
