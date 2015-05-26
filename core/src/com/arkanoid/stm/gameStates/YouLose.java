package com.arkanoid.stm.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Grzegorz on 2015-05-26.
 */
public class YouLose implements Screen {
    public final ArkanoidGdx game;
    private Sound youLoseSound;

    public YouLose(ArkanoidGdx game)
    {
        this.game= game;
    }

    @Override
    public void show() {
        youLoseSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/music/themes/victory/TriumphSound.mp3"));
        youLoseSound.play();
    }

    @Override
    public void render(float delta) {

        game.batch.begin();

        game.batch.draw(game.img, 0, 0);

        game.pipeBoard.drawSprite(game.batch);
        game.ball.drawSprite(game.batch);
        game.blocks.drawBlocks(game.batch);
        game.font.draw(game.batch, "LOOOSERR !!!!!", 150, 600);

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
        youLoseSound.dispose();
        game.dispose();
    }
}
