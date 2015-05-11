package GameEntities;

import com.badlogic.gdx.Gdx;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

import enumeration.GameEvent;
import system.IDrawable;
import system.Observer;
import system.Observerable;

/**
 * Created by Keshav on 5/17/2015.
 */
public class ScorePanel extends Entity implements Observer, Observerable
{
    private CompositeItem panel;
    private LabelItem score;
    private int currScore = 0;

    /**
     * Constructor
     * @param item
     */
    public ScorePanel(CompositeItem item)
    {
        this.panel = item;
        this.score = panel.getLabelById("score");

        score.setText(""+currScore);
    }


    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void onNotify(GameEvent event)
    {
        switch (event)
        {
            case PASSED_SEGMENT: score.setText(""+currScore++);
                break;
        }

        Gdx.app.log("Received", event.name() + " from " + this.getClass().getName());

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(GameEvent event) {

    }

    public int getScore() {
        return currScore;
    }
}
