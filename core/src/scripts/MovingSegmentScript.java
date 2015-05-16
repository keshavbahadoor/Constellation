package scripts;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by Keshav on 5/13/2015.
 */
@Deprecated
public class MovingSegmentScript implements IScript
{
    private boolean isMoving = true;
    private boolean isActive = true;
    private CompositeItem item;
    private float moveSpeed = 20;
    private static final float xDiffOffset = 200F;

    /**
     * Each segment contains a top and bottom
     * composite item
     */
    private CompositeItem bottom;
    private CompositeItem top;

    /**
     * positive: right
     * negative: legt
     */
    private int direction = -1;


    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.bottom = item.getCompositeById("bot");
        this.top = item.getCompositeById("top");

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
            item.setX(item.getX() + this.direction * delta * this.moveSpeed);

            // move physics bodies
            setBodyX(this.top, (this.top.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed);
            setBodyX(this.bottom, (this.bottom.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed);
        }

        // add to object pool
        if ((item.getX() + item.getWidth()) < -xDiffOffset)
        {
            //setBodyX(800);
            stopMovement();
        }
    }

    /**
     * stops segment
     */
    public void stopMovement()
    {
        this.isMoving = false;
        this.isActive = false;
        this.item.setVisible(false);
    }

    /**
     * resets the segment for endless level reuse
     * - positions it at the start of the screen
     */
    public void resetSegment()
    {
        // reset bottom
        this.setBodyX(this.top, Gdx.graphics.getWidth() + xDiffOffset);

        // reset top
        this.setBodyX(this.bottom, Gdx.graphics.getWidth() + xDiffOffset);

        this.item.setVisible(true);
        this.isMoving = true;
        this.isActive = true;
    }

    private void setBodyX(CompositeItem i, float x)
    {
        i.getBody().setTransform(x * PhysicsBodyLoader.SCALE,
                i.getBody().getPosition().y,
                i.getBody().getAngle());
    }



}
