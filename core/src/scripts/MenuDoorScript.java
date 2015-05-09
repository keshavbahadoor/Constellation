package scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LightActor;
import com.uwsoft.editor.renderer.actor.SpriteAnimation;
import com.uwsoft.editor.renderer.script.IScript;

import java.util.Random;

/**
 * Created by Keshav on 5/7/2015.
 */
public class MenuDoorScript implements IScript
{
    /**
     * door state enumeration
     */
    private enum DOOR_STATE
    {
        opening,
        opened,
        closing,
        closed
    }


    private CompositeItem item;
    private long startTime;
    private long elapsedTime;
    private LightActor light;
    private SpriteAnimation door;
    private DOOR_STATE state;


    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.light = item.getLightActorById("greenlight");
        this.door = item.getSpriteAnimationById("dooranimation");

        door.setNormalSpeed(0.3F);
        door.pause();

        this.state = DOOR_STATE.closed;
        this.startTime = TimeUtils.millis();
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);

        /**
         * Door state logic
         */
        if (state == DOOR_STATE.opening)
        {
            door.playTo(4);
            light.lightObject.setColor(1, 0, 0, 1);

            if (this.door.getCurrentFrameIndex() == 4 )
                state = DOOR_STATE.opened;
        }
        if (state == DOOR_STATE.opened)
        {
            door.pause();
        }
        if (state == DOOR_STATE.closing)
        {
            door.playTo(0);
            light.lightObject.setColor(1, 0, 0, 1);

            if (this.door.getCurrentFrameIndex() == 1 )
                state = DOOR_STATE.closed;
        }
        if (state == DOOR_STATE.closed)
        {
            door.pause();
            light.lightObject.setColor(0, 1, 0, 1);
        }


        /**
         * Door state logic control
         */
        if (elapsedTime >= 500 && elapsedTime  < 1000)
            state = DOOR_STATE.opening;
        if (elapsedTime >= 2000 && elapsedTime < 2500)
        {
            state = DOOR_STATE.closing;
        }
        if (elapsedTime > 3100)
        {
            startTime = TimeUtils.millis();
            elapsedTime = 0L;
        }
    }
}
