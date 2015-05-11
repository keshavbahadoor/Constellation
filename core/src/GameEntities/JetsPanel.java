package GameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.constellation.game.Entity;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

import enumeration.GameEvent;
import system.Observer;
import system.Observerable;

/**
 * Created by Keshav on 5/17/2015.
 */
public class JetsPanel extends Entity implements Observer, Observerable
{
    private CompositeItem panel;
    private ImageItem jetmeter;
    private float amount = 2F;
    private Array<Observer> observers;

    /**
     * Constructor
     * @param item
     */
    public JetsPanel(CompositeItem item)
    {
        this.panel = item;
        this.observers = new Array<Observer>();
        this.jetmeter = item.getCompositeById("jetmeter").getImageById("meter");
        this.amount = amount * item.mulX;
    }



    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void onNotify(GameEvent event)
    {

         if (event == GameEvent.JETS_ACTIVE)
         {
             if (jetmeter.getWidth() > 0) {
                 jetmeter.setWidth(jetmeter.getWidth() - amount);
             }
             if (jetmeter.getWidth() <= 0) {
                 jetmeter.setWidth(0);
                 this.notifyObservers(GameEvent.JETS_EMPTY);
             }
         }
        Gdx.app.log("Received", event.name() + " from " + this.getClass().getName());
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.removeValue(observer, true);
    }

    @Override
    public void notifyObservers(GameEvent event) {
        for (int i=0; i<observers.size; i++)
        {
            observers.get(i).onNotify(event);
        }

    }

}
