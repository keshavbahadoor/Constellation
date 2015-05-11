package GameEntities;

import com.constellation.game.Entity;

import enumeration.GameEvent;
import system.Observer;
import system.Observerable;

/**
 * Created by Keshav on 5/17/2015.
 */
public class NullEntity extends Entity implements Observer, Observerable
{
    public NullEntity()
    {
        this.name = "nullentity";
        this.isActive = false;
        this.isMoving = false;
    }

    @Override
    public void draw(float delta) {
        super.draw(delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void onNotify(GameEvent event) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(GameEvent event) {

    }
}
