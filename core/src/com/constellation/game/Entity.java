package com.constellation.game;

import com.badlogic.gdx.math.Vector2;

import system.IDrawable;


/**
 * basic gamne entity
 */
public class Entity implements IDrawable
{
    public Vector2 position;

    public Entity()
    {

    }

    public Entity(Vector2 pos)
    {
        this.position = pos;
    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void update(float delta) {

    }
}
