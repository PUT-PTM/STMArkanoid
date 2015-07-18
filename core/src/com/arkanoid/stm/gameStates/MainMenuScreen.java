package com.arkanoid.stm.gameStates;

import com.arkanoid.stm.ScreenProperties;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by grzeprza on 2015-05-06.
 */
public class MainMenuScreen implements Screen
{
    final ArkanoidGdx game;

    OrthographicCamera camera;
    Music music;

    private String text_FreestyleMode= "Freestyle mode";
    private String text_hitSpace= "(hit space)";

    public MainMenuScreen(ArkanoidGdx arkanoidGdx) {
        game = arkanoidGdx;

        //switch do wyboru muzyki
        music= Gdx.audio.newMusic(Gdx.files.internal("core/assets/music/themes/mainMenu/SohnTremors.mp3"));
        music.setLooping(true);

        camera= new OrthographicCamera();
        camera.setToOrtho(false, ScreenProperties.widthFit, ScreenProperties.heightFit);
    }

    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, text_FreestyleMode, ScreenProperties.widthFit / 3 - (text_FreestyleMode.length() / 2), 600);
        game.font.draw(game.batch, text_hitSpace, ScreenProperties.widthFit/3-(text_hitSpace.length()/2), 550);
        //game.font.draw(game.batch, text_FreestyleMode, 300-(text_FreestyleMode.length()/2), 400);

        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game, 1));
            this.dispose();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
        {
            game.setScreen(new GameScreen(game, 2));
            this.dispose();
        }

        if((Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)))
        {
            Gdx.app.exit();
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
        music.dispose();
    }
}
