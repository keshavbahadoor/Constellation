package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

import GameEntities.CompositeLevelSegment;
import scripts.LevelSegmentScript;
import scripts.ParallaxBackgroundScript;
import scripts.PlayerScript;
import services.Services;
import services.resource.CustomResourceManager;
import system.InputService;
import system.LevelGenerator;
import system.PhysicsContactListener;

/**
 * Created by Laptop on 5/9/2015.
 */
public class TestStage extends Overlap2DStage
{
    private final static int MAX_SEGMENTS = 4;
    private LevelGenerator levelGenerator;

    /**
     * Constructor
     * @param rm
     */
    public TestStage(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        this.levelGenerator = new LevelGenerator();

        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);

        this.sceneLoader.loadScene("MainScene");
        this.addActor(sceneLoader.getRoot());

        this.sceneLoader.getRoot().getCompositeById("player").addScript(new PlayerScript());

        //loadLevelSegments();



        // Set contact detection listener
        this.getWorld().setContactListener(new PhysicsContactListener());

        // Set input processor
        Services.inputService = new InputService();
        Gdx.input.setInputProcessor(Services.inputService);

    }

    /**
     * load segments
     */
    private void loadLevelSegments()
    {
        for (int i=1; i<=MAX_SEGMENTS; i++)
        {
            levelGenerator.addSegment(new CompositeLevelSegment( this.sceneLoader.getRoot().getCompositeById("lg" + i)) );
        }
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        // Draw and update segments
        levelGenerator.draw(delta);
        levelGenerator.update(delta);

    }
}
