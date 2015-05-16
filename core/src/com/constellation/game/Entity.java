package com.constellation.game;

import com.badlogic.gdx.math.Vector2;

import system.IDrawable;


/**
 * basic gamne entity
 */
public class Entity implements IDrawable
{
    public Vector2 position;
    protected String name = "default";
    protected boolean isDisposable = false;
    protected boolean isActive = true;
    protected boolean isMoving = true;

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

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
