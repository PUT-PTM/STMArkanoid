package com.arkanoid.stm.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Grzegorz on 2015-04-06.
 */
public abstract class GameObject
{
    public abstract void drawSprite ( SpriteBatch spriteBatch);

    public abstract boolean action(int type, float newY);
    public abstract void update(float delta);

    public abstract int collision(Rectangle rectangle);

    public abstract void setPosition(float x, float y);
    public abstract float getX();
    public abstract float getY();

    public abstract void destroy();

}

