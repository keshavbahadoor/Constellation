package system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Laptop on 5/10/2015.
 */
public class InputService implements InputProcessor
{
    public float x = 0;
    public float y = 0;
    public boolean isTouchDown = false;
    public boolean isTouchUp = false;


    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("touchdown", "true");
        this.x = screenX;
        this.y = screenY;
        this.isTouchDown = true;
        this.isTouchUp = false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("touchUp", "true");
        this.x = screenX;
        this.y = screenY;
        this.isTouchUp = true;
        this.isTouchDown = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
