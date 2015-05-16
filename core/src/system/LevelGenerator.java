package system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

import java.util.Random;

import GameEntities.CompositeLevelSegment;
import GameEntities.Player;
import enumeration.GameEvent;
import scripts.MovingSegmentScript;

/**
 * Created by Laptop on 5/9/2015.
 */
public class LevelGenerator implements IDrawable, Observer, Observerable
{
    private static final int MAX_SEGMENTS = 4;
    private static final float SPAWN_TIME = 1800;
    private long startTime;
    private long elapsedTime;
    private Random rand;
    private Overlap2DStage gameStage;
    private Array<Observer> observers;
    private boolean isActive = true;

    /**
     * The last segment is stored so that we spawn a segment different to the last one.
     * This makes the game more interesting for the user
     */
    private String lastSegmentName = "seg1";

    // local object pool
    private CompositeItemObjectPool itemObjectPool;

    /**
     * Current level segments
     * - This is used to house current segments sort of like a
     *   reference container. When a level segment is no longer
     *   active, we remove it and add it to the item pool.
     */
    private Array<CompositeLevelSegment> currentLevelSegments;

    /**
     * Constructor
     */
    public LevelGenerator(Overlap2DStage stage)
    {
        this.startTime = TimeUtils.millis();
        this.rand = new Random();
        this.gameStage = stage;
        this.itemObjectPool = new CompositeItemObjectPool(stage.sceneLoader);
        this.currentLevelSegments = new Array<CompositeLevelSegment>();
        this.observers = new Array<Observer>();
    }

    @Override
    public void draw(float delta)
    {
    }

    @Override
    public void update(float delta)
    {
        if (isActive)
        {
            elapsedTime = TimeUtils.timeSinceMillis(startTime);

            if (elapsedTime > SPAWN_TIME) {
                // Reset random segment
                spawnSegment();
                startTime = TimeUtils.millis();
                elapsedTime = 0L;
            }

            for (int i = 0; i < currentLevelSegments.size; i++) {
                if (!currentLevelSegments.get(i).isActive()) {
                    // add to pool
                    itemObjectPool.addEntity(currentLevelSegments.get(i), currentLevelSegments.get(i).getName());

                    // remove from list
                    currentLevelSegments.removeIndex(i);
                }//end if
            }


            for (int i = 0; i < currentLevelSegments.size; i++) {
                currentLevelSegments.get(i).update(delta);
            }
        }

        //Gdx.app.log("Size", "Current Segment Size: "+ currentLevelSegments.size + " Pool Size: " + itemObjectPool.getSize());

    }

    /**
     * transfers segment from available to current
     */
    public void spawnSegment( )
    {
        String segmentname  = getNextSegmentName();

        CompositeLevelSegment segment =  (CompositeLevelSegment) this.itemObjectPool.getEntity(segmentname);
        if (segment == null)
        {// create one ourselves
            segment = new CompositeLevelSegment(this.gameStage.sceneLoader.getLibraryAsActor(segmentname), segmentname);

        }
        // Add script and add to game stage
        segment.resetSegment();
        this.gameStage.addActor(segment.getActor());
        this.currentLevelSegments.add(segment);
    }

    /**
     * Always returns a segment name that is not equal to the last one used
     * @return
     */
    private String getNextSegmentName()
    {
        String name = "seg" + (rand.nextInt(MAX_SEGMENTS)+1);
        while (name.equals(lastSegmentName))
        {
            name = "seg" + (rand.nextInt(MAX_SEGMENTS)+1);
        }
        lastSegmentName = name;
        return name;
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void onNotify( GameEvent event)
    {
        switch (event)
        {
            case PLAYER_DIED: this.isActive = false;
                              this.setVisible(false);
                break;
            case GAME_PAUSE: this.isActive = false;
                break;
            case GAME_RESUMED: this.isActive = true;
        }

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


    public void setVisible(boolean visibility)
    {
        for (int i=0; i<currentLevelSegments.size; i++)
        {
            currentLevelSegments.get(i).setVisible(visibility);
        }
    }






}
