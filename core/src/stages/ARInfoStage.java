package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import screens.MenuScreen;
import scripts.ARInfoWindowScript;
import services.Services;
import services.resource.CustomResourceManager;

/**
 * Created by Keshav on 5/16/2015.
 */
public class ARInfoStage extends Overlap2DStage
{
    public ARInfoStage(CustomResourceManager rm)
    {



        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));
        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);
        this.sceneLoader.loadScene("ARScene");
        this.addActor(sceneLoader.getRoot());

        this.sceneLoader.getRoot().getCompositeById("infobox").addScript(new ARInfoWindowScript());

        SimpleButtonScript backBtn = SimpleButtonScript.selfInit(this.sceneLoader.getRoot().getCompositeById("backBtn"));
        backBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                Services.getGameObject().setScreen(new MenuScreen(Services.getGameObject()));
            }
        });

        Gdx.input.setInputProcessor(this);
    }
}
