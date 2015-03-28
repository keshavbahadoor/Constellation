package com.constellation.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.constellation.game.MainGame;

import services.GoogleServices;

/**
 * TODO : refactor google services separately
 */
public class DesktopLauncher implements GoogleServices
{
    private boolean signedInStateGPGS = false;

	public static void main (String[] arg)
    {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;
		new LwjglApplication(new MainGame(), config);
	}

    @Override
    public boolean getSignedInGPGS() {
        return signedInStateGPGS;
    }

    @Override
    public void loginGPGS()
    {
        System.out.println("LoginGPGS");
        signedInStateGPGS = true;
    }

    @Override
    public void submitScoreGPGS(int score)
    {
        System.out.println("submitScoreGPGS : "  + score);

    }

    @Override
    public void unlockAchievementGPGS(String achievementId)
    {
        System.out.println("unlockAchievementGPGS : " + achievementId);
    }

    @Override
    public void getLeaderboardGPGS()
    {
        System.out.println("getLeaderboardGPGS");
    }

    @Override
    public void getAchievementsGPGS()
    {
        System.out.println("getAchievementsGPGS");
    }
}
