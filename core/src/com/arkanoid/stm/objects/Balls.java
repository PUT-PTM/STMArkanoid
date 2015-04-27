package com.arkanoid.stm.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.arkanoid.stm.game.GameObject;

/**
 * Created by Grzegorz on 2015-04-07.
 */
public class Balls extends GameObject {

    PipeBoard pipeBoard;
    float ballCenter_X, ballCenter_Y;

    private Texture texture;
    private Sprite sprite;

    private Rectangle bottom, up, left, right;

    public Balls(PipeBoard pipeBoard)
    {
        this.pipeBoard = pipeBoard;
        ballCenter_X = pipeBoard.texture.getWidth() * 2/5 + pipeBoard.getX();
        ballCenter_Y = pipeBoard.texture.getHeight()*5/4 + pipeBoard.getY();

        texture = new Texture(Gdx.files.internal("core/assets/sprites/balls/white_ball.gif"));
        sprite = new Sprite(texture);

        sprite.setPosition(ballCenter_X, ballCenter_Y);

        //ball collision borders
        bottom  = new Rectangle(sprite.getX()                    , sprite.getY(),sprite.getWidth(),1);
        up      = new Rectangle(sprite.getX()+ sprite.getHeight(), sprite.getY(),sprite.getWidth(),1);
        left    = new Rectangle(sprite.getX()                    , sprite.getY(),1                ,sprite.getHeight());
        right   = new Rectangle(sprite.getX()+ sprite.getWidth() , sprite.getY(),1                ,sprite.getHeight());
    }

    @Override
    public void drawSprite(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    @Override
    public boolean action(int type, float newY) {
        setPosition(ballCenter_X, ballCenter_Y + 100);
        return false;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public int collision(Rectangle rectangle) {
        if(bottom.overlaps(rectangle))
        {
            return 1;
            /*TODO angles and movement initialization*/
        }
        return -1;
    }

    @Override
    public void setPosition(float x, float y) {
        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x,y);
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public void destroy() {

    }
}
