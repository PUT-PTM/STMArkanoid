package com.arkanoid.stm.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Grzegorz on 2015-05-26.
 */
public class YouLose implements Screen
{

    public final ArkanoidGdx game;
    private Sound youLoseSound;

    private long startTime, endTime;
    private int countDown;

    public YouLose(ArkanoidGdx game)
    {
        this.game= game;
        countDown=6;
    }

    @Override
    public void show() {
        youLoseSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/music/themes/youLose/youLose.mp3"));
        youLoseSound.play();

        startTime= System.currentTimeMillis()/1000;

    }

    @Override
    public void render(float delta) {

        game.batch.begin();

        game.batch.draw(game.img, 0, 0);

        game.pipeBoard.drawSprite(game.batch);
        game.ball.drawSprite(game.batch);
        game.blocks.drawBlocks(game.batch);
        game.font.draw(game.batch, "LOOOSERR !!!!!", 150, 600);
        game.font.draw(game.batch, "Again? Press Space", 200, 500);
        game.font.draw(game.batch, Integer.toString(countDown), 150, 300);

        game.batch.end();

        endTime= System.currentTimeMillis()/1000;

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            game.setScreen(new GameScreen(game,1));
        }
        if((startTime % 10) == endTime % 10)
        {
            ++startTime;
            --countDown;
            if(countDown == 0)
            {
                game.setScreen(new MainMenuScreen(game));
            }
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
        youLoseSound.dispose();
        game.dispose();
    }
}
