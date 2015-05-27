package com.arkanoid.stm.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

/**
 * Created by grzeprza on 2015-05-06.
 */
/**
 * Provides playing screen. You can exit pressing 'Escape' to menu. You can lose and win.*/
public class GameScreen implements Screen {

    final ArkanoidGdx game;

    public GameScreen(ArkanoidGdx game, int mode) {
        this.game = game;
        game.initArkanoidPart(mode);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        game.batch.begin();
        game.batch.draw(game.img, 0, 0);

        game.pipeBoard.drawSprite(game.batch);
        game.ball.drawSprite(game.batch);
        game.blocks.drawBlocks(game.batch);

        game.ball.update(Gdx.graphics.getDeltaTime());
        game.pipeBoard.update(Gdx.graphics.getDeltaTime());

        game.collision();

        game.controls();

        game.batch.end();
        // UPDATES

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }

        if(game.lifesLeft == 0)
        {
            game.victory= false;
            game.spacePressed=false;
            game.setScreen(new YouLose(game));
            this.dispose();
        }
        if(game.victory)
        {
            game.victory= false;
            game.spacePressed=false;
            game.setScreen(new Victory(game));
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
