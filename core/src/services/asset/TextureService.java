package services.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

import system.TextureInterface;

/**
 * Created by Keshav on 3/28/2015.
 */
public class TextureService implements TextureInterface
{
    private static final String PATH = "textures/images/";

    public static ObjectMap imageAssets;
    public static BitmapFont mainFont;

    public TextureService()
    {
        this.load();
    }

    @Override
    public void load()
    {
        imageAssets = new ObjectMap();
        mainFont = new BitmapFont();
        try
        {
            imageAssets.put("splashscreen",	 loadTexture ("splashScreen2"));
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public Texture getTexture(String key) {
        if (imageAssets!=null)
            return (Texture) imageAssets.get(key);
        else return null;
    }

    @Override
    public TextureRegion getTextureRegion(String key) {
        return null;
    }

    @Override
    public void disposeSplashScreens() {

    }

    @Override
    public void disposeAll() {

    }

    @Override
    public Texture loadTexture(String filename)
    {
        Texture texture = null;
        texture = new Texture (Gdx.files.internal(PATH + filename + ".png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }
}
