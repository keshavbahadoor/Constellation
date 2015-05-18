package scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.script.IScript;

import java.lang.invoke.SerializedLambda;

import services.Services;


/**
 * Created by Keshav on 5/16/2015.
 */
public class ARInfoWindowScript implements IScript
{
    public static final float MAX_ALLOWED_COUNT = 1000F;
    public static final String PREFS_NAME = "activity_recognition";
    public static final String[] ALLOWED_ACTIVITIES = {"still", "onfoot", "running", "invehicle"};
    private CompositeItem item;
    private LabelItem label;
    private ImageItem stillBar, vehicleBar, walkingBar, runningBar;
    private float [] maxBarSize;
    private float [] currBarSize;

    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.label = item.getLabelById("label");
        this.stillBar = item.getImageById("still");
        this.vehicleBar = item.getImageById("vehicle");
        this.walkingBar = item.getImageById("foot");
        this.runningBar = item.getImageById("running");

        this.maxBarSize = new float[4];
        this.currBarSize = new float[4];

        maxBarSize[0] = stillBar.getWidth();
        maxBarSize[1] = walkingBar.getWidth();
        maxBarSize[2] = runningBar.getWidth();
        maxBarSize[3] = vehicleBar.getWidth();

        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

        for (int i=0; i<maxBarSize.length; i++)
        {
            currBarSize[i] =  (prefs.getInteger(ALLOWED_ACTIVITIES[i], 0) / MAX_ALLOWED_COUNT) * maxBarSize[i];
            if (currBarSize[i] == 0) currBarSize[i] = 2;
            if (currBarSize[i] > maxBarSize[i]) currBarSize[i] = maxBarSize[i];
        }


        if (currBarSize[0] == maxBarSize[0])
            Services.getAchievementService().unlockRest();
        if (currBarSize[1] == maxBarSize[1])
            Services.getAchievementService().unlockWalk();
        if (currBarSize[2] == maxBarSize[2])
            Services.getAchievementService().unlockRun();;
        if (currBarSize[3] == maxBarSize[3])
            Services.getAchievementService().unlockVehicle();

        stillBar.setWidth(currBarSize[0]);
        walkingBar.setWidth(currBarSize[1]);
        runningBar.setWidth(currBarSize[2]);
        vehicleBar.setWidth(currBarSize[3]);

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        //label.setText(label.getText() + " heh");
        //label.setText(getFromFile());

    }



}
