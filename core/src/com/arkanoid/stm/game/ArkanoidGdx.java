package com.arkanoid.stm.game;

import com.arkanoid.stm.objects.Blocks;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.arkanoid.stm.objects.Balls;
import com.arkanoid.stm.objects.Block;
import com.arkanoid.stm.objects.PipeBoard;

public class ArkanoidGdx extends ApplicationAdapter
{

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private Rectangle screenBoundries_Down,
			screenBoundries_Up;

	boolean spacePressed =false;

	PipeBoard pipeBoard;
	Balls ball;
	Blocks blocks;

	@Override
	public void create () {

		screenBoundries_Down = new Rectangle(0,0,800,1);
		screenBoundries_Up = new Rectangle( 0,800,800,1);

		camera= new OrthographicCamera();
		camera.setToOrtho(false ,600, 800);

		batch = new SpriteBatch();

		img = new Texture(Gdx.files.internal("core/assets/sprites/newBackground.jpg"));

		pipeBoard= new PipeBoard();
		ball = new Balls(pipeBoard);

		blocks= new Blocks();
		blocks.loadBlocks_randomly();

		//ONLY FOR TEST
		//singleBlock= new Block(1,250, 250,100,100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(img, 0, 0);

		pipeBoard.drawSprite(batch);
		ball.drawSprite(batch);
		blocks.drawBlocks(batch);

		batch.end();

		// UPDATES
		pipeBoard.update(Gdx.graphics.getDeltaTime());
		ball.update(Gdx.graphics.getDeltaTime());

		collision();

		controls();

	}

	/**Enables movement of pipeboard*/
	public void controls()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			pipeBoard.moveLeft(Gdx.graphics.getDeltaTime());
		}

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			pipeBoard.moveRight(Gdx.graphics.getDeltaTime());
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			if( !spacePressed )
			{
				spacePressed = true;
				pipeBoard.pushBall();

			}

		}
	}

	/**Enables collision detection*/
	public void collision()
	{
		if(pipeBoard.collision(screenBoundries_Down) == 1)
		{
			spacePressed = pipeBoard.action(1, 2);
		}

		if(ball.collision(pipeBoard.getPipeRectangle()) == 1)
		{
			ball.action(1,1);
			pipeBoard.ballMoved = true;
		}


	}

}
