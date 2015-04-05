package stages;

import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.SceneLoader;

import scripts.ParallaxBackgroundScript;
import scripts.SceneScript;
import scripts.Windmill;
import system.CustomResourceManager;

/**
 * Created by Keshav on 3/23/2015.
 */
public class MainStage extends Overlap2DStage
{
    public MainStage(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));

        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);

        this.sceneLoader.loadScene("MainScene");

        this.addActor(sceneLoader.getRoot());

        //this.sceneLoader.getRoot().getCompositeById("windmill").addScript(new Windmill());
        this.sceneLoader.getRoot().getCompositeById("background").addScript(new ParallaxBackgroundScript("bg1", "bg2", 40));
        this.sceneLoader.getRoot().getCompositeById("wall").addScript(new ParallaxBackgroundScript("wall1", "wall2", 200));
        this.sceneLoader.getRoot().getCompositeById("floor").addScript(new ParallaxBackgroundScript("floor1", "floor2", 800));

    }
}
