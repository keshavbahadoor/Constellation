package scripts;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.IScript;


/**
 * Created by Keshav on 3/23/2015.
 */
public class SceneScript implements IScript
{
    private CompositeItem item;

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


    }
}
