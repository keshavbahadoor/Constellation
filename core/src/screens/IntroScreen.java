package screens;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import services.Services;
import system.AbstractScreen;

/**
 * Created by Keshav on 3/26/2015.
 */
public class IntroScreen extends AbstractScreen
{
    private static final float DEFAULT_FADE = 0.75F;
    private static final float DEFAULT_DELAY = 0.75F;
    private int currentSplashScreen;
    private Array<Image> splashScreens;

    /**
     * Constructor
     * @param game
     */
    public IntroScreen(Game game)
    {
        super(game);

        this.currentSplashScreen = 0;
        this.splashScreens = new Array<Image>();

        // Add splash screens
        this.addSplashScreen(new Image(Services.getResourceService().getTexture("splashscreen")));

        // Add actor (initial splash screen) to stage
        this.stage.addActor((Image) splashScreens.get(0));

    }

    /**
     * Adds a splash screen to array
     * @param img
     */
    public void addSplashScreen(Image img)
    {
        addSplashScreen(img, DEFAULT_FADE, DEFAULT_DELAY, DEFAULT_FADE);
    }


    /**
     * Adds splash screen
     * @param img
     * @param fadein
     * @param delay
     * @param fadeout
     */
    public void addSplashScreen( Image img, float fadein, float delay, float fadeout)
    {
        img.setPosition(0, 0);
        img.getColor().a = 0f; //allows screen to fade in from black
        img.addAction(sequence (fadeIn(fadein), delay(delay), fadeOut(fadeout),
                new Action(){

                    @Override
                    public boolean act(float delta) {

                        if (currentSplashScreen < splashScreens.size -1)
                        {
                            currentSplashScreen++;
                            stage.clear();
                            stage.addActor((Image)splashScreens.get(currentSplashScreen));
                        }
                        else
                        {
                            stage.dispose();
                            stage.clear();

                            // start main menu here
                            Gdx.app.log("Intro Screen", "finished");

                            Services.getGameObject().setScreen(new GameScreen(Services.getGameObject()));
                        }
                        return true;
                    }//end act

                }));
        splashScreens.add(img);
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
