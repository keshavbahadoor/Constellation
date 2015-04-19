package services;

/**
 * Created by Keshav on 3/22/2015.
 */
public interface GoogleGameServices
{
    public boolean getSignedInGPGS();
    public void loginGPGS();
    public void submitScoreGPGS(int score);
    public void unlockAchievementGPGS(String achievementId);
    public void getLeaderboardGPGS();
    public void getAchievementsGPGS();
}
