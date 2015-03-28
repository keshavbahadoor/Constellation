package stages;

import com.uwsoft.editor.renderer.Overlap2DStage;

import scripts.SceneScript;
import scripts.Windmill;

/**
 * Created by Keshav on 3/23/2015.
 */
public class MainStage extends Overlap2DStage
{
    public MainStage()
    {
        this.initSceneLoader();

        this.sceneLoader.loadScene("MainScene");

        this.addActor(sceneLoader.getRoot());

        this.sceneLoader.getRoot().getCompositeById("windmill").addScript(new Windmill());
    }
}
