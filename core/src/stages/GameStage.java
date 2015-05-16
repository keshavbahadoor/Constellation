package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

import enumeration.GameEvent;
import scripts.ParallaxBackgroundScript;
import GameEntities.Player;
import services.Services;
import services.resource.CustomResourceManager;
import system.InputService;
import system.LevelGenerator;
import system.Observer;
import system.Observerable;
import system.PhysicsContactListener;

/**
 * Created by Laptop on 5/9/2015.
 */
public class GameStage extends Overlap2DStage implements Observer, Observerable
{
    private LevelGenerator levelGenerator;
    private CompositeItem lowerBound;
    private CompositeItem upperBound;
    private Player player;
    private Array<Observer> observers;



    /**
     * Constructor
     * @param rm
     */
    public GameStage(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        // Set up scene loader and main scene
        this.observers = new Array<Observer>();
        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);
        this.sceneLoader.loadScene("MainScene");
        this.addActor(sceneLoader.getRoot());

        // Add screen bounds
        prepareScreenBounds();

        // Add parallax backgrounds
        prepareParallaxBackgrounds();

        // Add main player script
        this.player = new Player(this.sceneLoader.getRoot().getCompositeById("player"));

        // Set contact detection listener
        this.getWorld().setContactListener(new PhysicsContactListener());

        // Set input processor
        Services.inputService = new InputService();
        Gdx.input.setInputProcessor(Services.inputService);

        // Init level generator
        this.levelGenerator = new LevelGenerator(this);
    }

    /**
     * prepare parallax background and add scripts
     */
    public void prepareParallaxBackgrounds()
    {
        this.sceneLoader.getRoot().getCompositeById("bgBottom").addScript(new ParallaxBackgroundScript("bg1", "bg2", 30));
        this.sceneLoader.getRoot().getCompositeById("bgTop").addScript(new ParallaxBackgroundScript("bgTop1", "bgTop2", 30));
        this.sceneLoader.getRoot().getCompositeById("asteroids").addScript(new ParallaxBackgroundScript("as1", "as2", 10));

        // correct any offset problems due to different resolution support
        this.sceneLoader.getRoot().getCompositeById("bgTop").setY(Gdx.graphics.getHeight() - sceneLoader.getRoot().getCompositeById("bgTop").getHeight());
    }

    /**
     * Prepare all screen bound objects
     *   Trim lower and upper screen bounds so that it does not change on different resolutions
     */
    public void prepareScreenBounds()
    {
        lowerBound = this.sceneLoader.getRoot().getCompositeById("lowerbound");
        upperBound = this.sceneLoader.getRoot().getCompositeById("upperbound");
        lowerBound.getBody().getPosition().y = -lowerBound.getHeight();
        upperBound.getBody().getPosition().y = Gdx.graphics.getHeight();
        lowerBound.setY(-lowerBound.getHeight());
        upperBound.setY(Gdx.graphics.getHeight());
    }



    @Override
    public void act(float delta)
    {
        super.act(delta);

        // Update and draw level generator
        levelGenerator.update(delta);
        levelGenerator.draw(delta);

        // Update and draw player
        player.update(delta);
        player.draw(delta);
    }


    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void onNotify( GameEvent event)
    {
        Gdx.app.log("Received", event.name() + " from " + this.getClass().getName());


    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.removeValue(observer, true);
    }

    @Override
    public void notifyObservers(GameEvent event)
    {
        for (int i=0; i<observers.size; i++)
        {
            observers.get(i).onNotify(event);
        }
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public Player getPlayer() {
        return player;
    }
}
