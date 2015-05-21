package com.arkanoid.stm.gameStates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by grzeprza on 2015-05-20.
 */
public class Victory implements Screen
{
    public final ArkanoidGdx game;
    private Sound victorySound;

    public Victory(ArkanoidGdx game)
    {
        this.game= game;
    }

    @Override
    public void show() {
        victorySound= Gdx.audio.newSound(Gdx.files.internal("core/assets/music/themes/victory/TriumphSound.mp3"));
        victorySound.play();

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(game.img, 0, 0);

        game.pipeBoard.drawSprite(game.batch);
        game.ball.drawSprite(game.batch);
        game.blocks.drawBlocks(game.batch);
        game.font.draw(game.batch, "WELL DONE!!", 200, 600);

        game.ball.update(Gdx.graphics.getDeltaTime());
        game.pipeBoard.update(Gdx.graphics.getDeltaTime());

        game.collision();

        game.batch.end();
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
        victorySound.dispose();
        game.dispose();
    }
}
