package com.arkanoid.stm.gameStates;

import com.arkanoid.stm.objects.Balls;
import com.arkanoid.stm.objects.Block;
import com.arkanoid.stm.objects.Blocks;
import com.arkanoid.stm.objects.PipeBoard;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

//TODO moving ball
//TODO music
//TODO main menu
//TODO STM and JAVA
public class ArkanoidGdx extends Game
{

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private Rectangle screenBoundries_Down,screenBoundries_Left,
			screenBoundries_Up, screenBoundries_Right;

	boolean spacePressed =false;

	PipeBoard pipeBoard;
	Balls ball;
	Blocks blocks;

	@Override
	public void create () {

		screenBoundries_Down = new Rectangle(0,0,600,1);
		screenBoundries_Up = new Rectangle( 0,800,600,1);
		screenBoundries_Left= new Rectangle(0,0,1,800);
		screenBoundries_Right= new Rectangle(600,0,1,800);

		camera= new OrthographicCamera();
		camera.setToOrtho(false ,600, 800);

		batch = new SpriteBatch();

		img = new Texture(Gdx.files.internal("core/assets/sprites/newBackground.jpg"));

		initArkanoidPart();

	}

	/** Initializes balls and pipe*/
	public void initArkanoidPart()
	{
		pipeBoard= new PipeBoard(300);
		ball = new Balls(pipeBoard);

		blocks= new Blocks();
		blocks.loadBlocks_randomly();
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

		if((Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))) {
			Gdx.app.exit();
			try {
				finalize();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}

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
		pipe();

		ball();

		block();
	}

	public void pipe()
	{
		/**Enables bouncing pipe at the beggining*/
		if(pipeBoard.collision(screenBoundries_Down) == 1 && !spacePressed)
		{
			spacePressed = pipeBoard.action(1, 2);
		}
	}

	public void ball()
	{
		if(ball.collision(pipeBoard.getPipeRectangle()) == 1)
		{
			ball.action(1,1);
			pipeBoard.ballMoved = true;
		}

		if(ball.collision(screenBoundries_Left) == 1 || ball.collision(screenBoundries_Right) == 1)
		{
			ball.action(3, 1);

		}
		if(ball.collision(screenBoundries_Up) == 1 || ball.collision(screenBoundries_Down) == 1)
		{
			ball.action(2, 1);
		}
	}

	public void block()
	{
		Block block;
		Iterator<Block> it= blocks.getBlockList().iterator();
		while(it.hasNext()) {
			block = it.next();

			if (block.collision(ball.horizontal) == 1) {
				ball.collision(block.getBlock_rectangle());
				ball.action(3, 1);
			}

			if (block.collision(ball.vertical) == 1) {
				ball.collision(block.getBlock_rectangle());
				ball.action(2, 1);

			}

			if(block.lifeCounter == 0) it.remove();

		}
	}

}
