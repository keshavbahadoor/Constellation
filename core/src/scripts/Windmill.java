package scripts;

import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by Keshav on 3/28/2015.
 */
public class Windmill implements IScript
{
    private ImageItem fan;
    private CompositeItem item;

    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.fan = item.getImageById("fan");
        this.fan.setOrigin(fan.getX() + fan.getWidth()/2, fan.getY() -  fan.getHeight()/2);

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        fan.setRotation(fan.getRotation() + delta * 80);

    }
}
