package com.arkanoid.stm.gameStates;

import com.arkanoid.stm.objects.Balls;
import com.arkanoid.stm.objects.Block;
import com.arkanoid.stm.objects.Blocks;
import com.arkanoid.stm.objects.PipeBoard;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

//TODO moving ball different angles
//TODO Victory state
//TODO music
//TODO main menu
//TODO loading lvl from file
//TODO STM and JAVA
public class ArkanoidGdx extends Game
{
	BitmapFont font;
	private OrthographicCamera camera;
	public SpriteBatch batch;
	protected Texture img;
	private Rectangle screenBoundries_Down,screenBoundries_Left,
			screenBoundries_Up, screenBoundries_Right;

	boolean spacePressed =false;

	PipeBoard pipeBoard;
	Balls ball;
	Blocks blocks;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();

		font= new BitmapFont();

		this.setScreen(new MainMenuScreen(this));

	}
	/** Initializes balls, pipe, screen boundries*/
	protected void initArkanoidPart()
	{


		//Screen collision boundries
		screenBoundries_Down = new Rectangle(0,0,600,1);
		screenBoundries_Up = new Rectangle( 0,800,600,1);
		screenBoundries_Left= new Rectangle(0,0,1,800);
		screenBoundries_Right= new Rectangle(600,0,1,800);

		img = new Texture(Gdx.files.internal("core/assets/sprites/newBackground.jpg"));

		pipeBoard= new PipeBoard(300);
		ball = new Balls(pipeBoard);

		blocks= new Blocks();
		blocks.loadBlocks_randomly();
	}


	private void Game()
	{
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

	@Override
	public void render() {
		super.render();

	}

	/**Enables movement of pipeboard*/
	protected void controls()
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
	protected void collision()
	{
		pipe();

		ball();

		block();
	}

	/**Pipe collision behavior*/
	private void pipe()
	{
		/**Enables bouncing pipe at the beggining*/
		if(pipeBoard.collision(screenBoundries_Down) == 1 && !spacePressed)
		{
			spacePressed = pipeBoard.action(1, 2);
		}
	}

	/**Ball collision behavior*/
	private void ball()
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

	/**Block collision behavior. Includes part of ball behavior, because D.R.Y.*/
	private void block()
	{
		Block block;
		Iterator<Block> it= blocks.getActiveBlocksList().iterator();
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

			if(block.lifeCounter <= 0) it.remove();

		}

		for(Block passiveBlock: blocks.getPassiveBlocksList())
		{
			if (passiveBlock.collision(ball.horizontal) == 1) {
				ball.collision(passiveBlock.getBlock_rectangle());
				ball.action(3, 1);
			}

			if (passiveBlock.collision(ball.vertical) == 1) {
				ball.collision(passiveBlock.getBlock_rectangle());
				ball.action(2, 1);
			}
		}

	}

	public void dispose()
	{
		batch.dispose();
	}

}
