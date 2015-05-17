package GameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;

import services.Services;

/**
 *  wrapper class for Component Actor
 */
public class CompositeLevelSegment extends Entity
{
    private CompositeItem segment;

    /**
     * Each segment contains a top and bottom
     * composite item
     */
    private CompositeItem top;
    private CompositeItem bot;

    private float moveSpeed = 300F;
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
        this.top = segment.getCompositeById("top");
        this.bot = segment.getCompositeById("bot");
        this.moveSpeed = moveSpeed * segment.mulX;
    }

    /**
     * Constructor accepting name
     * @param segment
     * @param name
     */
    public CompositeLevelSegment(CompositeItem segment, String name)
    {
        this(segment);
        this.name = name;
    }


    @Override
    public void draw(float delta)
    {
        super.draw(delta);
        //segment.draw(Services.getSpriteBatch(), 1F);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        if (isMoving)
        {
            // update composite X coordinate
            segment.setX((segment.getX() + this.direction * delta * this.moveSpeed) );

            // move physics bodies
            setBodyX(this.top, ((this.top.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed));
            setBodyX(this.bot, ((this.bot.getBody().getPosition().x / PhysicsBodyLoader.SCALE) + this.direction * delta * this.moveSpeed) );
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
        this.segment.setX((Gdx.graphics.getWidth() + xDiffOffset) );

        this.segment.setY(0);


        // reset bottom
        this.setBody(this.top, (Gdx.graphics.getWidth() + xDiffOffset), 0);

        // reset top
        this.setBody(this.bot, (Gdx.graphics.getWidth() + xDiffOffset), 0 );

        this.segment.setVisible(true);
        this.isMoving = true;
        this.isActive = true;
    }

    private void setBodyX(CompositeItem i, float x)
    {
        try {
            i.getBody().setTransform(x * PhysicsBodyLoader.SCALE,
                    i.getBody().getPosition().y,
                    i.getBody().getAngle());
        }
        catch (Exception e)
        {
            Gdx.app.log("ERROR", "Error : " + e.getMessage());
        }
    }

    private void setBodyY(CompositeItem i, float y)
    {
        try {
            i.getBody().setTransform(i.getBody().getPosition().x,
                    y * PhysicsBodyLoader.SCALE,
                    i.getBody().getAngle());
        }
        catch (Exception e)
        {
            Gdx.app.log("ERROR", "Error : " + e.getMessage());
        }
    }

    private void setBody(CompositeItem i, float x, float y)
    {
        try {
            i.getBody().setTransform(x * PhysicsBodyLoader.SCALE,
                    y * PhysicsBodyLoader.SCALE,
                    i.getBody().getAngle());
        }
        catch (Exception e)
        {
            Gdx.app.log("ERROR", "Error : " + e.getMessage());
        }
    }

    /**
     * returns the x value plus the width
     * @return
     */
    public float getFarX()
    {
        return segment.getX() + segment.getWidth();
    }

    public float getWidth()
    {
        return segment.getWidth();
    }

    public Actor getActor()
    {
        return this.segment;
    }

    public void setVisible(boolean visibility)
    {
        this.segment.setVisible(visibility);
    }

}
