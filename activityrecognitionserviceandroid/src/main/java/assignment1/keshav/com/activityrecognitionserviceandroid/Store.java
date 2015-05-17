package assignment1.keshav.com.activityrecognitionserviceandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.DetectedActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by Keshav on 4/24/2015.
 */
public class Store
{
    public static Context context;
    public static final String FILENAME = "data.dat";
    public static final String PREFS_NAME = "activity_recognition";
    public static final String[] ALLOWED_ACTIVITIES = {"still", "onfoot", "running", "invehicle"};


    /**
     * Updates the activity record on file
     *  Data format:
     *      string still : { totaltime, count, lastupdatedtime }
     *      string onfoot : {meters, count, lastupdatedtime}
     *      string running : {meters, count, lastupdatedtime}
     *      string invehicle : {meters, count, lastupdatedtime}
     *
     * @param detectedActivity
     * @param confidence
     */
    public static void addActivityRecord(int detectedActivity, int confidence)
    {
        if (context != null)
        {
            try
            {
                if (!fileExists())
                {
                    createEmptyFile();
                }
                switch (detectedActivity)
                {
                    case DetectedActivity.IN_VEHICLE:
                         createMovementRecord("invehicle");
                        break;
                    case DetectedActivity.WALKING:
                         createMovementRecord("onfoot");
                        break;
                    case DetectedActivity.ON_FOOT:
                         createMovementRecord("onfoot");
                        break;
                    case DetectedActivity.RUNNING:
                         createMovementRecord("running");
                        break;
                    case DetectedActivity.STILL:
                         createMovementRecord("still");
                }

            }
            catch (Exception ex)
            {
                Log.d("ACTIVITYRECORD", "Error adding record: " + ex.getMessage());
            }
        }
    }

    /**
     * Creates an empty record structure and stores it to file
     */
    private static void createEmptyFile()
    {
        try
        {
            JSONObject still = new JSONObject();
            JSONObject onfoot = new JSONObject();
            JSONObject running = new JSONObject();
            JSONObject invehicle = new JSONObject();
            JSONObject obj = new JSONObject();

            Integer zero = new Integer(0);
            String timestamp = getTimeStamp();

            still.put("meters", zero);
            still.put("count", zero);
            still.put("lastupdated", timestamp);

            onfoot.put("meters", zero);
            onfoot.put("count", zero);
            onfoot.put("lastupdated", timestamp);

            running.put("meters", zero);
            running.put("count", zero);
            running.put("lastupdated", timestamp);

            invehicle.put("meters", zero);
            invehicle.put("count", zero);
            invehicle.put("lastupdated", timestamp);

            obj.put("still", still);
            obj.put("onfoot", onfoot);
            obj.put("running", running);
            obj.put("invehicle", invehicle);

            write(obj.toString());
        }
        catch (Exception ex)
        {
            Log.d("ACTIVITYRECORD", "Error creating empty file: " + ex.getMessage());
        }
    }

    /**
     * Adds a movement record
     * @param key
     */
    private static void createMovementRecord(String key)
    {
        try
        {
            JSONObject obj = new JSONObject(read());
            JSONObject activity = obj.getJSONObject(key);

            // update data
            int count = 0;
            count = activity.getInt("count");
            activity.put("count", count + 1);
            activity.put("lastupdated", getTimeStamp());

            obj.put(key, activity);
            write(obj.toString());
        }
        catch (Exception ex)
        {
            Log.d("ACTIVITYRECORD", "Error creating record: " + ex.getMessage());
        }
    }

    /**
     * appends to file
     * @param str
     */
    public static void append(String str)
    {
        if (context!=null)
        {
            try
            {
                FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_APPEND);
                fos.write(str.getBytes());
                fos.close();
            }
            catch (Exception ex)
            {
                Toast.makeText(context, "IO Error: " + ex.getMessage(), Toast.LENGTH_LONG);
            }
        }
    }

    /**
     * Writes to file
     * overrites any existing data
     * @param str
     */
    public static void write(String str)
    {
        try
        {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(encodeString(str).getBytes());
            fos.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "IO Error: " + ex.getMessage(), Toast.LENGTH_LONG);
        }
    }

    /**
     * Read from file
     * @return
     */
    public static String read()
    {
        if (context==null)
            return "Context object is null";

        try
        {
            int ch;
            StringBuffer filecontent = new StringBuffer("");
            FileInputStream fis = context.openFileInput(FILENAME);
            while ((ch = fis.read()) != -1)
            {
                filecontent.append((char)ch);
            }
            return decodeString(new String(filecontent));
        }
        catch (Exception ex)
        {
            return "Error reading file: " + ex.getMessage();
        }
    }

    /**
     * Returns true if the file exists,
     *  false if otherwise
     * @return
     */
    public static boolean fileExists()
    {
        File file = context.getFileStreamPath(FILENAME);
        if (file == null ||  ! file.exists() )
            return false;

        return true;
    }

    /**
     * Encode string
     * @param str
     * @return
     */
    private static String encodeString(String str)
    {
        return str;
    }

    /**
     * Decode string
     * @param str
     * @return
     */
    private static String decodeString (String str)
    {
        return str;
    }

    /**
     * Builds and returns current timestamp
     * @return
     */
    private static String getTimeStamp()
    {
        return android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();
    }

    /**
     * Resets the data by recreating empty file
     */
    public static void resetData()
    {
        createEmptyFile();
    }

    /**
     * Converts the JSON data to Android Perferences storage
     */
    public static void convertToPreferences()
    {
        try
        {
            if (!fileExists())
            {
                createEmptyFile();
            }

            int count = 0;

            // Get json
            JSONObject obj = new JSONObject(read());

            // get prefs editor
            SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();

            // Build prefs
            for (int i=0; i<ALLOWED_ACTIVITIES.length; i++)
            {
                JSONObject activity = obj.getJSONObject(ALLOWED_ACTIVITIES[i]);
                count = activity.getInt("count");
                editor.putInt(ALLOWED_ACTIVITIES[i], count);
            }

            // Commit changes
            editor.commit();

            Log.d("ACTIVITYRECORD", "Convert to preferences completed");

        }
        catch (Exception ex)
        {
            Log.d("ACTIVITYRECORD", "Error converting to perferences: " + ex.getMessage());
        }
    }


}
