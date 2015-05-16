package scripts;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Implementation changed. Level Segment Script has been deprecated due to
 * new level segments having multiple composite components, with multiple
 * physics bodies.
 */
@Deprecated
public class LevelSegmentScript implements IScript
{
    private boolean isMoving = true;
    private CompositeItem item;
    private float moveSpeed = Gdx.graphics.getWidth();
    private static final float xDiffOffset = 50F;

    /**
     * positive: right
     * negative: legt
     */
    private int direction = -1;


    @Override
    public void init(CompositeItem item)
    {
        this.item = item;

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        if (isMoving)
        {
            // update composite X coordinate
            item.setX(item.getBody().getPosition().x / PhysicsBodyLoader.SCALE);

            // move physics body
            setBodyX((this.item.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed);

        }

        // add to object pool
        if ((item.getX() + item.getWidth()) < -xDiffOffset)
        {
            setBodyX(800);
        }

    }

    /**
     * stops segment
     */
    public void stopMovement()
    {
        this.isMoving = false;
        this.item.setVisible(false);
    }

    /**
     * resets the segment for endless level reuse
     * - positions it at the start of the screen
     */
    public void resetSegment()
    {
        this.setBodyX(Gdx.graphics.getWidth() + xDiffOffset);
        this.item.setVisible(true);
        this.isMoving = true;
    }

    private void setBodyX(float x)
    {
        item.getBody().setTransform(x * PhysicsBodyLoader.SCALE, item.getBody().getPosition().y, item.getBody().getAngle());
    }
}
