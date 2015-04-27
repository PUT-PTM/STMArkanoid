package com.arkanoid.stm.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.arkanoid.stm.game.GameObject;
import com.arkanoid.stm.game.PipeMovement;

/**
 * Created by Grzegorz on 2015-04-04.
 */
public class PipeBoard extends GameObject implements PipeMovement
{


    Rectangle pipeRectangle;
    float veolocityY;
    boolean pushBall = false;

    Texture texture;
    Sprite sprite;
    public boolean ballMoved = false;

    public PipeBoard()
    {
        texture= new Texture(Gdx.files.internal("core/assets/sprites/pipes/pipe.gif"));
        sprite= new Sprite(texture);

        pipeRectangle = sprite.getBoundingRectangle();
        sprite.setPosition( this.getX(), this.getY());
        veolocityY = 0;
    }

    public void setPosition(float X, float Y)
    {
        pipeRectangle.x = X;
        pipeRectangle.y = Y;
        sprite.setPosition(X,Y);
    }

    public int collision(Rectangle object)
    {
        if(pipeRectangle.overlaps(object))
        {
            pushBall= false;
            return 1;
        }
        else return -1;
    }

    public boolean action(int type, float newY)
    {

        if ( type == 1 && !ballMoved)
        {
            veolocityY = 0;
            setPosition( getX() , newY );
            return false;
        }
        return true;
    }

    public void update( float delta)
    {
        if(pushBall)
        {
            veolocityY -= (20 * delta);
            pipeRectangle.y += veolocityY;
        }
        //System.out.println("Y= " + pipeRectangle.y);

        sprite.setPosition(getX(), getY());
    }

    public void drawSprite( SpriteBatch spriteBatch)
    {
        sprite.draw(spriteBatch);
    }

    public void moveLeft(float delta)
    {
        if(pipeRectangle.x >= 0)
             pipeRectangle.x -= (200 * delta);
        sprite.setPosition(pipeRectangle.x, pipeRectangle.y);

     //   System.out.println(" LEFT , x = " + pipeRectangle.x);
    }

    public void moveRight(float delta)
    {
        if (pipeRectangle.x <= 600 - pipeRectangle.getWidth())
            pipeRectangle.x += (200 * delta);
        sprite.setPosition(pipeRectangle.x, pipeRectangle.y);
      //   System.out.println(" RIGHT , x = " + pipeRectangle.x);
    }

    public void pushBall()
    {
        pushBall = true;
        veolocityY = 3;
    }

    public float getX()
    {
        return pipeRectangle.x;
    }

    public float getY()
    {
        return pipeRectangle.y;
    }

    @Override
    public void destroy() {
    }


    public Rectangle getPipeRectangle()
    {
        return pipeRectangle;
    }

}
