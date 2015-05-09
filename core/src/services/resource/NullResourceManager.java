package services.resource;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.SceneVO;
import com.uwsoft.editor.renderer.resources.IResourceLoader;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.utils.MySkin;

/**
 * Created by Keshav on 5/8/2015.
 * Null object resource manager
 */
public class NullResourceManager implements IResourceLoader, IResourceRetriever
{
    @Override
    public BitmapFont getBitmapFont(String name, int size) {
        return null;
    }

    @Override
    public void loadAtlasPack() {

    }

    @Override
    public void loadParticleEffects() {

    }

    @Override
    public void loadSpriteAnimations() {

    }

    @Override
    public void loadSpineAnimations() {

    }

    @Override
    public void loadFonts() {

    }

    @Override
    public void loadSpriterAnimations() {

    }

    @Override
    public SceneVO loadSceneVO(String sceneName) {
        return null;
    }

    @Override
    public ProjectInfoVO loadProjectVO() {
        return null;
    }

    @Override
    public TextureRegion getTextureRegion(String name) {
        return null;
    }

    @Override
    public ParticleEffect getParticleEffect(String name) {
        return null;
    }

    @Override
    public TextureAtlas getSkeletonAtlas(String name) {
        return null;
    }

    @Override
    public FileHandle getSkeletonJSON(String name) {
        return null;
    }

    @Override
    public FileHandle getSCMLFile(String name) {
        return null;
    }

    @Override
    public TextureAtlas getSpriteAnimation(String name) {
        return null;
    }

    @Override
    public MySkin getSkin() {
        return null;
    }

    @Override
    public SceneVO getSceneVO(String sceneName) {
        return null;
    }

    @Override
    public ProjectInfoVO getProjectVO() {
        return null;
    }
}
