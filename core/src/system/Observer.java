package system;

import com.constellation.game.Entity;

import enumeration.GameEvent;

/**
 * Created by Keshav on 3/22/2015.
 */
public interface Observer
{
    public void onNotify(  GameEvent event);
}
