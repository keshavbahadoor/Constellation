package system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Keshav on 3/28/2015.
 */
public interface LocalResourceInterface
{
    public void load();
    public Object get(String key);
    public Texture getTexture(String key);
    public TextureRegion getTextureRegion(String key);
    public void disposeSplashScreens();
    public void disposeAll();
    public Texture loadTexture(String filename);
    public ParticleEffect loadParticleEffect(String filename);
    public ParticleEffect getParticleEffect(String key);

}
