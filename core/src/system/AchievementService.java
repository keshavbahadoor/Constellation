package system;

import services.Services;

/**
 * TODO: need to make this more dynamic.
 * Goal : not to rebuild APK when i want to change or make different achievements.
 *        The user should not have to constantly update the app if achievement system
 *        changes.
 */
public class AchievementService
{
    /**
     * Reviews a score and unlocks any achievements via Google Game Services
     * @param score
     */
    public static void unlockLevelAchievements(int score)
    {
        if (score >= 5  )
            unlockAchievement(1);
        if (score >= 10)
            unlockAchievement(2);
        if (score >= 15)
            unlockAchievement(3);
        if (score >= 20)
            unlockAchievement(4);
        if (score >= 25)
            unlockAchievement(5);
    }

    /**
     * Achievement IDs are hardcoded for now until i figure out how to refactor
     * this service properly to make the IDs independant of the build
     * @param archievement
     */
    private static void unlockAchievement(int archievement)
    {
        switch (archievement)
        {
            case 1:
                Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQAQ");
                break;
            case 2:
                Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQAg");
                break;
            case 3:
                Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQAw");
                break;
            case 4:
                Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQBA");
                break;
            case 5:
                Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQBQ");
                break;
        }
    }

    public static void unlockRest()
    {
        Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQCA");
    }
    public static void unlockVehicle()
    {
        Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQCQ");
    }
    public static void unlockWalk()
    {
        Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQCg");
    }
    public static void unlockRun()
    {
        Services.getGPGS().unlockAchievementGPGS("CgkIpJKzq7oBEAIQCw");
    }

}
