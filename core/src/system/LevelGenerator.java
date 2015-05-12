package system;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

import GameEntities.CompositeLevelSegment;

/**
 * Created by Laptop on 5/9/2015.
 */
public class LevelGenerator implements IDrawable
{
    /**
     * Houses the composite level segments that are randomly reused
     */
    private Array<CompositeLevelSegment> availableSegments = new Array<CompositeLevelSegment>();
    private Array<CompositeLevelSegment> currentSements = new Array<CompositeLevelSegment>();
    private long startTime;
    private long elapsedTime;
    private Random rand;

    /**
     * Constructor
     */
    public LevelGenerator()
    {
        this.startTime = TimeUtils.millis();
        this.rand = new Random();
    }

    @Override
    public void draw(float delta)
    {
        for(int i=0; i< currentSements.size; i++) {
            currentSements.get(i).draw(delta);
        }
    }

    @Override
    public void update(float delta)
    {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);

        if (elapsedTime > 1000 && availableSegments.size > 0)
        {
            // Reset random segment
            spawnSegment( );
            startTime = TimeUtils.millis();
            elapsedTime = 0L;
        }

        for(int i=0; i< currentSements.size; i++)
        {
            currentSements.get(i).update(delta);
        }

        cleanupSegments();
    }

    /**
     * transfers segment from available to current
     */
    public void spawnSegment( )
    {
        int i = rand.nextInt(availableSegments.size);
        currentSements.add(availableSegments.get(i));
        availableSegments.removeIndex(i);
    }

    /**
     * adds a segment
     * @param segment
     */
    public void addSegment(CompositeLevelSegment segment)
    {
        segment.stopMovement();
        this.availableSegments.add(segment);
    }


    /**
     * if the segment is not active, clean it up and place in
     * the available array
     */
    public void cleanupSegments()
    {
        for (int i=0; i<currentSements.size; i++)
        {
            if (! currentSements.get(i).isActive())
            {
                currentSements.get(i).resetSegment();
                availableSegments.add(currentSements.get(i));
                currentSements.removeIndex(i);
            }
        }
    }
}
