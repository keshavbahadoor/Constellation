package services.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

import system.LocalResourceInterface;

/**
 * Created by Keshav on 3/28/2015.
 */
public class ResourceService implements LocalResourceInterface
{
    private static final String PATH = "textures/images/";
    private static final String PARTICLE_PATH = "particles/";

    public static ObjectMap imageAssets;
    public static ObjectMap particleAssets;
    public static BitmapFont mainFont;

    public ResourceService()
    {
        this.load();
    }

    @Override
    public void load()
    {
        imageAssets = new ObjectMap();
        particleAssets = new ObjectMap();
        mainFont = new BitmapFont();
        try
        {
            // Load images
            imageAssets.put("splash1",	 loadTexture ("splash1"));
            imageAssets.put("particle",	 loadTexture ("particle"));
            imageAssets.put("pre_particle",	 loadTexture ("pre_particle"));

            // Load particles
            particleAssets.put("jet", loadParticleEffect("jet"));

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
        return null;
    }

    @Override
    public ParticleEffect getParticleEffect(String key)
    {
        if (particleAssets != null)
            return (ParticleEffect) particleAssets.get(key);
        return null;
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

    /**
     * creates and returns a particle effect
     * @param filename
     * @return
     */
    @Override
    public ParticleEffect loadParticleEffect(String filename)
    {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal(PARTICLE_PATH + filename), Gdx.files.internal("particles"));
        return particleEffect;
    }
}
