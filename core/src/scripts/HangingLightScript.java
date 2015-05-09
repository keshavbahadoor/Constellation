package scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LightActor;
import com.uwsoft.editor.renderer.script.IScript;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import box2dLight.Light;

/**
 * Created by Keshav on 5/6/2015.
 */
public class HangingLightScript implements IScript
{
    private CompositeItem item;
    private ImageItem lightholder;
    private LightActor light;
    private long startTime;
    private long elapsedTime;
    private Random rand;

    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.light = item.getLightActorById("light");
        this.lightholder = item.getImageById("lightholder");
        this.startTime = TimeUtils.millis();
        this.rand = new Random();


        light.lightObject.setActive(true);


    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);

        // Flicker light
        if (elapsedTime > (rand.nextInt(2000) + 100) )
        {
            light.lightObject.setActive(!light.lightObject.isActive());
            startTime = TimeUtils.millis();
            elapsedTime = 0L;
        }


    }
}
