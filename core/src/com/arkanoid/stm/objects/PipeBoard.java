package com.arkanoid.stm.objects;

import com.arkanoid.stm.ScreenProperties;
import com.arkanoid.stm.interfaces.GameObject;
import com.arkanoid.stm.interfaces.PipeMovement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by grzeprza on 2015-04-04.
 */
public class PipeBoard extends GameObject implements PipeMovement
{
    Rectangle pipeRectangle;
    float veolocityY;
    boolean pushBall = false;

    Texture texture;
    Sprite sprite;
    public boolean ballMoved = false;

    float pipeCenter;

    public PipeBoard(float startX)
    {

        texture= new Texture(Gdx.files.internal("core/assets/sprites/pipes/pipe.gif"));
        sprite= new Sprite(texture);
        sprite.setPosition( startX , 0);
       // sprite.setScale(sprite.getWidth()*2,sprite.getHeight());
        pipeRectangle = sprite.getBoundingRectangle();

        pipeCenter= this.getX() + sprite.getWidth()/2;

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
        pipeCenter= this.getX() + sprite.getWidth()/2;
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
        if (pipeRectangle.x <= ScreenProperties.widthFit - pipeRectangle.getWidth())
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
        texture.dispose();
    }


    public Rectangle getPipeRectangle()
    {
        return pipeRectangle;
    }

}
