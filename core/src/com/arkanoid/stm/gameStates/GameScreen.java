package com.arkanoid.stm.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by grzeprza on 2015-05-06.
 */
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
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(game.img, 0, 0);

        game.pipeBoard.drawSprite(game.batch);
        game.ball.drawSprite(game.batch);
        game.blocks.drawBlocks(game.batch);

        game.batch.end();

        // UPDATES
        game.pipeBoard.update(Gdx.graphics.getDeltaTime());
        game.ball.update(Gdx.graphics.getDeltaTime());

        game.controls();
        game.collision();

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
        game.dispose();
    }
}
