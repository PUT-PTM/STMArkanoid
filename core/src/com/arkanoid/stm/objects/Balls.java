package com.arkanoid.stm.objects;

import com.arkanoid.stm.interfaces.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by grzeprza on 2015-04-07.
 */
public class Balls extends GameObject {

    PipeBoard pipeBoard;
    float ballCenter_X, ballCenter_Y;

    private Texture texture;
    private Sprite sprite;

    private Sound bounce_1, bounce_2, deathSound;
    boolean playFirst=false;

    public Rectangle vertical,horizontal;   ShapeRenderer shapeRenderer;
    public Circle ball_circle;
    protected float velocityY=0, velocityX = 0;

    public Balls(PipeBoard pipeBoard)
    {
        this.pipeBoard = pipeBoard;

        texture = new Texture(Gdx.files.internal("core/assets/sprites/balls/white_ball.gif"));
        sprite = new Sprite(texture);

        bounce_1= Gdx.audio.newSound(Gdx.files.internal("core/assets/music/balls/Blip1.mp3"));
        bounce_2= Gdx.audio.newSound(Gdx.files.internal("core/assets/music/balls/Blip2.mp3"));

        deathSound= Gdx.audio.newSound(Gdx.files.internal("core/assets/music/themes/death/dieLikePacman.mp3"));

        ballCenter_Y = pipeBoard.texture.getHeight()* 5/4 + pipeBoard.getY();
        ballCenter_X = pipeBoard.texture.getWidth()/2-this.texture.getWidth()/2 + pipeBoard.getX();
        sprite.setPosition(ballCenter_X, ballCenter_Y);

        //ball collision borders
        vertical  = new Rectangle(sprite.getX()+ (float) (0.1)*sprite.getWidth(), sprite.getY()                                 ,sprite.getWidth()*(float) (0.8), sprite.getHeight());
        horizontal= new Rectangle(sprite.getX()                                  ,sprite.getY()+(float) (0.1)*sprite.getHeight(),sprite.getWidth()               , sprite.getHeight()*(float) (0.8) );

        shapeRenderer= new ShapeRenderer();
    }

    @Override
    public void drawSprite(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);

    }

    @Override
    /**Defines balls behavior*/
    public boolean action(int type, float newY)
    {

        if(type == 1)
        {
            velocityY = 5;
            float tempVelX= (pipeBoard.pipeCenter-(ballCenter_X+ this.texture.getWidth()/2)) /10;
            //System.out.println(" | ball-pipe= "+ (ballCenter_X-pipeBoard.pipeCenter+this.texture.getWidth()/2));
            if(pipeBoard.pipeCenter-(ballCenter_X+ this.texture.getWidth()/2) > -2 && pipeBoard.pipeCenter-(ballCenter_X+ this.texture.getWidth()/2) <2)
                velocityX=0;
            else
            velocityX= -tempVelX;

            System.out.println(velocityX);
        }
        if(type == 2)
        {
            bounceY();
        }
        if(type == 3)
        {
          bounceX();
        }
        if(type==4)
        {
            deathSound.play();
            velocityX=0;
            velocityY=0;
            ballCenter_Y = pipeBoard.texture.getHeight()* 5/4 + pipeBoard.getY();
            pipeBoard.pushBall=false;
            pipeBoard.ballMoved=false;

        }

        return true;
    }

    @Override
    /**Updates position*/
    public void update(float delta)
    {
        //Code below shows balls collision rectangle

       shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(vertical.getX(), vertical.getY(), vertical.getWidth(), vertical.getHeight());
       shapeRenderer.rect(horizontal.getX(),horizontal.getY(),horizontal.getWidth(),horizontal.getHeight());
       // shapeRenderer.rect(ballCenter_X+ this.texture.getWidth()/2,this.getY(),1,2*this.sprite.getHeight());
        shapeRenderer.circle(sprite.getX(), sprite.getY(), sprite.getHeight());
        shapeRenderer.end();

        if(!pipeBoard.ballMoved)
        {
            ballCenter_X = pipeBoard.texture.getWidth()/2-this.texture.getWidth() / 2 + pipeBoard.getX();
        }

        setPosition(ballCenter_X += velocityX, ballCenter_Y+= velocityY);
    }

    @Override
    /**Detects collision -> returns 1. */
         public int collision(Rectangle rectangle) {
        if(vertical.overlaps(rectangle) || horizontal.overlaps(rectangle))
        {
            if(playFirst)
            {
                bounce_1.play();
                playFirst=false;
            }
            else
            {
                bounce_2.play();
                playFirst=true;
            }
                return 1;
        }
        return -1;
    }

    @Override
    /**Sets position of sprite and moves collision rectangle*/
    public void setPosition(float x, float y) {
        vertical.setPosition(x + (float) (0.1)*sprite.getWidth() , y);
        horizontal.setPosition(x, y +(float) (0.1) * sprite.getHeight());
        sprite.setPosition(x, y);
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public void destroy() {
        bounce_2.dispose();
        bounce_1.dispose();
        texture.dispose();
    }

    public float setVelX(float newVelX)
    {
        return this.velocityX= newVelX;
    }
    public float setVelY(float newVelY)
    {
        return this.velocityY= newVelY;
    }
    public void bounceX()
    {
        setVelX(-(this.velocityX));
    }
    public void bounceY()
    {
        setVelY(-(this.velocityY));
    }
}
