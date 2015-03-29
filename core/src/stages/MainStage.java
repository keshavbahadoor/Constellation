package stages;

import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;

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

        this.setViewport(new StretchViewport(800, 480));
        //this.getCamera().lookAt(0.05f, 0, 0);

    }
}
