package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

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

        CompositeItem root = sceneLoader.getRoot();

        Actor a = root.getCompositeById("healthbar");
        a.setY(a.getY() - 20);
        a.setName(a.getName() + "1");


        this.addActor(root);


        Gdx.app.log("debug", "Actors: " + root.getChildren().size);

        ///this.sceneLoader.getRoot().getCompositeById("healthbar").addScript(new EnergyBarScript());
    }
}
