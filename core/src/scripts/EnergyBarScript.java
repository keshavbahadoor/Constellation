package scripts;

import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by Keshav on 5/3/2015.
 */
public class EnergyBarScript  implements IScript
{
    private CompositeItem item;
    private ImageItem bar;

    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.bar = item.getImageById("healthbar");
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        bar.setWidth(bar.getWidth() - 10 * delta);
    }

}
