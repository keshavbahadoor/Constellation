package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;


import screens.ARInfoScreen;
import screens.GameScreen;
import services.Services;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 4/19/2015.
 */
public class MainMenuStage extends Overlap2DStage
{

    public MainMenuStage(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);

        this.sceneLoader.loadScene("MainMenu");
        this.addActor(sceneLoader.getRoot());

        Gdx.app.log("stage", "main menu");

        Gdx.input.setInputProcessor(this);

        SimpleButtonScript startButton = SimpleButtonScript.selfInit(this.sceneLoader.getRoot().getCompositeById("goBtn"));
        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                // Do stuff here
                Gdx.app.log("button", "start clicked");
                Services.getGameObject().setScreen(new GameScreen(Services.getGameObject()));
            }
        });

        SimpleButtonScript arButton = SimpleButtonScript.selfInit(this.sceneLoader.getRoot().getCompositeById("arBtn"));
        arButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){

                // Do stuff here
                Gdx.app.log("button", "AR clicked");
                Services.getGameObject().setScreen(new ARInfoScreen(Services.getGameObject()));
            }
        });

        SimpleButtonScript optionsButton = SimpleButtonScript.selfInit(this.sceneLoader.getRoot().getCompositeById("settingsBtn"));
        optionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                // Do stuff here
                Gdx.app.log("button", "optionsButton clicked");
                Services.getGPGS().getAchievementsGPGS();
            }
        });

        SimpleButtonScript leaderboardButton = SimpleButtonScript.selfInit(this.sceneLoader.getRoot().getCompositeById("leaderboardBtn"));
        leaderboardButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                // Do stuff here
                Gdx.app.log("button", "leaderboardButton clicked");
                Services.getGPGS().getLeaderboardGPGS();

            }
        });




    }

}
