package services.asset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import system.TextureInterface;

/**
 * Created by Keshav on 3/28/2015.
 */
public class NullTextureService implements TextureInterface
{
    @Override
    public void load() {
        // Do nothing
    }

    @Override
    public Object get(String key) {
        // Do nothing
        return null;
    }

    @Override
    public Texture getTexture(String key) {
        // Do nothing
        return null;
    }

    @Override
    public TextureRegion getTextureRegion(String key) {
        // Do nothing
        return null;
    }

    @Override
    public void disposeSplashScreens() {
        // Do nothing

    }

    @Override
    public void disposeAll() {
        // Do nothing
    }

    @Override
    public Texture loadTexture(String filename) {
        // Do nothing
        return null;
    }
}
