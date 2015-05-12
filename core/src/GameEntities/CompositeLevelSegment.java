package GameEntities;

import com.badlogic.gdx.Gdx;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;

/**
 * Created by Laptop on 5/9/2015.
 */
public class CompositeLevelSegment extends Entity
{
    private CompositeItem segment;
    private float moveSpeed = 400F;
    private static final float xDiffOffset = 50F;

    /**
     * positive: right
     * negative: legt
     */
    private int direction = -1;

    /**
     * Constructor
     * @param segment
     */
    public CompositeLevelSegment(CompositeItem segment)
    {
        this.segment = segment;
    }


    @Override
    public void draw(float delta)
    {
        super.draw(delta);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        if (isMoving)
        {
            // update composite X coordinate
            segment.setX(segment.getBody().getPosition().x / PhysicsBodyLoader.SCALE);

            // move physics body
            setBodyX((this.segment.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed);

        }

        // add to object pool
        if ((segment.getX() + segment.getWidth()) < -xDiffOffset)
        {
            stopMovement();
        }
    }

    /**
     * stops segment
     */
    public void stopMovement()
    {
        this.isActive = false;
        this.isMoving = false;
        this.segment.setVisible(false);
    }

    /**
     * resets the segment for endless level reuse
     * - positions it at the start of the screen
     */
    public void resetSegment()
    {
        this.setBodyX(Gdx.graphics.getWidth() + xDiffOffset);
        this.segment.setVisible(true);
        this.isMoving = true;
        this.isActive = true;
    }

    private void setBodyX(float x)
    {

        segment.getBody().setTransform(x * PhysicsBodyLoader.SCALE,
                                       segment.getBody().getPosition().y,
                                       segment.getBody().getAngle());
    }
}
