package com.arkanoid.stm.game;

/**
 * Created by Grzegorz on 2015-04-07.
 */
public interface PipeMovement
{
    void moveLeft(float delta);
    void moveRight(float delta);
    void pushBall();
}
