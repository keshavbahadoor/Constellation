package com.constellation.game.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.constellation.game.MainGame;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

import assignment1.keshav.com.activityrecognitionserviceandroid.ActivityRecognitionService;
import services.GoogleGameServices;

/**
 * Main Android Activity
 *
 * TODO : create separate implementation for gamehelperlistener
 */
public class MainActivity extends AndroidApplication implements GameHelper.GameHelperListener, GoogleGameServices
{
    private GameHelper gameHelper;

	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainGame(this), config);
        if (gameHelper == null)
        {
            this.gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
            this.gameHelper.enableDebugLog(true);
        }
        gameHelper.setup(this);
	}

    @Override
    protected void onStart()
    {
        super.onStart();
        this.gameHelper.onStart(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.gameHelper.onStop();

        /**
         * This is where we will start Google Activity Recognition service
         * to continue running in the background as the user goes about
         * life
         */
        Log.d("On Stop", "start AR Service");
        Intent arServiceIntent = new Intent(this, ActivityRecognitionService.class);
        this.startService(arServiceIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        this.gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSignInFailed()
    {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public boolean getSignedInGPGS()
    {
        return this.gameHelper.isSignedIn();
    }

    @Override
    public void loginGPGS()
    {
        try
        {
            this.runOnUiThread(new Runnable(){
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (final Exception ex)
        {

        }
    }

    @Override
    public void submitScoreGPGS(int score)
    {
        Games.Leaderboards.submitScore(this.gameHelper.getApiClient(), "" + R.string.app_id, score);
    }

    @Override
    public void unlockAchievementGPGS(String achievementId)
    {
        Games.Achievements.unlock(this.gameHelper.getApiClient(), achievementId);
    }

    @Override
    public void getLeaderboardGPGS()
    {
        if (this.gameHelper.isSignedIn())
        {
            this.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(this.gameHelper.getApiClient(), "" + R.string.app_id), 100);
        }
        else if ( ! gameHelper.isConnecting())
        {
            this.loginGPGS();
        }
    }

    @Override
    public void getAchievementsGPGS()
    {
        if (this.gameHelper.isSignedIn())
        {
            this.startActivityForResult(Games.Achievements.getAchievementsIntent(this.gameHelper.getApiClient()), 100);
        }
        else if ( ! gameHelper.isConnecting())
        {
            this.loginGPGS();
        }
    }


}
