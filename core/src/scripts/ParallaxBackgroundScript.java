package scripts;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by Keshav on 3/30/2015.
 */
public class ParallaxBackgroundScript implements IScript
{
    private CompositeItem item;
    private ImageItem bg1;
    private ImageItem bg2;
    private String bg1Name;
    private String bg2Name;
    private float speed;

    public ParallaxBackgroundScript(String bg1, String bg2, float speed)
    {
        this.bg1Name = bg1;
        this.bg2Name = bg2;
        this.speed = speed;
    }

    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.bg1 = item.getImageById(bg1Name);
        this.bg2 = item.getImageById(bg2Name);
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        bg1.setX(bg1.getX() - speed * delta);
        bg2.setX(bg2.getX() - speed * delta);


        resolveBackground(bg1);
        resolveBackground(bg2);

//        // correct background seam
//        if (bg2.get)
//        bg2.setX(bg1.getX() + Gdx.graphics.getWidth());

    }

    /**
     * resolves the background to the start of the screen
     * @param item
     */
    private void resolveBackground(ImageItem item)
    {
        if (item.getX() <= - Gdx.graphics.getWidth())
        {
            item.setX(Gdx.graphics.getWidth());
        }
    }
}
