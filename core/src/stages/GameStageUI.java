package stages;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;

import scripts.EnergyBarScript;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 5/3/2015.
 */
public class GameStageUI extends Overlap2DStage
{

    public GameStageUI(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);

        this.sceneLoader.loadScene("GameScreenUI");

        this.addActor(sceneLoader.getRoot());

        this.sceneLoader.getRoot().getCompositeById("healthbar").addScript(new EnergyBarScript());
    }
}
