package GameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.constellation.game.Entity;

import services.Services;

/**
 * Created by Keshav on 5/9/2015.
 */
public class LevelSegment extends Entity
{
    private Array<TextureRegion> textures;
    private Array<Vector2> positions;
    private int i;
    private int tileWidth = 12;
    private int tileHeight = 6;
    private int texSize = 0;

    public static String testdata = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 10, 4, 4, 4, 5, 0, 0, 0, 0, 0, 0, 26, 24, 24, 24, 24, 22, 0, 0, 0, 0, 0, 0]";


    /**
     * Constructor accepts json data and builds level
     * @param jsonData
     */
    public LevelSegment(String jsonData)
    {
        textures = new Array<TextureRegion>();
        positions = new Array<Vector2>();
        i = 0;
        texSize = Services.getResourceManager().getTextureRegion("t1").getRegionHeight();

        // build self
        buildData();

    }

    /**
     * Builds the data arrays from json input
     */
    private void buildData()
    {
        JsonValue data = new JsonReader().parse(testdata);
        int arr[] = data.asIntArray();

        int row = tileHeight-1;
        int col = 0;

        for (int i=0; i<data.size; i++)
        {
            if (arr[i] != 0 )
            {
                textures.add(Services.getResourceManager().getTextureRegion("t" + arr[i]));
                positions.add(new Vector2(col*texSize, row*texSize));
                //Gdx.app.log("sizes", "textsize: " + texSize + " row: " + row + " col: " + col + " X: " + col*texSize + " Y: " + row*texSize);
            }

            col++;

            if (col == tileWidth)
            {
                col = 0;
                row--;
            }
        }
    }

    @Override
    public void update(float delta)
    {
        for (i=0; i<positions.size; i++)
        {
            positions.get(i).x += 100 * delta;
        }
    }

    /**
     * Assumes that a sprite batch has already been created
     * @param delta
     */
    @Override
    public void draw(float delta)
    {

        for (i=0; i<textures.size; i++)
        {
            Services.getSpriteBatch().draw(textures.get(i), positions.get(i).x, positions.get(i).y);
        }
    }

    /**
     * sets the x position
     * @param x
     */
    public void setX(float x)
    {
        float diff = positions.get(0).x + x;


        for (int j=0; j<positions.size; j++)
        {
            positions.get(j).x += diff;
        }
    }

    public float getX()
    {
        return positions.get(0).x;
    }

    public Vector2 getPosition()
    {
        return positions.get(0);
    }
}
