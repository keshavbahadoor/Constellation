package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;

import scripts.HangingLightScript;
import scripts.MenuDoorScript;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 5/6/2015.
 */
public class MainMenuBackgroundStage extends Overlap2DStage
{
    public MainMenuBackgroundStage(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);

        this.sceneLoader.loadScene("MainMenuBackground");
        this.addActor(sceneLoader.getRoot());

        this.sceneLoader.getRoot().getCompositeById("hanginglight2").addScript(new HangingLightScript());
        this.sceneLoader.getRoot().getCompositeById("doorcomposite").addScript(new MenuDoorScript());

        Gdx.app.log("stage", "main menu background");




    }
}
