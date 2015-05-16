package system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * A variation of the Object Pool design pattern that utilizes
 * a hash map instead of an array store.
 *
 * - used for generating Library Actors
 */
public class CompositeItemObjectPool
{
    private static final String TAG = "CompositeItemPool";

    /**
     * Pool hash map
     */
    private ObjectMap<String, Entity> pool;
    private SceneLoader loader;


    /**
     * Constructor
     */
    public CompositeItemObjectPool(SceneLoader sceneLoader)
    {
        this.pool = new ObjectMap<String, Entity>();
        this.loader = sceneLoader;
    }

    /**
     * Adds composite item to pool
     * @param e
     * @param key
     */
    public void addEntity(Entity e, String key)
    {
        this.pool.put(key, e);
    }



    /**
     * Gets a composite item from the pool
     * - creates and returns a composite item
     *   if one is not contained in the pool
     * @param key
     * @return
     */
    public Entity getEntity(String key)
    {
        try
        {
            if (!pool.containsKey(key))
            {
                return null;
            }
            Entity item = pool.get(key);
            pool.remove(key);
            return item;
        }
        catch (Exception ex)
        {
            Gdx.app.log(TAG, "Error getting composite item: " + ex.getMessage());
            return null;
        }
    }

    /**
     * returns a composite item at position x, y
     * @param key
     * @param x
     * @param y
     * @return
     */
    public Entity getEntity(String key, float x, float y)
    {
        Entity ci = this.getEntity(key);
        if (ci == null) return null;
        return ci;
    }

    /**
     *  pool count
     * @return
     */
    public int getSize()
    {
        return this.pool.size;
    }


}
