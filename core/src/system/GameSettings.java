package system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Keshav on 5/16/2015.
 */
public class GameSettings
{
    private static final String PREFS_NAME = "game_preferences";
    private static final String HIGH_SCORE = "high_score";
    private static final String FIRST_PLAY = "first_time";

    /**
     * create preferences if the game is being
     * played for the first time.
     */
    public static void initPreferences()
    {
        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
        if (! prefs.contains(FIRST_PLAY) )
        {
            prefs.putBoolean(FIRST_PLAY, true);
        }
        if (! prefs.contains(HIGH_SCORE))
        {
            prefs.putInteger(HIGH_SCORE, 0);
        }
    }

    public static int getHighScore()
    {
        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
        return prefs.getInteger(HIGH_SCORE, 0);
    }

    /**
     * updates the high score of the game
     * @param points
     */
    public static void updateHighScore(int points)
    {
        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
        int score = prefs.getInteger(HIGH_SCORE);
        if (points > score)
        {
            prefs.putInteger(HIGH_SCORE, points);
        }
    }

    /**
     * returns true if its the firs time the game is being launched
     * false if otherwise
     * @return
     */
    public static boolean isFirstTime()
    {
        return Gdx.app.getPreferences(PREFS_NAME).getBoolean(FIRST_PLAY);
    }

    public static void updateFirstTime(boolean bool)
    {
        Gdx.app.getPreferences(PREFS_NAME).putBoolean(FIRST_PLAY, bool);
    }
}
