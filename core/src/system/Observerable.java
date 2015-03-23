package system;

import com.constellation.game.Entity;

import enumeration.GameEvent;

/**
 * Created by Keshav on 3/22/2015.
 */
public interface Observerable
{
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(GameEvent event);
}
