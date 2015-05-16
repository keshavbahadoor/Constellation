package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

import enumeration.GameEvent;
import screens.GameScreen;
import screens.MenuScreen;
import scripts.EnergyBarScript;
import services.Services;
import services.resource.CustomResourceManager;
import system.Observer;
import system.Observerable;

/**
 * Created by Keshav on 5/3/2015.
 */
public class GameStageUI extends Overlap2DStage implements Observer, Observerable
{
    private Array<Observer> observers;
    private CompositeItem failWindow;
    private CompositeItem restartBtnUI;
    private CompositeItem homeBtnUI;


    public GameStageUI(CustomResourceManager rm)
    {
        super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));
        this.observers = new Array<Observer>();
        this.initSceneLoader(rm);
        sceneLoader.setResolution(rm.currentResolution.name);
        this.sceneLoader.loadScene("GameScreenUI");
        this.addActor(sceneLoader.getRoot());

        failWindow = sceneLoader.getRoot().getCompositeById("failwindow");
        restartBtnUI = sceneLoader.getRoot().getCompositeById("restartBtn");
        homeBtnUI = sceneLoader.getRoot().getCompositeById("homeBtn");

        setFailVisibility(false);

        SimpleButtonScript restartButton = SimpleButtonScript.selfInit(restartBtnUI);
        restartButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                // Restart game
                // TODO : restart more dynamically - ie - dont just load back gamescreen
                Services.getGameObject().setScreen(new GameScreen(Services.getGameObject()));
            }
        });

        SimpleButtonScript homeButton = SimpleButtonScript.selfInit(homeBtnUI);
        homeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                // Main Menu
                Services.getGameObject().setScreen(new MenuScreen(Services.getGameObject()));
            }
        });
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void onNotify( GameEvent event)
    {
        switch (event)
        {
            case PLAYER_DIED:  setFailVisibility(true);
                               Gdx.input.setInputProcessor(this);
                break;
            case GAME_PAUSE:
                break;
            case GAME_RESUMED:
        }

        Gdx.app.log("Received", event.name() + " from " + this.getClass().getName());
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.removeValue(observer, true);
    }

    @Override
    public void notifyObservers(GameEvent event)
    {
        for (int i=0; i<observers.size; i++)
        {
            observers.get(i).onNotify(event);
        }
    }

    private void setFailVisibility(boolean visibility)
    {
        failWindow.setVisible(visibility);
        restartBtnUI.setVisible(visibility);
        homeBtnUI.setVisible(visibility);
    }
}
