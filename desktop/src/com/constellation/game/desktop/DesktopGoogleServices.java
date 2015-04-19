package com.constellation.game.desktop;

import services.GoogleGameServices;

/**
 * Created by Keshav on 4/5/2015.
 */
public class DesktopGoogleServices implements GoogleGameServices
{
    private boolean signedInStateGPGS = false;
    @Override
    public boolean getSignedInGPGS() {
        return false;
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
