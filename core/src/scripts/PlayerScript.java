package scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ParticleActor;
import com.uwsoft.editor.renderer.actor.ParticleItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

import services.Services;

/**
 * Created by Laptop on 5/9/2015.
 */
public class PlayerScript implements IScript
{
    private CompositeItem item;
    private final static float ANGULAR_DAMPING = 40f;
    private final static float IMPULSE_FORCE = Gdx.graphics.getWidth();
    private float xForce = 0;
    private float yForce = 0;
    private float objX = 0;
    private float objY = 0;

    private ParticleItem particleXleft, particleXright;
    private ParticleItem particleYup, particleYdown;




    @Override
    public void init(CompositeItem item)
    {
        this.item = item;
        this.particleXleft = item.getParticleById("particleXleft");
        this.particleXright = item.getParticleById("particleXright");
        this.particleYup = item.getParticleById("particleYup");
        this.particleYdown = item.getParticleById("particleYdown");

        particleXleft.forceContinuous();
        particleXright.forceContinuous();
        particleYup.forceContinuous();
        particleYdown.forceContinuous();

        particleXleft.setVisible(false);
        particleXright.setVisible(false);
        particleYup.setVisible(false);
        particleYdown.setVisible(false);

        //item.getBody().setFixedRotation(true);

        //item.getBody().setAngularDamping(ANGULAR_DAMPING);
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void act(float delta)
    {
        objX = item.getBody().getPosition().x / PhysicsBodyLoader.SCALE;
        objY = item.getBody().getPosition().y / PhysicsBodyLoader.SCALE;



        /**
         * Handle user input
         */
        if (Gdx.input.isTouched())
        {
            if (Gdx.input.getX() > objX)
            {
                xForce = -IMPULSE_FORCE;
                particleXleft.setVisible(false);
                particleXright.setVisible(true);
            }
            if (Gdx.input.getX() < objX)
            {
                xForce = IMPULSE_FORCE;
                particleXright.setVisible(false);
                particleXleft.setVisible(true);
            }
            if (Gdx.input.getY() < objY)
            {
                yForce = -IMPULSE_FORCE;
                particleYup.setVisible(true);
                particleYdown.setVisible(false);
            }
            if (Gdx.input.getY() > objY)
            {
                yForce = IMPULSE_FORCE;
                particleYdown.setVisible(true);
                particleYup.setVisible(false);
            }

            // Apply force in appropriate direction
            item.getBody().applyForceToCenter(xForce, yForce, true);

        }

        /**
         * hide particles when not pressed
         */
        if (Services.inputService.isTouchUp)
        {
            particleXleft.setVisible(false);
            particleXright.setVisible(false);
            particleYup.setVisible(false);
            particleYdown.setVisible(false);
        }


    }

}
